package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.ProductVO;

import utill.DBManager;

public class ProductDAO {
	// price1 = 원가 2=판매가 3=판매가-원가
	private ProductDAO() {

	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	// 신상품 리스트 얻어오기

	public ArrayList<ProductVO> listNewProduct() {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from new_pro_view";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2")); // 판매가
				product.setImage(rs.getString("image"));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return productList;
	}

	// 베스트 상품 리스트 얻어오기 . 신상품과 비슷
	public ArrayList<ProductVO> listBestProduct() {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from best_pro_view";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2")); // 판매가
				product.setImage(rs.getString("image"));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return productList;
	}

	// 상품번호로 상품 정보 한개 얻어오기
	public ProductVO getProduct(String pseq) {
		ProductVO product = null;
		String sql = "select * from product where pseq=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setKind(rs.getString("kind"));
				product.setPrice1(rs.getInt("price1"));
				product.setPrice2(rs.getInt("price2"));
				product.setPrice3(rs.getInt("price3"));
				product.setContent(rs.getString("content"));
				product.setImage(rs.getString("image"));
				product.setUseyn(rs.getString("useyn"));
				product.setBestyn(rs.getString("bestyn"));
				product.setIndate(rs.getTimestamp("indate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return product;
	}

	// 상품 종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from product where kind=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2"));
				product.setImage(rs.getString("image"));
				productList.add(product);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return productList;
	}

	//
	
	
	
}
