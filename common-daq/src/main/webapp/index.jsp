<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="form1" method="post" action=""> 
<center> 
<p><font size="5" color="#0000FF" face="华文细黑">您在本站已停留：</font></p> 
<p> 
<input name="textarea" type="text" value=""> 
</p> 
</center> 
</form> 
<center>this app-server is running...</center>
</body>
<script type="text/javascript">
if(document.referrer){
	document.write("您来自" + document.referrer);
}var second=0; 
var minute=0; 
var hour=0; 
window.setTimeout("interval();",1000); 
function interval() 
{ 
second++; 
if(second==60) 
{ 
second=0;minute+=1; 
} 
if(minute==60) 
{ 
minute=0;hour+=1; 
} 
document.form1.textarea.value = hour+"时"+minute+"分"+second+"秒"; 
window.setTimeout("interval();",1000); 
} 
</script>
</html>