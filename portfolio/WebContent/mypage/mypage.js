function go_cart() {
	if(document.formm.quantity.value==""){
		alert("수량을 입력하세요");
		document.formm.quantity.focus();
	}else{
		document.formm.action="NonageServlet?command=cart_insert";
		document.formm.submit();
	}
}

function go_cart_delete(){
	var count=0;
	
	if(document.formm.cseq.length == underfined){
		if(document.formm.cseq.checked == true){
			count++; //항목이 같지않은 장바구니 카운트 올려주는 것
		}
	}
	
	for(var i=0; i<document.formm.cseq.length; i++){
		alert(""+document.formm.cseq[i].checked);
		if(document.formm.cseq[i].checked==true){
			count++;
			alert(""+count); //항목이 같은물건에서 올려줌
		}
	}
	
	if(count==0){ //삭제처리
		alert("삭제할 항목을 선택해주세요");
	}else{
		document.formm.action = "NonageServlet?command=cart_delete";
		document.formm.submit();
	}
}


function go_order_insert(){
	document.formm.action = "NonageServlet?command=order_insert";
	document.formm.submit();
}

function go_order_delete(){
	var count=0;
	
	if(document.formm.oseq.length==undefined){
		if(document.formm.oseq.check == true){
			count++;
		}
	}
	
	for(var i=0; i<document.formm.oseq.length; i++){
		if(document.formm.oseq[i].checked == true){
			count++;
		}
	}
	
	if(count==0){
		alert("삭제할 항목을 선택해주세요");
	}else{
		document.formm.action = "NonageServlet?command=order_delete";
		document.formm.submit();
	}	
}

function go_order(){
	document.formm.action = "NonageServlet?command=mypage";
	document.formm.submit();
}