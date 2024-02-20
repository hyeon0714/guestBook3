package com.javaex.gbDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.gbVo.GbVo;

public class GbDao {
	
	
	public int gbinsert(GbVo gv) {
		int count=-1;//결과내용 출력을 위한 변수 지정
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//sql문 준비
			String query ="";
			query+=" insert into guest ";
			query+=" value(null, ?, ?, ?, date(now())); ";
			
			//바인딩
			pstmt =  conn.prepareStatement(query);
			pstmt.setString(1, gv.getName());
			pstmt.setString(2, gv.getPassword());
			pstmt.setString(3, gv.getContent());
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리(콘솔에서 보기위해)
			
			System.out.println(count+"건 등록되었습니다");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}
			}
		return count;//정상 처리시 처리된 카운트 값 출력
	}
	
	public List<GbVo> gblist() {
		List<GbVo> gList = new ArrayList<GbVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" select	no, ";
			query+=" 		name, ";
			query+="        password, ";
			query+="        content, ";
			query+="        reg_date ";
			query+=" from guest ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
				
				System.out.print(no);
				System.out.print(name);
				System.out.print(password);
				System.out.print(content);
				System.out.println(reg_date);
				
				GbVo gv = new GbVo(no, name, password, content, reg_date);
				
				gList.add(gv);
				
				System.out.println(gv);
				
			}
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return gList;
	}

	public int gbdelete(int no, String pw) {
		
		int count = -1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" delete from guest ";
			query+=" where 	no = ? ";
			query+=" and	password = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, pw);
			
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count+"건 등록되었습니다");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}	
		return count;
	}
	
}
