package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDriverTest {


	public static void main(String[] args) {
		searchEmployees("ko");
	}
	
	public static void searchEmployees(String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("driver.MyDriver");
			
			//2. 연결하기
			String url = "jdbc:mydb://192.168.0.187:3307/mydb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결성공!-"+conn);
			
			/*
			 * //3. Statement 객체 생성 stmt = conn.createStatement();
			 * 
			 * //4. SQL 실행 String sql = "select emp_no, first_name, last_name" +
			 * "  from employees" + " where first_name like '%" + keyword + "%'" +
			 * "   and last_name like '%" + keyword + "%'";
			 * 
			 * rs = stmt.executeQuery(sql);
			 * 
			 * //5. 결과 처리 while(rs.next()) { Long empNo = rs.getLong(1); String firstName =
			 * rs.getString(2); String lastName = rs.getString(3);
			 * 
			 * System.out.println(empNo + ":" + firstName + ":" + lastName); }
			 */
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

}
