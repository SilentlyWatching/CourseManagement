<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加教师信息</TITLE> 
<STYLE type=text/css>
BODY {
    	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*验证表单*/
function checkForm() {
    var teacherNumber = document.getElementById("teacher_QQ.teacherNumber").value;
    if(teacherNumber=="") {
        alert('请输入教师编号!');
        return false;
    }
    var teacherName = document.getElementById("teacher_QQ.teacherName").value;
    if(teacherName=="") {
        alert('请输入教师姓名!');
        return false;
    }
    var teacherSex = document.getElementById("teacher_QQ.teacherSex").value;
    if(teacherSex=="") {
        alert('请输入性别!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>

<BODY background="<%=basePath %>images/adminBg.jpg">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
    <s:form action="Teacher_QQ/Teacher_QQ_AddTeacher_QQ.action" method="post" id="teacher_QQAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>教师编号:</td>
    <td width=70%><input id="teacher_QQ.teacherNumber" name="teacher_QQ.teacherNumber" type="text" /></td>
  </tr>

  <tr>
    <td width=30%>教师姓名:</td>
    <td width=70%><input id="teacher_QQ.teacherName" name="teacher_QQ.teacherName" type="text" size="12" /></td>
  </tr>

  <tr>
    <td width=30%>登录密码:</td>
    <td width=70%><input id="teacher_QQ.teacherPassword" name="teacher_QQ.teacherPassword" type="text" size="30" /></td>
  </tr>

  <tr>
    <td width=30%>性别:</td>
    <td width=70%><input id="teacher_QQ.teacherSex" name="teacher_QQ.teacherSex" type="text" size="2" /></td>
  </tr>

  <tr>
    <td width=30%>出生日期:</td>
    <td width=70%><input type="text" readonly id="teacher_QQ.teacherBirthday"  name="teacher_QQ.teacherBirthday" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>入职日期:</td>
    <td width=70%><input type="text" readonly id="teacher_QQ.teacherArriveDate"  name="teacher_QQ.teacherArriveDate" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>联系邮箱:</td>
    <td width=70%><input id="teacher_QQ.teacherCardNumber" name="teacher_QQ.teacherCardNumber" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>联系电话:</td>
    <td width=70%><input id="teacher_QQ.teacherPhone" name="teacher_QQ.teacherPhone" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>教师照片:</td>
    <td width=70%><input id="teacherPhotoFile" name="teacherPhotoFile" type="file" size="50" /></td>
  </tr>

  <tr>
    <td width=30%>所在院系:</td>
    <td width=70%><input id="teacher_QQ.teacherAddress" name="teacher_QQ.teacherAddress" type="text" size="100" /></td>
  </tr>

  <tr>
    <td width=30%>附加信息:</td>
    <td width=70%><input id="teacher_QQ.teacherMemo" name="teacher_QQ.teacherMemo" type="text" size="100" /></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
