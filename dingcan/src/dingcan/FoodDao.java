package dingcan;

import java.sql.*;

/**
 * 访问菜品表的数据访问对象
 * @author Administrator
 *
 */
public class FoodDao {
	
	//实例变量（属性）
	int a;
	
	//方法定义
	//返回值   方法名     参数列表
	void    test  (     ){
		// 方法体
	}
	
	/**
	 * 主方法  类的执行方法
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException {
		
		//JDBC：java连接数据库组件
		//1、加载驱动
		Class.forName("com.mysql.jdbc.Driver"); //安装 ctrl + 鼠标   = 变手
		
		//2、获取连接
		String url = "jdbc:mysql://ly/dingcan?"
				+ "useUnicode=true&charEncoding=utf-8";
		String user = "root";
		String password = "123";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		//3、创建并执行语句对象
		Statement stat = conn.createStatement();
		String sql = "select * from resfood";
		//查询并返回结果集对象
		ResultSet rs = stat.executeQuery(sql);
		
		//next 将指针（游标）指向下一个记录
		while(rs.next()){
			System.out.print(rs.getInt("fid")+"\t");
			System.out.print(rs.getString("fname")+"\t");
			System.out.print(rs.getDouble("normprice")+"\t");
			System.out.print(rs.getDouble("realprice")+"\t");
			System.out.print(rs.getString("detail")+"\t");
			System.out.print(rs.getString("fphoto"));
			System.out.println();
		}
		
		//4、关闭连接
		conn.close();
		
	}

}
