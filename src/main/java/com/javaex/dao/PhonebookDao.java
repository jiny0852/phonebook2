package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhonebookDao {

	// 필드

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private	String url = "jdbc:mysql://localhost:3306/phonebook_db";
	private String id = "phonebook";
	private String pw = "phonebook";

	// 생성자
	// 기본 생성자 사용 (그래서 생략)

	// 메서드 gs
	// 필드값을 외부에서 사용하면 안됨 (그래서 생략)
	
	// 메서드 일반

	// DB연결 메소드
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리 메소드
	private void close() {
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
	
	
	
	//////////////////////////////////////////////////
	
	//리스트 가져오기
	public List<PersonVo> getPersonList() {
		
		int count = -1;
		this.getConnection();
		
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// - sql문 준비
			String query = ""; 
			query += " select   person_id, ";
			query += " 			name, ";
			query += " 		    hp, ";
			query += " 	        company ";
			query += " from person ";
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);

			// - 실행
			rs = pstmt.executeQuery();
			
			
			// 4.결과처리
			
			// - 리스트로 만들기
			while (rs.next()) {
				
				int id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(id, name, hp, company);
				
				System.out.println(personVo);
				personList.add(personVo);
				
				count++;
				
			}
			
			System.out.println(count + "건 조회 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return personList;
		
	}
	
	
	
	

}
