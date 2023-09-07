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
}
