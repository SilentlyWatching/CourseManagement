<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.shuangyulin.domain.ClassInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�studentClassNumber��Ϣ
    List<ClassInfo> classInfoList = (List<ClassInfo>)request.getAttribute("classInfoList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>���ѧ����Ϣ</TITLE> 
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
/*��֤��*/
function checkForm() {
    var studentNumber = document.getElementById("student_QQ.studentNumber").value;
    if(studentNumber=="") {
        alert('������ѧ��!');
        return false;
    }
    var studentName = document.getElementById("student_QQ.studentName").value;
    if(studentName=="") {
        alert('����������!');
        return false;
    }
    var studentPassword = document.getElementById("student_QQ.studentPassword").value;
    if(studentPassword=="") {
        alert('�������¼����!');
        return false;
    }
    var studentSex = document.getElementById("student_QQ.studentSex").value;
    if(studentSex=="") {
        alert('�������Ա�!');
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
    <s:form action="Student_QQ/Student_QQ_AddStudent_QQ.action" method="post" id="student_QQAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  

  <tr>
    <td width=30%>�γ��ļ��ϴ�</td>
    <td width=70%><input id="studentPhotoFile" name="studentPhotoFile" type="file" size="50" /></td>
  </tr>

  

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='ȷ��' >
        &nbsp;&nbsp;
        <input type="reset" value='ȡ��' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
