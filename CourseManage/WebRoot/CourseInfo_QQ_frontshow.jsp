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

%>
<HTML><HEAD><TITLE>查看课程信息</TITLE>
<STYLE type=text/css>
body{margin:0px; font-size:12px; background-image:url(<%=basePath%>images/bg.jpg); background-position:bottom; background-repeat:repeat-x; background-color:#A2D5F0;}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
</HEAD>
<BODY><br/><br/>
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3'  class="tablewidth">
  <tr>
    <td width=30%>课程编号:</td>
    <td width=70%><%=courseInfo_QQ.getCourseNumber() %></td>
  </tr>

  <tr>
    <td width=30%>课程名称:</td>
    <td width=70%><%=courseInfo_QQ.getCourseName() %></td>
  </tr>

  <tr>
    <td width=30%>上课老师:</td>
    <td width=70%>
      <select name="courseInfo_QQ.courseTeacher.teacherNumber" disabled>
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
    <td width=70%><%=courseInfo_QQ.getCourseTime() %></td>
  </tr>

  <tr>
    <td width=30%>选修类型:</td>
    <td width=70%><%=courseInfo_QQ.getCoursePlace() %></td>
  </tr>

  <tr>
    <td width=30%>课程学分:</td>
    <td width=70%><%=courseInfo_QQ.getCourseScore() %></td>
  </tr>

  <tr>
    <td width=30%>课程学时:</td>
    <td width=70%><%=courseInfo_QQ.getCourseMemo() %></td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="返回" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
