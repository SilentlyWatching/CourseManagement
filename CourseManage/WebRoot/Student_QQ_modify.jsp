<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.shuangyulin.domain.Student_QQ" %>
<%@ page import="com.shuangyulin.domain.ClassInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的studentClassNumber信息
    List<ClassInfo> classInfoList = (List<ClassInfo>)request.getAttribute("classInfoList");
    Student_QQ student_QQ = (Student_QQ)request.getAttribute("student_QQ");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>修改学生信息</TITLE>
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
    var studentNumber = document.getElementById("student_QQ.studentNumber").value;
    if(studentNumber=="") {
        alert('请输入学号!');
        return false;
    }
    var studentName = document.getElementById("student_QQ.studentName").value;
    if(studentName=="") {
        alert('请输入姓名!');
        return false;
    }
    var studentPassword = document.getElementById("student_QQ.studentPassword").value;
    if(studentPassword=="") {
        alert('请输入登录密码!');
        return false;
    }
    var studentSex = document.getElementById("student_QQ.studentSex").value;
    if(studentSex=="") {
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
    <TD align="left" vAlign=top ><s:form action="Student_QQ/Student_QQ_ModifyStudent_QQ.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>学号:</td>
    <td width=70%><input id="student_QQ.studentNumber" name="student_QQ.studentNumber" type="text" value="<%=student_QQ.getStudentNumber() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>姓名:</td>
    <td width=70%><input id="student_QQ.studentName" name="student_QQ.studentName" type="text" size="12" value='<%=student_QQ.getStudentName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>登录密码:</td>
    <td width=70%><input id="student_QQ.studentPassword" name="student_QQ.studentPassword" type="text" size="30" value='<%=student_QQ.getStudentPassword() %>'/></td>
  </tr>

  <tr>
    <td width=30%>性别:</td>
    <td width=70%><input id="student_QQ.studentSex" name="student_QQ.studentSex" type="text" size="2" value='<%=student_QQ.getStudentSex() %>'/></td>
  </tr>

  <tr>
    <td width=30%>所在班级:</td>
    <td width=70%>
      <select name="student_QQ.studentClassNumber.classNumber">
      <%
        for(ClassInfo classInfo:classInfoList) {
          String selected = "";
          if(classInfo.getClassNumber().equals(student_QQ.getStudentClassNumber().getClassNumber()))
            selected = "selected";
      %>
          <option value='<%=classInfo.getClassNumber() %>' <%=selected %>><%=classInfo.getClassName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>出生日期:</td>
    <td width=70%><input type="text" readonly  id="student_QQ.studentBirthday"  name="student_QQ.studentBirthday" onclick="setDay(this);" value='<%=student_QQ.getStudentBirthday() %>'/></td>
  </tr>

  <tr>
    <td width=30%>政治面貌:</td>
    <td width=70%><input id="student_QQ.studentState" name="student_QQ.studentState" type="text" size="20" value='<%=student_QQ.getStudentState() %>'/></td>
  </tr>

  <tr>
    <td width=30%>学生照片:</td>
    <td width=70%><img src="<%=basePath %><%=student_QQ.getStudentPhoto() %>" width="200px" border="0px"/><br/>
    <input type="hidden" name="student_QQ.studentPhoto" value="<%=student_QQ.getStudentPhoto() %>" />
    <input id="studentPhotoFile" name="studentPhotoFile" type="file" size="50" /></td>
  </tr>
  <tr>
    <td width=30%>联系电话:</td>
    <td width=70%><input id="student_QQ.studentTelephone" name="student_QQ.studentTelephone" type="text" size="20" value='<%=student_QQ.getStudentTelephone() %>'/></td>
  </tr>

  <tr>
    <td width=30%>学生邮箱:</td>
    <td width=70%><input id="student_QQ.studentEmail" name="student_QQ.studentEmail" type="text" size="30" value='<%=student_QQ.getStudentEmail() %>'/></td>
  </tr>

  <tr>
    <td width=30%>联系qq:</td>
    <td width=70%><input id="student_QQ.studentQQ" name="student_QQ.studentQQ" type="text" size="20" value='<%=student_QQ.getStudentQQ() %>'/></td>
  </tr>

  <tr>
    <td width=30%>家庭地址:</td>
    <td width=70%><input id="student_QQ.studentAddress" name="student_QQ.studentAddress" type="text" size="100" value='<%=student_QQ.getStudentAddress() %>'/></td>
  </tr>

  <tr>
    <td width=30%>附加信息:</td>
    <td width=70%><input id="student_QQ.studentMemo" name="student_QQ.studentMemo" type="text" size="100" value='<%=student_QQ.getStudentMemo() %>'/></td>
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
