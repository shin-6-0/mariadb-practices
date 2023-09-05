package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class BookDao {

	public List<BookVo> selectBookAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "select title,price from book";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				String title = rs.getString(1);
				Long price = rs.getLong(2);

				BookVo vo = new BookVo();
				vo.setTitle(title);
				vo.setPrice(price);
				
				result.add(vo);
			}
			
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
	
	public boolean insert(BookVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmtBefore = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn=getConnection();

			//3. SQL 준비
			String chkCategorySql="select no from category where name = '"
					+vo.getCategoryName()+"' ";
			pstmtBefore = conn.prepareStatement(chkCategorySql);
			rs = pstmtBefore.executeQuery();
			Long categoryNo = 0L;
			while(rs.next()) {
				categoryNo = rs.getLong(1);
			}
		
			String sql = "insert into"
			+" book (title,price,category_no) values (?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, categoryNo);
			int count = pstmt.executeUpdate();
			result = count == 1;
						
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	public boolean updateBookPrice(String title, Long price) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "update book"
					+ " set price = ?"
					+ " where title = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, price);
			pstmt.setString(2, title);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	


	public static Long findBookNo(String title) {
		Long bookNo = 0L;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();

			String findBookNoSql = "select no from book "
					+ "where title = '"+title+"'";
			pstmt = conn.prepareStatement(findBookNoSql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				bookNo=rs.getLong(1);
			}
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		return bookNo;
	}
	
	public static String findBookTitle(Long no ) {
		String bookTitle = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();

			String findBookNoSql = "select title from book "
					+ "where no = '"+no.intValue()+"'";
			pstmt = conn.prepareStatement(findBookNoSql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				bookTitle=rs.getString(1);
			}
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		return bookTitle;
	}

	public static Long findBookPrice(Long no ) {
		Long Price = 0L;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();

			String findBookNoSql = "select price from book "
					+ "where no = '"+no.intValue()+"'";
			pstmt = conn.prepareStatement(findBookNoSql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Price=rs.getLong(1);
			}
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
		return Price;
	}
	
	private static Connection getConnection() throws SQLException {
		Connection conn=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.13:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;
	}



}
