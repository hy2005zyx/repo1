package com.ly.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper {
	//封装     jdbc的写法太麻烦，每次都需要写好多行，因此我们把他封装一下
	//1、driver只需要整个程序运行过程中注册一次即可.  而且整个程序中都会用到，而且只有一次，所以可以用静态块处理
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2、获取连接
	public Connection getCon(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(MyProperties.getInstance()
					.getProperty("url"), MyProperties.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//3、关闭资源，也可以封装
	//			我们总共有三个东西要关闭
	public void closeAll( ResultSet rs,  Statement stmt,  Connection con ){
		try {
			if( rs!=null){
				rs.close();
			}
			if(    stmt!=null){
				stmt.close();
			}
			if(   con!=null ){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//开始封装功能
	//第一个，增删改都是一样的，返回值都一样，所以我们先来封装增删改的功能
	/**
	 * 
	 * @param sql           sql语句
	 * @param params		为了防注入，我们使用预处理方式，所以里面是有?的，我们需要注入值
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public int doUpdate(String sql,List<Object> params){
		//定义返回值
		int result=-1;
		//获取连接
		Connection con=getCon();
		//预处理
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			//设置参数
			doParams(pstm,params);
			//执行sql语句
			result=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeAll(null, pstm, con);
		return result;
	}

	//设置参数
	public void doParams(PreparedStatement pstm, List<Object> params){
		//首先，如果参数为null，则不需要设置参数
		try {
			if(pstm!=null && params!=null && params.size()>0){
				//将params中的参数循环取出，一个一个的设置到pstm里面去，但是注意一下数据类型
				for(int i=0;i<params.size();i++){
					Object o=params.get(i);
					//判断类型
					if(o instanceof Integer){
						Integer t=Integer.parseInt( o.toString() );
						pstm.setInt(i+1, t);
					}else if(o instanceof String){
						pstm.setString(i+1, o.toString());
					}else if(o instanceof Double){
						Double d=Double.parseDouble(o.toString());
						pstm.setDouble(i+1, d);
					}else{
						pstm.setBytes(i+1, (byte[]) o);
					}
					//long    boolean     blob     clob   xxxxx
				} 
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//查询
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return   因为查询出来的是一个键值对形式的，列明为键，值为值，所以我们
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public List<Map<String,String>> findAll(String sql , List<Object> params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Connection con=getCon();
		PreparedStatement pstm = null;
		//查询得到结果集
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			rs = pstm.executeQuery();
			//拿到元数据，取出每一个列名
			ResultSetMetaData rsmd=rs.getMetaData();
			//存到一个数组里面去
			String[] columnName=new String[rsmd.getColumnCount()];
			for(int i=0;i<columnName.length;i++){
				//取值存值，注意，+1
				columnName[i]=rsmd.getColumnName(i+1);
			}
			
			//开始处理数据   循环rs，取出每一列的数据，存到Map中，再把Map存到List中
			String ctypename="";
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				//根据列名取值
				for(int i=0;i<columnName.length;i++){
					String cn=columnName[i];	//得到列名
					//根据列名取值
					ctypename=rs.getObject(cn).getClass().getName();
					if("oracle.sql.BLOB".equals(ctypename)){
						BufferedInputStream is = null;
						byte[] bytes = null;
						Blob  blob=rs.getBlob(cn);
						is = new BufferedInputStream(blob.getBinaryStream());
						bytes = new byte[(int) blob.length()];
						map.put(cn, bytes.toString());
					}else{
						String value=rs.getString(cn);
						//有了键和值，开始存Map
						map.put(cn, value);
					}
					
				}
				//循环完毕，存list
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(rs, pstm, con);
		}
		
		//返回数据
		return list;
	}
	
	
	public <T> List<T> find(String sql,List<Object> params,Class<T> c){
		List<T> list=new ArrayList<T>(); //要返回的结果的集合
		Connection con=getCon(); //获取连接
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt=con.prepareStatement(sql); //预编译对象
			doParams(pstmt, params); //设置占位符
			rs=pstmt.executeQuery();  //执行查询语句，得到结果集

			Method[] ms=c.getMethods(); //取出这个反射实例的所有方法
			ResultSetMetaData md=rs.getMetaData(); //获取结果集的元数据，它反映了结果集的信息

			String[] colnames=new String[md.getColumnCount()];//创建一个数据colnames，用来存放列的名字
			for(int i=0;i<colnames.length;i++){  //将列名保存到colname数组中
				colnames[i]=md.getColumnName(i+1);
			}

			T t;
			String mname=null;  //方法名
			String cname=null;  //列名
			String ctypename=null; //类型名

			while(rs.next()){
				t=(T)c.newInstance(); //创建反射类的实例化对象    Product t=(Product)c.newInstance();
				for(int i=0;i<colnames.length;i++){//循环方法名 ,格式为setXXXX或getXXX
					cname=colnames[i]; //取出列名并在前面加上set  setXXX
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					if(ms!=null&&ms.length>0){
						for(Method m:ms){//循环列名
							mname=m.getName(); //取出方法名

							if(cname.equals(mname)&&rs.getObject(colnames[i])!=null){//判断方法名和列名是否一样，相同则激活方法，注入数据                           //只要"set"+数据列名.equalsIgnoreCase（方法名），则激活这个方法
								//setXXX(String str); setXXX(int num); 激活对应的方法还必须知道它的数据类型
								ctypename=rs.getObject(colnames[i]).getClass().getName();//获取当前列的类型名
								if("java.lang.Integer".equals(ctypename)){
									m.invoke(t,rs.getInt(colnames[i])); //obj.setXX(xx);
								}else if("java.lang.String".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.math.BigInteger".equals(ctypename)){
									m.invoke(t, rs.getDouble(colnames[i]));
								}else if("java.math.BigDecimal".equals(ctypename)){
									try{
										m.invoke(t, rs.getInt(colnames[i]));
									}catch(Exception e1){
										m.invoke(t, rs.getDouble(colnames[i]));
									}
								}else if("java.sql.Timestamp".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Date".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Time".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("image".equals(ctypename)){
									m.invoke(t,rs.getBlob(colnames[i]));
								}else if("oracle.sql.BLOB".equals(ctypename)){
									BufferedInputStream is = null;
									byte[] bytes = null;
									Blob  blob=rs.getBlob(colnames[i]);
									try {
										is = new BufferedInputStream(blob.getBinaryStream());
										bytes = new byte[(int) blob.length()];
										is.read(bytes);
									} catch (Exception e) {
										e.printStackTrace();
									}
									m.invoke(t,bytes);
								}else{
									m.invoke(t, rs.getString(colnames[i]));
								}
								break;
							}
						}
					}
				}
				list.add(t);
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		closeAll(rs, pstmt, con);
		return list;
	}
}
