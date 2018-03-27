package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.OrderDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

public class OrderDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/orderDetail.jsp";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			url="NonageServlet?command=login_form";
		}else {
			//주문번호 찾고 oseq에 담기
			int oseq=Integer.parseInt(request.getParameter("oseq").trim());
			//주문DAO 객체생성
			OrderDAO orderDAO= OrderDAO.getInstance();
			//db에서 listorderbyid 함수를 통해서 로그인유저의 주문번호에 대한 리스트를 다 가져온다.
			ArrayList<OrderVO> orderList = orderDAO.listOrderById(loginUser.getId(), "%", oseq);
			
			//총가격구하기
			int totalPrice=0;
			for(OrderVO ovo : orderList) {
				totalPrice+=ovo.getPrice2()*ovo.getQuantity();
			}
			
			request.setAttribute("orderDetail", orderList.get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);			
		}
		request.getRequestDispatcher(url).forward(request, response);		
	}

}
