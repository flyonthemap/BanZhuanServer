package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * JDBC工具类
 */
public class JDBCUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static{
		try {
			// 读取配置文件
			Properties prop  = new Properties();
			
			InputStream is = JDBCUtils.class.getResourceAsStream("/db.properties");
			
			//InputStream is = new FileInputStream("src/db.properties");
			prop.load(is);
			
			is.close();
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			//注册驱动
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//获得连接
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建连接失败!");
		}
		return conn;
	}
	
	/*
	 *  释放资源
	 *  参数可能为空
	 *  调用close方法要抛出异常,确保即使出现异常也能继续关闭
	 *  关闭顺序,需要从小到大
	 */

	public  static void  close(Connection conn , Statement st , ResultSet rs){
		
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(st!=null){
				st.close();	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(conn!=null){
						conn.close();	
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
