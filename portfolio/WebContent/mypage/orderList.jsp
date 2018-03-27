<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp" %>
<%@ include file="sub_img.html" %>
<%@ include file="sub_menu.jsp" %>

<article>
	<h2>주문내역</h2>
	<form name="formm" method="post">
		<table id="cartList">
			<tr>
				<th>상품명</th>
				<th>수량</th>
				<th>가격</th>
				<th>주문일</th>
				<th>진행상태</th>				
			</tr>
			<c:forEach items="${orderList}" var="orderVO">
				<tr>
					<td>
						<a href="NonageServlet?command=product_detail&pseq=${cartVO.pseq }"><h3>
						${orderVO.pname}</h3></a>
					</td>
					
					<td>
						${orderVO.quantity} <!-- 수량 -->
					</td>
					<td> <!-- 가격.   currency ='통화' -->
						<fmt:formatNumber value="${orderVO.price2*orderVO.quantity}" type="currency"></fmt:formatNumber>
					</td>
					<td> <!-- 날짜 -->
						<fmt:formatDate value="${orderVO.indate }" type="date"></fmt:formatDate>
					</td>
					<td>
						처리 진행 중
					</td>
				</tr>
			</c:forEach>
			<tr> <!-- 총 결제금액 -->
				<th colspan="2">총액</th>
				<th colspan="2"><fmt:formatNumber value="${totalPrice }"></fmt:formatNumber>
				<br></th>
				<th>주문 처리가 완료되었습니다.</th>
			</tr>
		</table>
		<div class="clear"></div>
		<div id="buttons" style="float:right">
			<input type="button" value="쇼핑계속하기" class="cancel" onclick="location.href='NonageServlet?command=index'">
		</div>
	</form>
</article><%@ include file="../footer.jsp" %>