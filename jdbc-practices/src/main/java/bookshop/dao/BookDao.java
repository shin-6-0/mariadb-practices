package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;
import hr.dao.vo.EmployeesVo;

public class BookDao {

	public static boolean updateRent(BookVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.187:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. SQL 준비
			String sql =
				"Update book"
				+ " set rent = ?"
				+ " where no= ? ";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding 
			pstmt.setString(1, vo.getRent());
			pstmt.setInt(2, vo.getNo());
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			//6. 결과 처리
			result = count == 1;
			
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
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.187:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. SQL 준비
			String sql =
				"select b.title, a.name, b.rent"
				+ " from book b"
				+ " inner join author a on b.author_no = a.no";
			pstmt = conn.prepareStatement(sql);
			//4. binding 할게없음
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				String title=rs.getString(1);
				String author = rs.getString(2);
				String rent = rs.getString(3);
				//System.out.println(title+", "+author+", "+rent);
				
				BookVo vo= new BookVo();
				vo.setTitle(title);
				vo.setName(author);
				vo.setRent(rent);
				
				result.add(vo);
			}
			
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
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
