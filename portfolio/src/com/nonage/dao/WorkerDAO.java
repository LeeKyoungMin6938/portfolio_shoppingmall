package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utill.DBManager;

public class WorkerDAO {

	private WorkerDAO() {
		
	}
	
	private static WorkerDAO instance = new WorkerDAO();
	
	public static WorkerDAO getInstance() {
		return instance;
	}
	
	//사용자 인증을 위한 메소드 
	//-1:아이디존재하지않음
	//0: 아이디 존재하지만 비밀번호 불일치
	//1: 아이디와 비밀번호 모두 일치
	//login.jsp -> workerCheck.jsp
	
	//아이디를 검색조건으로 주어서 비밀번호를 얻어온다
	public int workerCheck(String userid, String userpw) {
		String sql = "select pwd from worker where id=?";
		int result=-1; //틀렸을때를 대비해서 기본 -1
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과값을 저장할때씀
		try {
			conn=DBManager.getConnection(); //db연결
			pstmt = conn.prepareStatement(sql); //db sql 수행
			pstmt.setString(1, userid); // ? 값넣어줌
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //전달인자로 준 아이디와 일치하는 행이존재한다면,
				result=0; // 등록된 관리자
				String dbPwd = rs.getString(1); // db에 저장된 비밀번호
				if(dbPwd.equals(userpw)) { //db에 저장된 비밀번호와 입력된 비밀번호가 일치한다면,
					result=1;
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);			
		}
		return result;
		
	}
}
