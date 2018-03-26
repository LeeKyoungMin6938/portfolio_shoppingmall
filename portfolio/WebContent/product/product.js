function go_cart(){
	if(document.formm.quantity.value==""){
		alert("수량을 입력해주세요");
		document.formm.quantity.focus();
	} else {
		document.formm.action = "NonageServlet?command=cart_insert";
		document.formm.submit();
	}
}




function go_order(){
	document.formm.action = "NonageServlet?command=mypage";
	document.formm.submit();
}