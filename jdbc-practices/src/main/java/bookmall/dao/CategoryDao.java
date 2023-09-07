package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	public List<CategoryVo> selectCategoryAll() {
		List<CategoryVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);

				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setName(name);
				
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

	public void insert(CategoryVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "insert into"
			+" category (name) values ('"+vo.getName()+ "') ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, vo.getName());
			pstmt.executeQuery();
						
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
	}

	public void delete(String categoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "delete from category where name = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, categoryName);
			pstmt.executeQuery();
						
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
	}
	
	public String selectCategoryOne(long categoryNo) {
		String categoryName = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "select name from category"
					+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, categoryNo);

			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			categoryName = rs.getString(1);
			
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
		return categoryName;
	}
	
	private Connection getConnection() throws SQLException {
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
