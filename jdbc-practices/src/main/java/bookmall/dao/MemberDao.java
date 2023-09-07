package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

public class MemberDao {

	public List<MemberVo> select() {
		List<MemberVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setPhone(phone);
				
				
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

	public void insert(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "insert into"
			+" member (name, phone, email, password )"
			+ " values ( ? , ? , ? , ? ) ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
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

	public void updatePW(String phone, String email,String pwBefore, String pwAfter) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "update member "
					+ " set password = ?"
					+ "where phone = ? and email = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, pwAfter);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, pwBefore);

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
	
	public void delete(String MemberName, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();

			//3. SQL 준비
			String sql = "delete from member where name = ?"
					+ " and email = ? ";
			pstmt = conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, MemberName);
			pstmt.setString(2, email);
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
	
	public static long findMemberNo(String email, String password) {
		long memberNo = 0L;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();

			String findMemNoSql = "select no from member "
					+ "where email = '"+email+"' and password = '"+password+"'";
			pstmt = conn.prepareStatement(findMemNoSql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberNo=rs.getLong(1);
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
		return memberNo;
	}
	public static String findMemberName(String email, String password) {
		String memberName = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=getConnection();

			String findMemNoSql = "select name from member "
					+ "where email = '"+email+"' and password = '"+password+"'";
			pstmt = conn.prepareStatement(findMemNoSql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memberName=rs.getString(1);
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
		return memberName;
	}
	private static Connection getConnection() throws SQLException {
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
