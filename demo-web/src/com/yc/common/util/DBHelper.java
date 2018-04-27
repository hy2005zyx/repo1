package com.yc.common.util;

import java.io.BufferedInputStream;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 数据库操作助手类，实现上下文监听器接口，可加载数据库参数
 * @author 廖彦
 *
 */
public class DBHelper implements ServletContextListener {
	public static String URL = "jdbc:mysql://ly/ycdb?useUnicode=true&amp;characterEncoding=UTF-8";
	public static String USR = "root";
	public static String PWD = "123";
	public static String DRV = "com.mysql.jdbc.Driver";

	static {
		init();
	}

	private static void init() {
		try {
			Class.forName(DRV);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USR, PWD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return con;
	}

	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static int doUpdate(String sql, Object... params) {

		Connection con = getCon();
		PreparedStatement pstm = null;
		try {
			System.out.println("SQL:" + sql);
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			int rows = pstm.executeUpdate();
			System.out.println("update rows " + rows);
			return rows;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
	}


	@SuppressWarnings("unchecked")
	public static void doParams(PreparedStatement pstm, Object... params) {
		try {
			int i = 1;
			for (Object o : params) {
				//如果元素是一个集合，则取出所有集合里的元素作为参数
				//不确定的参数类型，直接使用setObject，让jdbc去转型
				if (o instanceof Collection) {
					for (Object p : (Collection<Object>) o) {
						pstm.setObject(i++, p);
					}
				} else {
					pstm.setObject(i++, o);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Map<String, Object>> findAll(String sql, Object... params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = getCon();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			System.out.println("SQL:" + sql);
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			rs = pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			String[] columnName = new String[rsmd.getColumnCount()];
			for (int i = 0; i < columnName.length; i++) {
				columnName[i] = rsmd.getColumnName(i + 1);
			}

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				for (String cn : columnName) {
					map.put(cn, rs.getObject(cn));
				}

				list.add(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}

		return list;
	}

	public static <T> List<T> find(String sql, Class<T> c, Object... params) {
		List<T> list = new ArrayList<T>();
		Connection con = getCon(); 
		ResultSet rs;
		PreparedStatement pstmt;

		try {
			System.out.println("SQL:" + sql);
			pstmt = con.prepareStatement(sql); 
			doParams(pstmt, params); 
			rs = pstmt.executeQuery(); 

			Method[] ms = c.getMethods();
			ResultSetMetaData md = rs.getMetaData(); 

			String[] colnames = new String[md.getColumnCount()];
			for (int i = 0; i < colnames.length; i++) {
				colnames[i] = md.getColumnName(i + 1);
			}

			T t;
			String mname = null; 
			String cname = null; 

			while (rs.next()) {
				t = (T) c.newInstance();
				for (int i = 0; i < colnames.length; i++) {
					//空值忽略
					Object value = rs.getObject(colnames[i]);
					if (value == null) {
						continue;
					}
					cname = colnames[i];
					cname = "set" + cname.substring(0, 1).toUpperCase() + cname.substring(1).toLowerCase();
					if (ms != null && ms.length > 0) {
						for (Method m : ms) {
							mname = m.getName(); 
							if (cname.equals(mname)) {
								Class<?> cls = m.getParameterTypes()[0];
								String clsName = cls.getSimpleName().toLowerCase();
								switch (clsName) {
								case "byte":
									m.invoke(t, rs.getByte(colnames[i]));
									break;
								case "short":
									m.invoke(t, rs.getShort(colnames[i]));
									break;
								case "integer":
									m.invoke(t, rs.getInt(colnames[i]));
									break;
								case "long":
									m.invoke(t, rs.getLong(colnames[i]));
									break;
								case "float":
									m.invoke(t, rs.getFloat(colnames[i]));
									break;
								case "double":
									m.invoke(t, rs.getDouble(colnames[i]));
									break;
								case "string":
									m.invoke(t, rs.getString(colnames[i]));
									break;
								case "boolean":
									m.invoke(t, rs.getBoolean(colnames[i]));
									break;
								case "date":
									m.invoke(t, rs.getDate(colnames[i]));
									break;
								case "timestamp":
									m.invoke(t, rs.getTimestamp(colnames[i]));
									break;
								case "byte[]":
									BufferedInputStream is = null;
									byte[] bytes = null;
									Blob blob = rs.getBlob(colnames[i]);
									try {
										is = new BufferedInputStream(blob.getBinaryStream());
										bytes = new byte[(int) blob.length()];
										is.read(bytes);
									} catch (Exception e) {
										e.printStackTrace();
									}
									m.invoke(t, bytes);
									break;
								default:
									System.out.println("未知类型：" + clsName + "===>" + value + "，听天由命了！");
									m.invoke(t, value);
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
		} finally {
			close(con);
		}
		System.out.println("select rows " + list.size());
		return list;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//从上下文初始化参数中加载连接参数
		String driver = sce.getServletContext().getInitParameter("dbDriver");
		if (driver != null) {
			DRV = driver;
			URL = sce.getServletContext().getInitParameter("dbUrl");
			USR = sce.getServletContext().getInitParameter("dbUser");
			PWD = sce.getServletContext().getInitParameter("dbPwd");
			init();
		}
	}

}
