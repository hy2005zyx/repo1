package com.ly.util;

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
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 数据库操作助手类，实现上下文监听器接口，可加载数据库参数
 * @author 廖彦
 *
 */
public class DBHelper {
	public static String URL = "jdbc:mysql://ly/ycdb?useUnicode=true&characterEncoding=UTF-8";
	public static String USR = "root";
	public static String PWD = "123";
	public static String DRV = "com.mysql.jdbc.Driver";

	private static Context ctx;
	private static DataSource ds;

	static {
		init();
	}

	private static void init() {
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle("db");
			String jndidb = bundle.getString("JNDI-DB");
			//优先使用 JNDI 数据源
			ctx = new InitialContext();
			jndidb = jndidb == null ? "java:comp/env/sqlite/lydb" : jndidb;
			ds = (DataSource) ctx.lookup(jndidb);
			System.out.println("==============使用JNDI数据源==============");
			System.out.println("JNDI数据源名称：" + jndidb);
			/*for(int i=0;i<100;i++) {
				Connection c = ds.getConnection();
				System.out.println(i+"+"+c);
				//c.close();
			}*/
		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				// 从配置文件 db.properties 中读取数据库配置信息
				DRV = bundle.getString("driverClassName");
				URL = bundle.getString("url");
				USR = bundle.getString("user");
				PWD = bundle.getString("password");
				System.out.println("==============使用db.properties数据源==============");
			} catch (Exception e1) {
				System.err.println(e.getMessage());
				System.out.println("==============使用DBHelper内部数据源==============");
			}
			// 使用代码中的数据库配置（加载驱动必须保证会执行到）
			try {
				System.out.println("数据库URL：" + URL);
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
						System.out.println("参数" + i + "：" + p);
						pstm.setObject(i++, p);
					}
				} else {
					System.out.println("参数" + i + "：" + o);
					pstm.setObject(i++, o);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询结果，返回 集合类型为 Vector ，元素类型类型也是 Vector 的结果集
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Vector<Vector<Object>> vector(String sql, Object... params) {
		return select(sql, new Vector<Vector<Object>>().getClass(), new Vector<Object>().getClass(), params);
	}

	/**
	 * 查询结果，以指定的实例类作为元素类型返回 List 结果集
	 * @param sql
	 * @param b
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <B> List<B> find(String sql, Class<B> b, Object... params) {
		return (List<B>) select(sql, new ArrayList<B>().getClass(), b, params);
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> find(String sql, Object... params) {
		return (List<Map<String, Object>>) select(sql, new ArrayList<HashMap<String, Object>>().getClass(),
				new HashMap<String, Object>().getClass(), params);
	}

	/**
	 * @param sql		执行的查询语句
	 * @param c			返回集合的类型，如：ArrayList、Vector
	 * @param b			返回元素的类型，如：HashMap、Vector、ArrayList、实体类
	 * @param params	参数：可变数组参数，没有参数则不写
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <C extends Collection<B>, B> C select(String sql, Class<C> c, Class<B> b, Object... params) {
		Collection<B> list;
		try {
			list = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new RuntimeException(e1);
		}
		Connection con = getCon();
		ResultSet rs;
		PreparedStatement pstmt;

		try {
			System.out.println("SQL:" + sql);
			pstmt = con.prepareStatement(sql);
			doParams(pstmt, params);
			rs = pstmt.executeQuery();

			Method[] ms = b.getMethods();
			ResultSetMetaData md = rs.getMetaData();

			String[] colnames = new String[md.getColumnCount()];
			for (int i = 0; i < colnames.length; i++) {
				colnames[i] = md.getColumnName(i + 1);
			}

			B t;
			String mname = null;
			String cname = null;

			while (rs.next()) {
				t = (B) b.newInstance();
				if (t instanceof Collection) {
					Collection<Object> coll = (Collection<Object>) t;
					for (int i = 0; i < colnames.length; i++) {
						coll.add(rs.getObject(colnames[i]));
					}
				} else {
					if (t instanceof Map) {
						Map<String, Object> map = (Map<String, Object>) t;
						for (int i = 0; i < colnames.length; i++) {
							map.put(colnames[i], rs.getObject(colnames[i]));
						}
					}
				}
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
		return (C) list;
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
			System.out.println("SQL:" + sql);
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			doParams(pstm, params);
			pstm.executeUpdate();
			// 获取自增列值结果集
			ResultSet rs = pstm.getGeneratedKeys();
			Integer key = null;
			if (rs.next()) {
				// 获取自增列值，先转字符串，再转Integer
				key = Integer.parseInt(rs.getString(1));
				System.out.println("insert row and return generated key " + key);
			}
			return key;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println(select("select * from user", Vector.class, ArrayList.class));
	}
}
