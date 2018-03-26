package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

public class ProductKindAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="product/productKind.jsp";
		String kind = request.getParameter("kind").trim();
		
		ProductDAO productDAO = ProductDAO.getInstance(); //dao 객체가져오기
		ArrayList<ProductVO> productKindList = productDAO.listKindProduct(kind); 
		// 어레이리스트에 kind에 담아져서 보내진항목을 쿼리를돌려서 해당항목을db에서 조회, productKindList에 담아줌 
		request.setAttribute("productKindList", productKindList); // 위에서 담아진 arraylist를 productKindList 에 담아서
		//이동할 페이지로 넘겨준다.
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//productKind.jsp 로 데이터들을 넘겨준다
		dispatcher.forward(request, response);
		
	}
	
}
