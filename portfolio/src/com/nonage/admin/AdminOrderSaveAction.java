package com.nonage.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.OrderDAO;

public class AdminOrderSaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 리스트
		String url="NonageServlet?command=admin_order_list";
		
		//미처리, 처리 결과값을 여러개 담아온다
		String[] resultArr = request.getParameterValues("result");
		
		//여러개의 디비처리
		for(String oseq:resultArr) {
			System.out.println(oseq);
			OrderDAO orderDAO = OrderDAO.getInstance();
			orderDAO.updateOrderResult(oseq);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
