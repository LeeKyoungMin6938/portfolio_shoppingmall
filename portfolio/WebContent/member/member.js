function go_save(){
	if(document.formm.id.value==""){
		alert("아이디를 입력해주세요");
		document.formm.id.focus();
	}else if(document.formm.id.value != document.formm.reid.value){
		alert("중복확인을 클릭해주세요");
		document.formm.id.focus();
	}else if(document.formm.pwd.value == ""){
		alert("비밀번호를 입력해주세요");
		document.formm.pwd.focus();
	}else if((document.formm.pwd.value != document.formm.pwdCheck.value)){
		alert("비밀번호가 일치하지 않습니다");
		document.formm.pwd.focus();
	}else if(document.formm.name.value == ""){
		alert("이름을 입력해주세요");
		document.formm.name.focus();
	}else if(document.formm.email.value == ""){
		alert("이메일을 입력해주세요");
		document.formm.email.focus();
	}else{ // 회원가입 조건이 충족됐을경우
		document.formm.action = "NonageServlet?command=join";
		document.formm.submit();
	}
}


 function idcheck(){
	 if(document.formm.id.value == ""){
		 alert("아이디를 입력해주세요");
		 document.formm.id.focus();
		 return;
	 }
	 var url="NonageServlet?command=id_check_form&id="+document.formm.id.value;
	 window.open(url, "_blank_1","toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200");
 }
 
 function post_zip(){ // 우편번호
	 var url = "NonageServlet?command=find_zip_num";
	 window.open(url,"_blank_1","toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=300, top=300, left=300");
 }
 
 function go_next(){ // 회원가입할 때 동의
	 if(document.formm.okon1[0].checked==true){
		 document.formm.action = "NonageServlet?command=join_form";
		 document.formm.submit();
	 }else if(document.formm.okon1[1].checked==true){
		 alert("약관에 동의하셔야 합니다");
	 }
 }