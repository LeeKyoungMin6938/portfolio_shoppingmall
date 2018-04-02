package com.nonage.dto;

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
	
	
	
	public int workerCheck(String userid,String userpw) {
		String sql = "select pwd from worker where id=?";
		int result = -1;
	
		Connection conn = null;
	
		PreparedStatement pstmt = null;
	
		ResultSet rs = null;	
		try {
	
			conn = DBManager.getConnection();
	
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, userid);
	
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				result = 0; 
				String dbPwd = rs.getString(1); 
				
				if (dbPwd.equals(userpw)) {
					result = 1; 
				}
			}
			DBManager.close(conn, pstmt, rs);
		} catch (Exception e) {
		}
		return result;
	}
}
