package com.yc.teach.util;

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



//eclipse 消失  黯然失色  漆黑
public class DBHelper {
	/*开始封装   jdbc的原生写法太麻烦  要写好多次 而且   还容易出错
	 * 
	 * 思路：  
	 * 		加载驱动，绝对有   而且不变的的  而且呢  我们想要程序一运行就加载  并且贯穿整个程序
	 * 
	 * 
	 * 
	 * */
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取连接   有返回值   每一个里面都要有
	public Connection getCon(){
		Connection con=null;
		try {
			 con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","a");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	//开始封装功能
	//增删改  返回值都一样
	public int doUpdate(String sql,List<Object> params){
		//定义返回值
		int result=-1;
		Connection con=getCon();
		//获取连接
		try {
			//预处理
			PreparedStatement pstm=con.prepareStatement(sql);
			//设置参数   多个地方使用同一个功能  再封装 
			doParams(pstm,params);
			//ִ执行sql 
			result = pstm.executeUpdate();
			//关闭连接
			closeAll(pstm, null, null, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		return result;
		
	}
	
	
	//查询
	//问题  返回类型      参数
	public List<Map<String,String>> findAll(String sql,List<Object> params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		//这些跟前面都是一样的
		try{
			Connection con=getCon();
			PreparedStatement pstm=con.prepareStatement(sql);
			doParams(pstm,params);
			ResultSet rs=pstm.executeQuery();
			//
			ResultSetMetaData rsmd=rs.getMetaData();
			//刚才看到，列名是以数组的形式存在的
			String[] columnName=new String[rsmd.getColumnCount()  ];
			for(int i=0;i<columnName.length ;i++){
				// 存列名                                        注意：这里不是从0开始的  而是从1开始的
				columnName[i]=rsmd.getColumnName(i+1);
			}
			
			while(rs.next() ){
				Map<String,String> map=new HashMap<String,String>();
				//根据列名来获取值
				for(int i=0;i<columnName.length ;i++){
					
					String cn=columnName[i];
					//获取值ֵ
					String value=rs.getString(cn);
					//键有了  值也有了  存map中
					map.put(cn, value);
				}
				//一个next遍历完毕   意味着一个map存完
				list.add(map);
			}
			closeAll(pstm,null,rs,con);
			
			//到了这一步  就开始不一样了
			//分析：根据原生的jdbc  最终是通过get方法，对应键  获取值  -> Map<String,String>
			//我们不可能只有一条数据  很多数据  ->List
			//融合  List<Map<String,String>>
			//返回类型确定
			//现在的难点  如何确定这个键  换句话说  如何确定一个表里面所有的列
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void doParams(PreparedStatement pstm, List<Object> params) {
		// TODO Auto-generated method stub
		try {
		if(pstm!=null&&params!=null&&params.size()>0){
			//做兼容处理ֵ
			for(int i=0;i<params.size() ;i++){
				Object o=params.get(i);
				//因为不知道具体的数据类型是什么 所以我们需要一个一个的去判断
				if( o instanceof Integer ){
					Integer t=Integer.parseInt( o.toString());
					pstm.setInt(i+1, t);
				}else if(o instanceof String){
						pstm.setString(i+1, o.toString() );
				} else if(o instanceof Double){
					Double t=Double.parseDouble( o.toString());
					pstm.setDouble(i+1, t);
				}else{
					pstm.setBytes(i+1, (byte[])o);
				}
				// long   boolean   blob clob
			}	
		}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//查询  基于对象的查询
	//问题 ？  对象确定不了   泛型<T>
	//返回类型？  list对象
	//参数？  sql  params  查询的对象
	// 参数：sql sql语句 
	//    params  注入的参数
	//    c  用户传进来的对象
	
	
	public <T> List<T> find(String sql,List<Object> params,Class<T> c){
		List<T> list = new ArrayList<T>();
		Connection con=getCon();
		PreparedStatement pstm = null;
		//查询得到的结果
		ResultSet rs=null;
		
		try {
			pstm = con.prepareStatement(sql);
			doParams(pstm,params);
			rs = pstm.executeQuery();
			
			//拿到元数据，取出一个列名
			//System.out.println(rs);
			ResultSetMetaData rsmd=rs.getMetaData();
			//存到一个数据里面去
			//System.out.println(rsmd);
			String[] columnName=new String[rsmd.getColumnCount() ];
			for(int i=0;i<columnName.length;i++){
				//取值存值                                                     注意 +1；
				columnName[i] = rsmd.getColumnName(i + 1);
				//System.out.println(rsmd.getColumnName(1  ));
			}
			//到这里开始   就不一样了
			//思考  上面的方法  到了这一步   开始存值了
			//现在是对象  对象存值用的是set方法          setUsid  setUname  setPhoto
			//现在我们知道  有哪些方法 但其实   我们是不知道的   所以 我们就可以同过传进来的对象  来获取它里面的所有方法
			
			Method[] ms=c.getMethods();
			//System.out.println(ms);
			
			//定义变量
			T t;
			String mname;   //方法名
			String cname;  //列名字
			String ctypename;  //类型名
			
			
			while(rs.next() ){
				//第一步，得到一个对象实例
				
					t=(T)c.newInstance();   //  UserInfo t=(UserInfo)c.newInstance();
				
				//循环取名字，得到方法名
				for(int i=0;i<columnName.length ;i++){
					cname=columnName[i];    
					//开始转换  usid  ->   setUid()   setUname setPhoto
					cname="set"+cname.substring(0, 1).toUpperCase()+cname.substring(1).toLowerCase();
					//System.out.println(cname);
					//容错处理
					if(ms!=null && ms.length>0){
						for(Method m : ms){
							//开始比较
							mname=m.getName();
							//在找到方法名的同时 还得保证 它的值不为空
							if(cname.equals(mname)  &&  rs.getObject(columnName[i] )!=null ){
								//触发set方法  问题  不能直接调用  必须反向激活
								//m.invoke(t,rs.getString(columnName[i]) );
								//不能直接这么写  我们还得判断数据类型
								ctypename=rs.getObject(columnName[i] ).getClass().getName();
								//System.out.println(ctypename);
								//不能直接这么写  我们还得判断数据类型
								if("java.lang.Integer".equals(ctypename) ){
									m.invoke(t,rs.getInt(columnName[i]) );
								}else if("java.lang.String".equals(ctypename) ){
									m.invoke(t,rs.getString(columnName[i]) );
								}else if("java.math.BigDecimal".equals(ctypename)){
									try {
										m.invoke(t,rs.getInt(columnName[i]) );
									} catch (Exception e) {
										m.invoke(t,rs.getDouble(columnName[i]) );
									}
								}else if("oracle.sql.BLOB".equals(ctypename)){
									//吧oracle里面的blob类型的值  转换为byte[] 的值
									BufferedInputStream is=null;
									byte[] bytes=null;
									Blob blob=rs.getBlob(columnName[i] );
									is=new BufferedInputStream(blob.getBinaryStream() );
									// blob.getBinaryStream()  二进制流转化   数据库中 存图片等文件
									bytes=new byte[(int) blob.length() ];
									is.read(bytes);
									m.invoke(t,bytes);
								}else{
									//invoke  反向激活
									m.invoke(t,rs.getString(columnName[i]) );
								}
							}
							
						}
					}
				}	
				list.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeAll(pstm, null, rs, con);
		return list;
		
	}
	public void closeAll(PreparedStatement pstm,Statement stmt,ResultSet rs,Connection con){
	try {
		//封装的话  就一定要考虑兼容问题
		if(pstm!=null){
			pstm.close();
		}
		if(stmt!=null){
			stmt.close();
		} 
		if(rs!=null){
			rs.close();
		}
		if(con!=null){
			con.close();
		}
	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
