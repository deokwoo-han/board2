package com.hancompany.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hancompany.board.dto.BDto;

public class BDao {
	
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/webdb";
	static String user = "root";
	static String password = "12345";
	
	public void write(String bid, String bname, String btitle, String bcontent) {
	
		String sql = "INSERT INTO jsp_board(bid, bname, btitle, bcontent, bhit) VALUES('" + bid + "','" + bname + "','" + btitle + "','" + bcontent + "',0)";
		
		int dbFlag = 0;//받아도 그만, 안 받아도 그만
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
	}//리턴 없애기
	
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		String sql = "SELECT * FROM jsp_board";		
			
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		ResultSet rs = null;
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String bid = rs.getString("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				int bhit = rs.getInt("bhit");
				
				BDto dto = new BDto(bid, bname, btitle, bcontent, bhit);
				dtos.add(dto);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}	
		
		return dtos;
	}
	
	public BDto content_view(String strid) {
		
		upHit(strid);//실행될 때마다 조회수가 1 증가하는함수 hit();
		
		BDto dto = null;
		
		String sql = "SELECT * FROM jsp_board WHERE bid = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		ResultSet rs = null;
	
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			
			rs = pstmt.executeQuery();//하나밖애 안돌아가니 if 문으로 while문 대체해도 무버ㅏㅇ
			
			if(rs.next()) {
				String bid = rs.getString("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				int bhit = rs.getInt("bhit");
				
				dto = new BDto(bid, bname, btitle, bcontent, bhit);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}//가입이 되어 있으면 1이 반환, 아니면 0이 반환
		
		return dto;
	}
	
	public void modify(String bid, String bname, String btitle, String bcontent) {
		
		String sql = "UPDATE jsp_board SET bname = ?, btitle = ?, bcontent = ? WHERE bid = ?";
		
		int dbFlag = 0;//받아도 그만, 안 받아도 그만
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체
		
		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);
			
			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
	}//리턴 없애기
	
	
	public void delete(String bid) {

		String sql = "DELETE FROM jsp_board WHERE bid=?";

		int dbFlag = 0;		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체

		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bid);			

			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			

		}	

	}
	
	private void upHit(String bid) {

		String sql = "UPDATE jsp_board SET bhit = bhit + 1 WHERE bid = ?";

		int dbFlag = 0;		
		Connection conn = null;
		PreparedStatement pstmt = null;//sql 실행 객체

		try {
			Class.forName(driverName);//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(url, user, password);//DB 연동			
			pstmt = conn.prepareStatement(sql);			

			pstmt.setString(1, bid);

			dbFlag = pstmt.executeUpdate();//sql실행->실행 성공시 1 반환

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			

		}		
	}

}