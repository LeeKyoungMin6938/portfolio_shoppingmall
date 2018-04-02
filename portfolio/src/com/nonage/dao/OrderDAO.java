package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

import utill.DBManager;

public class OrderDAO {
	private OrderDAO() {
		
	}
	
	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance() {
		return instance;
	}
	
	//사용자가 주문
	public int insertOrder(ArrayList<CartVO> cartList,String id) {
		int maxOseq = 0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs;
		
		try {
			conn=DBManager.getConnection();
			// 주문테이블 oseq 주문번호
			// id 주문자 아이디
			// indatte 주문일
			// 홍길동 1 주문번호.   one 2 주문번호. 3 4 5(가장최신주문번호).
			// 최신주문 6번으로처리
			String selectMaxOseq = "select max(oseq) from orders"; //가장높은숫자 찾는 쿼리
			pstmt=conn.prepareStatement(selectMaxOseq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				maxOseq = rs.getInt(1);
			}
			pstmt.close();
			
			
			//order_detail 주문상세테이블
			//주문상세번호  주문번호  상품번호  수량  처리여부
			String insertOrder = "insert into orders(oseq, id) values (orders_seq.nextval , ?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			for(CartVO cartVO : cartList) {
				insertOrderDetail(cartVO, maxOseq);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return maxOseq;
	}
	
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Connection conn=null;
		PreparedStatement pstmt =null;
		
		try {
			conn=DBManager.getConnection();
			
			String insertOrderDetail = "insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, maxOseq);
			pstmt.setInt(2, cartVO.getPseq());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.executeUpdate();
			pstmt.close();
			//카트 테이블에 (result) 결과처리여부     1.미처리 2.처리
			String updateCartResult = "update cart set result=2 where cseq=?";
			pstmt = conn.prepareStatement(updateCartResult);
			pstmt.setInt(1, cartVO.getCseq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn,pstmt);
		}
	}
	
	
	//주문내역을 가져오는함수
	public ArrayList<OrderVO> listOrderById(String id, String result, int oseq){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		
		String sql = "select * from order_view where id=? and result like '%'||?||'%' and oseq=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, result);
			pstmt.setInt(3, oseq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOdseq(rs.getInt("ODSEQ"));
				orderVO.setOseq(rs.getInt("OSEQ"));
				orderVO.setId(rs.getString("ID"));
				orderVO.setIndate(rs.getTimestamp("INDATE"));
				orderVO.setMname(rs.getString("MNAME"));
				orderVO.setZipNum(rs.getString("ZIP_NUM"));
				orderVO.setAddress(rs.getString("ADDRESS"));
				orderVO.setPhone(rs.getString("PHONE"));
				orderVO.setPseq(rs.getInt("PSEQ"));
				orderVO.setQuantity(rs.getInt("QUANTITY"));
				orderVO.setPname(rs.getString("PNAME"));
				orderVO.setPrice2(rs.getInt("PRICE2"));
				orderVO.setResult(rs.getString("RESULT"));
				orderList.add(orderVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}

	//현재 진행중인 주문내역 조회
	public ArrayList<Integer> selectSeqOrderIng(String id) {
		ArrayList<Integer> oseqList = new ArrayList<Integer>();
		//distinct : 중복제거
		String sql = "select distinct oseq from order_view where id=? and result='1' order by oseq desc";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				oseqList.add(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con,pstmt,rs);
		}
		return oseqList;
	}

	
	//관리자 모드에서 사용되는 메소드
	//조회
	public ArrayList<OrderVO> listOrder(String member_name) {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		String sql = "select * from order_view where mname like '%'||?||'%' "+"order by result, oseq desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(member_name=="") {
				pstmt.setString(1, "%");			
			}else {
				pstmt.setString(1, member_name);
			}
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOdseq(rs.getInt("ODSEQ"));
				orderVO.setOseq(rs.getInt("OSEQ"));
				orderVO.setId(rs.getString("ID"));
				orderVO.setPseq(rs.getInt("PSEQ"));
				orderVO.setMname(rs.getString("MNAME"));
				orderVO.setPname(rs.getString("PNAME"));
				orderVO.setQuantity(rs.getInt("QUANTITY"));
				orderVO.setZipNum(rs.getString("ZIP_NUM"));
				orderVO.setAddress(rs.getString("ADDRESS"));
				orderVO.setPhone(rs.getString("PHONE"));
				orderVO.setIndate(rs.getTimestamp("INDATE"));
				orderVO.setPrice2(rs.getInt("PRICE2"));
				orderVO.setResult(rs.getString("RESULT"));
				orderList.add(orderVO);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return orderList;
	}
	
	
	//주문 미처리(1) 처리(2)
	//oseq 주문번호 , 주문시퀀스로 증가, foreign key
	//odseq 주문상세번호 , 시퀀스로 증가
	public void updateOrderResult(String oseq) {
		String sql = "update order_detail set result='2' where odseq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oseq);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
			
		}
	}
	
	
	
	
	
}