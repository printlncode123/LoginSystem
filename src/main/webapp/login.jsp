<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<form action="login" method="post">
		用户名:<input type="text" name="username"><br>
		密    码:<input type="password" name="password"><br>
		<!-- <input name="tokenValidate" type="hidden" id="getToken"> -->
		<input type="submit" value="登录" id="loginBtn">
		<input type="reset" value="重置">
	</form>
	
	<%-- <input type="hidden" value=${tokenError } id="tokenError"> --%>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
var token_value="";
$(function(){
	/* $("#getToken").val(getCookie()); */
	console.log(getCookie());
	
});
function getCookie()
{
	/* 
	var token_keyVal;
	var cookieArr=document.cookie.split(";");
	if(cookieArr.length>1){
		for(var i=0;i<cookieArr.length;i++){
			if(cookieArr[i].indexOf("tokenVal")>-1){
				token_keyVal=cookieArr[i];
				break;
			}
		}
		var token_name=token_keyVal.split("=")[0];//tokenVal
		if(token_name=="tokenVal"){
			token_value=token_keyVal.split("=")[1];	
		}
	}
	return token_value; */
}

</script>
</html>