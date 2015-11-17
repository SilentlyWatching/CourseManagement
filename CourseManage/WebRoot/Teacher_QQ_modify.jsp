<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.shuangyulin.domain.Teacher_QQ" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Teacher_QQ teacher_QQ = (Teacher_QQ)request.getAttribute("teacher_QQ");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸Ľ�ʦ��Ϣ</TITLE>
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
    var teacherNumber = document.getElementById("teacher_QQ.teacherNumber").value;
    if(teacherNumber=="") {
        alert('�������ʦ���!');
        return false;
    }
    var teacherName = document.getElementById("teacher_QQ.teacherName").value;
    if(teacherName=="") {
        alert('�������ʦ����!');
        return false;
    }
    var teacherSex = document.getElementById("teacher_QQ.teacherSex").value;
    if(teacherSex=="") {
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
    <TD align="left" vAlign=top ><s:form action="Teacher_QQ/Teacher_QQ_ModifyTeacher_QQ.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>��ʦ���:</td>
    <td width=70%><input id="teacher_QQ.teacherNumber" name="teacher_QQ.teacherNumber" type="text" value="<%=teacher_QQ.getTeacherNumber() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>��ʦ����:</td>
    <td width=70%><input id="teacher_QQ.teacherName" name="teacher_QQ.teacherName" type="text" size="12" value='<%=teacher_QQ.getTeacherName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��¼����:</td>
    <td width=70%><input id="teacher_QQ.teacherPassword" name="teacher_QQ.teacherPassword" type="text" size="30" value='<%=teacher_QQ.getTeacherPassword() %>'/></td>
  </tr>

  <tr>
    <td width=30%>�Ա�:</td>
    <td width=70%><input id="teacher_QQ.teacherSex" name="teacher_QQ.teacherSex" type="text" size="2" value='<%=teacher_QQ.getTeacherSex() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input type="text" readonly  id="teacher_QQ.teacherBirthday"  name="teacher_QQ.teacherBirthday" onclick="setDay(this);" value='<%=teacher_QQ.getTeacherBirthday() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��ְ����:</td>
    <td width=70%><input type="text" readonly  id="teacher_QQ.teacherArriveDate"  name="teacher_QQ.teacherArriveDate" onclick="setDay(this);" value='<%=teacher_QQ.getTeacherArriveDate() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��ϵ����:</td>
    <td width=70%><input id="teacher_QQ.teacherCardNumber" name="teacher_QQ.teacherCardNumber" type="text" size="20" value='<%=teacher_QQ.getTeacherCardNumber() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��ϵ�绰:</td>
    <td width=70%><input id="teacher_QQ.teacherPhone" name="teacher_QQ.teacherPhone" type="text" size="20" value='<%=teacher_QQ.getTeacherPhone() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��ʦ��Ƭ:</td>
    <td width=70%><img src="<%=basePath %><%=teacher_QQ.getTeacherPhoto() %>" width="200px" border="0px"/><br/>
    <input type="hidden" name="teacher_QQ.teacherPhoto" value="<%=teacher_QQ.getTeacherPhoto() %>" />
    <input id="teacherPhotoFile" name="teacherPhotoFile" type="file" size="50" /></td>
  </tr>
  <tr>
    <td width=30%>����Ժϵ:</td>
    <td width=70%><input id="teacher_QQ.teacherAddress" name="teacher_QQ.teacherAddress" type="text" size="100" value='<%=teacher_QQ.getTeacherAddress() %>'/></td>
  </tr>

  <tr>
    <td width=30%>������Ϣ:</td>
    <td width=70%><input id="teacher_QQ.teacherMemo" name="teacher_QQ.teacherMemo" type="text" size="100" value='<%=teacher_QQ.getTeacherMemo() %>'/></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='����' >
        &nbsp;&nbsp;
        <input type="reset" value='��д' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
