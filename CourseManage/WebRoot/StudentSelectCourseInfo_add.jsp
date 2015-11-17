<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.shuangyulin.domain.Student_QQ" %>
<%@ page import="com.shuangyulin.domain.CourseInfo_QQ" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的studentNumber信息
    List<Student_QQ> student_QQList = (List<Student_QQ>)request.getAttribute("student_QQList");
    //获取所有的courseNumber信息
    List<CourseInfo_QQ> courseInfo_QQList = (List<CourseInfo_QQ>)request.getAttribute("courseInfo_QQList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加选课信息</TITLE> 
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
    <s:form action="StudentSelectCourseInfo/StudentSelectCourseInfo_AddStudentSelectCourseInfo.action" method="post" id="studentSelectCourseInfoAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>学生对象:</td>
    <td width=70%>
      <select name="studentSelectCourseInfo.studentNumber.studentNumber">
      <%
        for(Student_QQ student_QQ:student_QQList) {
      %>
          <option value='<%=student_QQ.getStudentNumber() %>'><%=student_QQ.getStudentName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>课程对象:</td>
    <td width=70%>
      <select name="studentSelectCourseInfo.courseNumber.courseNumber">
      <%
        for(CourseInfo_QQ courseInfo_QQ:courseInfo_QQList) {
      %>
          <option value='<%=courseInfo_QQ.getCourseNumber() %>'><%=courseInfo_QQ.getCourseName() %></option>
      <%
        }
      %>
    </td>
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
