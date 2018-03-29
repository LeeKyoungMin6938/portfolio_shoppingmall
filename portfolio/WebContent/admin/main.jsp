<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="admin/css/admin.css">
<title>Insert title here</title>
<script>
	function worker_check(){
		if(document.frm.workerId.value==""){
			alert("아이디를 입력하세요");
			return false;
		}else if(document.frm.workerPwd.value==""){
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div id="wrap">
	<header>
		<div id="logo">
			<a href="admin/main.jsp">
				<img src="admin/images/bar_01.gif" style="float:left">
				<img src="admin/images/text.gif">
			</a>
		</div>
	</header>
	<div class="clear"></div>
	<article> <!-- 아이디,비밀번호 -->
		<div id="loginform">
			<form name="frm" method="post" action="NonageServlet?command=admin_login">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="workerId" size="10" value="admin"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="workerPwd" size="10"></td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input class="btn" type="submit" value="업무 로그인" onclick="return worker_check()"><br><br>
							<h4 style="color:red">${message }</h4>							
						</td>
					</tr>
				</table>
			</form>
		</div>
	</article>
</div>
</body>
</html>