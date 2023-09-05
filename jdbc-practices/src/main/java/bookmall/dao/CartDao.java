package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookmall.vo.OrderBookVo;

public class CartDao {
	
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

	public List<Map<String,Long>> selectCartPersonal(Long memberNo) {
		List<Map<String,Long>> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();
			
			//3. SQL 준비
			String sql = "select mem_no, book_no, quantity"
					+ " from cart"
					+ " where mem_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, memberNo);

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				Map<String, Long> map = new HashMap<>();
				Long memNo = rs.getLong(1);
				Long bookNo = rs.getLong(2);
				Long quantity = rs.getLong(3);
				
				map.put("memNo", memNo);
				map.put("bookNo", bookNo);
				map.put("quantity", quantity);
				
				list.add(map);
			}
						
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
		return list;
	}

	public boolean updateCartEach(Long memberNo, Long bookNo, long quantity) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmtMem = null;
		PreparedStatement pstmtBook = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
		
			String sql = "update cart "
					+ "set quantity = ? "
					+ "where mem_no = ? and book_no = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, quantity);
			pstmt.setLong(2, memberNo);
			pstmt.setLong(3, bookNo);
			
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

	public boolean deleteCartEach(Long memberNo, Long bookNo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmtMem = null;
		PreparedStatement pstmtBook = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
			
			String sql = "delete from cart "
					+ "where mem_no = ? and book_no = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, bookNo);
			
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

	public boolean insertCartEach(Long memberNo, Long bookNo, long quantity) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmtMem = null;
		PreparedStatement pstmtBook = null;
		PreparedStatement pstmtExist = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
						
			//추가할 값이 이미 존재하는지 확인
			String chkExistSql = "select quantity from cart"
					+ " where book_no='"+bookNo+"' and mem_no = '"+memberNo+"'";
			pstmtExist = conn.prepareStatement(chkExistSql);
			rs=pstmtExist.executeQuery();
			Long quantityChk=0L;
			while(rs.next()) {
				quantityChk=rs.getLong(1);
			}
			
			if(quantityChk>1) {
				//장바구니에 있을 경우 update
				String sql = "update cart "
						+ "set quantity = ? "
						+ "where mem_no = ? and book_no = ? ";
				pstmt = conn.prepareStatement(sql);
				//4. binding
				pstmt.setLong(1, quantity+quantityChk.intValue());
				pstmt.setLong(2, memberNo);
				pstmt.setLong(3, bookNo);
				
				int count = pstmt.executeUpdate();
				result = count == 1;
			}else {
				//장바구니에 없을 경우 추가
				String sql = "insert into cart "
						+ "(book_no, mem_no, quantity) values ( ? , ? , ? ) ";
				pstmt = conn.prepareStatement(sql);
				//4. binding
				pstmt.setLong(1, bookNo);
				pstmt.setLong(2, memberNo);
				pstmt.setLong(3, quantity);

				int count = pstmt.executeUpdate();
				result = count == 1;
			}
			
						
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
}
