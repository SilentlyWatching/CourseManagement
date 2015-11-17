<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.shuangyulin.domain.CourseInfo_QQ" %>
<%@ page import="com.shuangyulin.domain.Teacher_QQ" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的courseTeacher信息
    List<Teacher_QQ> teacher_QQList = (List<Teacher_QQ>)request.getAttribute("teacher_QQList");
    CourseInfo_QQ courseInfo_QQ = (CourseInfo_QQ)request.getAttribute("courseInfo_QQ");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>修改课程信息</TITLE>
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
    var courseNumber = document.getElementById("courseInfo_QQ.courseNumber").value;
    if(courseNumber=="") {
        alert('请输入课程编号!');
        return false;
    }
    var courseName = document.getElementById("courseInfo_QQ.courseName").value;
    if(courseName=="") {
        alert('请输入课程名称!');
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
    <TD align="left" vAlign=top ><s:form action="CourseInfo_QQ/CourseInfo_QQ_ModifyCourseInfo_QQ.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>课程编号:</td>
    <td width=70%><input id="courseInfo_QQ.courseNumber" name="courseInfo_QQ.courseNumber" type="text" value="<%=courseInfo_QQ.getCourseNumber() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>课程名称:</td>
    <td width=70%><input id="courseInfo_QQ.courseName" name="courseInfo_QQ.courseName" type="text" size="20" value='<%=courseInfo_QQ.getCourseName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>上课老师:</td>
    <td width=70%>
      <select name="courseInfo_QQ.courseTeacher.teacherNumber">
      <%
        for(Teacher_QQ teacher_QQ:teacher_QQList) {
          String selected = "";
          if(teacher_QQ.getTeacherNumber().equals(courseInfo_QQ.getCourseTeacher().getTeacherNumber()))
            selected = "selected";
      %>
          <option value='<%=teacher_QQ.getTeacherNumber() %>' <%=selected %>><%=teacher_QQ.getTeacherName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>起讫周序:</td>
    <td width=70%><input id="courseInfo_QQ.courseTime" name="courseInfo_QQ.courseTime" type="text" size="40" value='<%=courseInfo_QQ.getCourseTime() %>'/></td>
  </tr>

  <tr>
    <td width=30%>选修类型:</td>
    <td width=70%><input id="courseInfo_QQ.coursePlace" name="courseInfo_QQ.coursePlace" type="text" size="40" value='<%=courseInfo_QQ.getCoursePlace() %>'/></td>
  </tr>

  <tr>
    <td width=30%>课程学分:</td>
    <td width=70%><input id="courseInfo_QQ.courseScore" name="courseInfo_QQ.courseScore" type="text" size="8" value='<%=courseInfo_QQ.getCourseScore() %>'/></td>
  </tr>

  <tr>
    <td width=30%>课程学时:</td>
    <td width=70%><input id="courseInfo_QQ.courseMemo" name="courseInfo_QQ.courseMemo" type="text" size="100" value='<%=courseInfo_QQ.getCourseMemo() %>' style="width: 69px; "/></td>
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
