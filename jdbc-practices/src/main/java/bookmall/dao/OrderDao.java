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

public class OrderDao {
	public List<Map<String, Object>> selectMemOrderList(long memberNo) {
		List<Map<String,Object>> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();
			
			//3. SQL 준비
			String sql = "select no, total_price, address"
					+ " from orders"
					+ " where mem_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, memberNo);

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				long orderNo = rs.getLong(1);
				long totalPrice = rs.getLong(2);
				String address = rs.getString(3);
				
				map.put("orderNo", orderNo);
				map.put("totalPrice", totalPrice);
				map.put("address", address);
				
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

	public boolean cancelMemOrderList(long memberNo, long orderNo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmtMem = null;
		PreparedStatement pstmtBook = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
			
			String sql = "delete from orders "
					+ "where mem_no = ? and book_no = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, orderNo);
			
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
	
	public boolean orderPersonal(long memberNo, long totalPrice, String address) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
					
			//장바구니에 있을 경우 update
			String sql = "insert into orders (mem_no,total_price,address)"
					+ " values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, totalPrice);
			pstmt.setString(3, address);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
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
		return result;
	}
	
	public long findOrderNum(long memberNo, long totalPrice, String address) {
		long result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
					
			//장바구니에 있을 경우 update
			String sql = "select count(*) from orders "
					+ "where mem_no = ? and total_price = ? and address = ?";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, totalPrice);
			pstmt.setString(3, address);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getLong(1);
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
		return result;
	}

	public boolean insertOrderBook(long findOrderNum, long bookNo, long bookQuantity) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCnt = null;
		PreparedStatement pstmtUpdate = null;

		ResultSet rs = null;

		try {
			//3. SQL 준비
			conn=getConnection();
			long countHas = 0L;
			String sql = "select count(*) from order_book where book_no =?";
			pstmtCnt = conn.prepareStatement(sql);
			
			pstmtCnt.setLong(1,bookNo);
			
			rs = pstmtCnt.executeQuery();
			if(rs.next()) {
				countHas = rs.getLong(1);
			}
			
			if(countHas!=0) {
				//이미 책 주문목록에 있을 경우 update
				String sql2 = "update order_book "
						+ "set quantity = ? "
						+ " where book_no = ? ";
				pstmtUpdate=conn.prepareStatement(sql2);
				pstmtUpdate.setLong(1, (countHas+bookQuantity));
				pstmtUpdate.setLong(2, bookNo);
				int count = pstmtUpdate.executeUpdate();
				result = count ==1;
			}else {
				//없을 경우 insert
				String sql3 = "insert into order_book (no,book_no,quantity)"
						+ " values (?,?,?)";
				pstmt = conn.prepareStatement(sql3);
				//4. binding
				pstmt.setLong(1, findOrderNum);
				pstmt.setLong(2, bookNo);
				pstmt.setLong(3, bookQuantity);
				
				int count = pstmt.executeUpdate();
				result = count == 1;
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
		return result;
	}
	
	public List<Map<String, Object>> selectBookOrderList() {
		List<Map<String,Object>> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();
			
			//3. SQL 준비
			String sql = "select * from order_book";
			pstmt = conn.prepareStatement(sql);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				long orderNo = rs.getLong(1);
				long bookNo = rs.getLong(2);
				long quantity= rs.getLong(3);
				
				map.put("orderNo", orderNo);
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

	private Connection getConnection() throws SQLException {
		Connection conn=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.92:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		return conn;
	}


}
