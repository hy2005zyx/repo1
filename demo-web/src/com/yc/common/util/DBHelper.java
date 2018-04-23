package com.yc.common.util;

import java.io.BufferedInputStream;
import java.io.IOException;
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
	public static String URL = "jdbc:mysql://ly/teach?useUnicode=true&amp;characterEncoding=UTF-8";
	public static String USR = "root";
	public static String PWD = "123";
	public static String DRV = "com.mysql.jdbc.Driver";
	
	
	static{
		try {
			Class.forName(DRV);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//2銆佽幏鍙栬繛鎺�
	public static Connection getCon(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USR, PWD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return con;
	}
	
	//3銆佸叧闂祫婧愶紝涔熷彲浠ュ皝瑁�
	//			鎴戜滑鎬诲叡鏈変笁涓笢瑗胯鍏抽棴
	public static void closeAll( ResultSet rs,  Statement stmt,  Connection con ){
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
	
	//寮�濮嬪皝瑁呭姛鑳�
	//绗竴涓紝澧炲垹鏀归兘鏄竴鏍风殑锛岃繑鍥炲�奸兘涓�鏍凤紝鎵�浠ユ垜浠厛鏉ュ皝瑁呭鍒犳敼鐨勫姛鑳�
	/**
	 * 
	 * @param sql           sql璇彞
	 * @param params		涓轰簡闃叉敞鍏ワ紝鎴戜滑浣跨敤棰勫鐞嗘柟寮忥紝鎵�浠ラ噷闈㈡槸鏈�?鐨勶紝鎴戜滑闇�瑕佹敞鍏ュ��
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static int doUpdate(String sql,List<Object> params){
		//瀹氫箟杩斿洖鍊�
		int result=-1;
		//鑾峰彇杩炴帴
		Connection con=getCon();
		//棰勫鐞�
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(sql);
			//璁剧疆鍙傛暟
			doParams(pstm,params);
			//鎵цsql璇彞
			result=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeAll(null, pstm, con);
		return result;
	}

	//璁剧疆鍙傛暟
	public static void doParams(PreparedStatement pstm, List<Object> params){
		//棣栧厛锛屽鏋滃弬鏁颁负null锛屽垯涓嶉渶瑕佽缃弬鏁�
		try {
			if(pstm!=null && params!=null && params.size()>0){
				//灏唒arams涓殑鍙傛暟寰幆鍙栧嚭锛屼竴涓竴涓殑璁剧疆鍒皃stm閲岄潰鍘伙紝浣嗘槸娉ㄦ剰涓�涓嬫暟鎹被鍨�
				for(int i=0;i<params.size();i++){
					Object o=params.get(i);
					pstm.setObject(i+1, o);
				} 
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//鏌ヨ
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return   鍥犱负鏌ヨ鍑烘潵鐨勬槸涓�涓敭鍊煎褰㈠紡鐨勶紝鍒楁槑涓洪敭锛屽�间负鍊硷紝鎵�浠ユ垜浠�
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static List<Map<String,Object>> findAll(String sql , List<Object> params){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Connection con=getCon();
		PreparedStatement pstm = null;
		//鏌ヨ寰楀埌缁撴灉闆�
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			rs = pstm.executeQuery();
			//鎷垮埌鍏冩暟鎹紝鍙栧嚭姣忎竴涓垪鍚�
			ResultSetMetaData rsmd=rs.getMetaData();
			//瀛樺埌涓�涓暟缁勯噷闈㈠幓
			String[] columnName=new String[rsmd.getColumnCount()];
			for(int i=0;i<columnName.length;i++){
				//鍙栧�煎瓨鍊硷紝娉ㄦ剰锛�+1
				columnName[i]=rsmd.getColumnName(i+1);
			}
			
			//寮�濮嬪鐞嗘暟鎹�   寰幆rs锛屽彇鍑烘瘡涓�鍒楃殑鏁版嵁锛屽瓨鍒癕ap涓紝鍐嶆妸Map瀛樺埌List涓�
			while(rs.next()){
				Map<String,Object> map=new HashMap<String,Object>();
				//鏍规嵁鍒楀悕鍙栧��
				for(String cn : columnName){
					map.put(cn, rs.getObject(cn));
				}
				//寰幆瀹屾瘯锛屽瓨list
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(rs, pstm, con);
		}
		
		//杩斿洖鏁版嵁
		return list;
	}
	
	
	public static <T> List<T> find(String sql,List<Object> params,Class<T> c){
		List<T> list=new ArrayList<T>(); //瑕佽繑鍥炵殑缁撴灉鐨勯泦鍚�
		Connection con=getCon(); //鑾峰彇杩炴帴
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt=con.prepareStatement(sql); //棰勭紪璇戝璞�
			doParams(pstmt, params); //璁剧疆鍗犱綅绗�
			rs=pstmt.executeQuery();  //鎵ц鏌ヨ璇彞锛屽緱鍒扮粨鏋滈泦

			Method[] ms=c.getMethods(); //鍙栧嚭杩欎釜鍙嶅皠瀹炰緥鐨勬墍鏈夋柟娉�
			ResultSetMetaData md=rs.getMetaData(); //鑾峰彇缁撴灉闆嗙殑鍏冩暟鎹紝瀹冨弽鏄犱簡缁撴灉闆嗙殑淇℃伅

			String[] colnames=new String[md.getColumnCount()];//鍒涘缓涓�涓暟鎹甤olnames锛岀敤鏉ュ瓨鏀惧垪鐨勫悕瀛�
			for(int i=0;i<colnames.length;i++){  //灏嗗垪鍚嶄繚瀛樺埌colname鏁扮粍涓�
				colnames[i]=md.getColumnName(i+1);
			}

			T t;
			String mname=null;  //鏂规硶鍚�
			String cname=null;  //鍒楀悕
			String ctypename=null; //绫诲瀷鍚�

			while(rs.next()){
				t=(T)c.newInstance(); //鍒涘缓鍙嶅皠绫荤殑瀹炰緥鍖栧璞�    Product t=(Product)c.newInstance();
				for(int i=0;i<colnames.length;i++){//寰幆鏂规硶鍚� ,鏍煎紡涓簊etXXXX鎴杇etXXX
					cname=colnames[i]; //鍙栧嚭鍒楀悕骞跺湪鍓嶉潰鍔犱笂set  setXXX
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					if(ms!=null&&ms.length>0){
						for(Method m:ms){//寰幆鍒楀悕
							mname=m.getName(); //鍙栧嚭鏂规硶鍚�

							if(cname.equals(mname)&&rs.getObject(colnames[i])!=null){//鍒ゆ柇鏂规硶鍚嶅拰鍒楀悕鏄惁涓�鏍凤紝鐩稿悓鍒欐縺娲绘柟娉曪紝娉ㄥ叆鏁版嵁                           //鍙"set"+鏁版嵁鍒楀悕.equalsIgnoreCase锛堟柟娉曞悕锛夛紝鍒欐縺娲昏繖涓柟娉�
								//setXXX(String str); setXXX(int num); 婵�娲诲搴旂殑鏂规硶杩樺繀椤荤煡閬撳畠鐨勬暟鎹被鍨�
								ctypename=rs.getObject(colnames[i]).getClass().getName();//鑾峰彇褰撳墠鍒楃殑绫诲瀷鍚�
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		closeAll(rs, pstmt, con);
		return list;
	}
}
