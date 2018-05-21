package com.yc.teach.util;

import java.io.BufferedInputStream;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 数据库操作助手类，实现上下文监听器接口，可加载数据库参数
 * @author 廖彦
 *
 */
public class DBHelper implements ServletContextListener {
	public static String URL = "jdbc:mysql://ly/ycdb?useUnicode=true&characterEncoding=UTF-8";
	public static String USR = "root";
	public static String PWD = "123";
	public static String DRV = "com.mysql.jdbc.Driver";
	
	private static Logger logger = Logger.getLogger(DBHelper.class);

	private static Context ctx;
	private static DataSource ds;

	static {
		init();
	}

	private static void init() {
		try {
			//优先使用 JNDI 数据源
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/sqlite/lydb");
			logger.debug("==============使用JNDI数据源==============");
			/*for(int i=0;i<100;i++) {
				Connection c = ds.getConnection();
				System.out.println(i+"+"+c);
				//c.close();
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage());
			try {
				// 从配置文件 db.properties 中读取数据库配置信息
				ResourceBundle bundle = ResourceBundle.getBundle("db");
				DRV = bundle.getString("driverClassName");
				URL = bundle.getString("url");
				USR = bundle.getString("user");
				PWD = bundle.getString("password");
				logger.debug("==============使用db.properties数据源==============");
			} catch (Exception e1) {
				logger.error(e.getMessage());
				logger.debug("==============使用DBHelper内部数据源==============");
			}
			// 使用代码中的数据库配置（加载驱动必须保证会执行到）
			try {
				Class.forName(DRV);
			} catch (ClassNotFoundException ex) {
				throw new RuntimeException(ex);
			}

		}
	}

	public static Connection getCon() {
		//优先使用 JNDI 数据源
		try {
			if (ds != null) {
				return ds.getConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//其次使用项目数据源
		try {
			return DriverManager.getConnection(URL, USR, PWD);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
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
			logger.debug("SQL:" + sql);
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			int rows = pstm.executeUpdate();
			logger.debug("update rows " + rows);
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
						logger.debug("参数" + i + "：" + p);
						pstm.setObject(i++, p);
					}
				} else {
					logger.debug("参数" + i + "：" + o);
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
			logger.debug("SQL:" + sql);
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
			logger.debug("select rows " + list.size());
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
			logger.debug("SQL:" + sql);
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
									logger.debug("未知类型：" + clsName + "===>" + value + "，听天由命了！");
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
		logger.debug("select rows " + list.size());
		return list;
	}

	/**
	 * 上下文监听器方法
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * 上下文监听器方法，获取web.xml文件里面配置的数据库连接信息
	 */
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

	/**
	 * insert 方法，新增完成后返回自增主键值，注意：该方法不支持批量 insert
	 * @param sql
	 * @param params
	 * @return 返回第一个自增列的值
	 */
	public static Integer doInsert(String sql, Object... params) {

		Connection con = getCon();
		PreparedStatement pstm = null;
		try {
			logger.debug("SQL:" + sql);
			pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			doParams(pstm, params);
			pstm.executeUpdate();
			// 获取自增列值结果集
			ResultSet rs = pstm.getGeneratedKeys();
			Integer key = null;
			if (rs.next()) {
				// 获取自增列值，先转字符串，再转Integer
				key = Integer.parseInt(rs.getString(1));
				logger.debug("insert row and return generated key " + key);
			}
			return key;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
	}

}
