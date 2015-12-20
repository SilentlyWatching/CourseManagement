<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>教务报课管理系统登录</title>
</head>




<body  topmargin="150" leftmargin="100">
<center>
<h1>报课系统登录界面</h1>
<br>
<br>
<link href="css/common1.css"type="text/css"rel="stylesheet">
<style type="text/css">
<!--
body {
background-image: url(<%=basePath %> images/fuda.jpg);
background-repeat: no-repeat;
background-size: 100%;
}
-->
table{
text-align: center
}
.textSize{
width: 120px;
height: 15px
}
</style>
<script type="text/javascript">
//确定按钮
function gogo(){
	document.forms[0].submit();
}
//取消按钮
function cancel(){
	document.form[0].action="";
}
//忘记密码？
function forget(){
	document.form[0].submit();
}
</script>
</head>
<body>
<form action="<%=basePath %>login/login_CheckLogin.action" method="post" name="form1">
<table>
<tr>
<td colspan="2"><img src="<%=basePath %>images/login.gif" width="100" height="100"></td>
</tr>
<tr>
<td>姓名：</td>
<td><input class="textSize"type="text"name="admin.username">
</tr>
<tr>
<td>密码：</td>
<td><input class="textSize"type="password"name="admin.password">
</tr>
<tr>
<td colspan="2">
<br>
<input type="image"src="<%=basePath %>images/dl.gif" width="50" height="18" onclick="gogo()">
<input type="image"src="<%=basePath %>images/cancel.jpg" width="50" height="18" onclick="cancel()">
<br>
<br>
<a href="foget.html"> <font size="1" color="blue" onclick="forget()">忘记密码？</font > </a>

</td>
</tr>
</table>
</form>
</center>
</body>








<%-- 

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>教务选课管理系统登录</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
-->
</style></head>

<body>
<s:fielderror cssStyle="color:red" />
<form action="<%=basePath %>login/login_CheckLogin.action" method="post" name="form1">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="<%=basePath %>images/login_03.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" background="<%=basePath %>images/login_05.gif">&nbsp;</td>
            <td width="206" background="<%=basePath %>images/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16%" height="25"><div align="right"><span class="STYLE1">用户</span></div></td>
                <td width="57%" height="25"><div align="center">
                  <input type="text" name="admin.username" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td width="27%" height="25">&nbsp;</td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" name="admin.password" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td height="25"><div align="left"><img style="cursor:hand;" onclick="document.forms[0].submit();" src="<%=basePath %>images/dl.gif" width="49" height="18" border="0"></div></td>
              </tr>
            </table></td>
            <td width="362" background="<%=basePath %>images/login_07.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="213" background="<%=basePath %>images/login_08.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
 
 

</body>
</html>
--%>
