<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.shuangyulin.domain.StudentSelectCourseInfo" %>
<%@ page import="com.shuangyulin.domain.Student_QQ" %>
<%@ page import="com.shuangyulin.domain.CourseInfo_QQ" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�studentNumber��Ϣ
    List<Student_QQ> student_QQList = (List<Student_QQ>)request.getAttribute("student_QQList");
    //��ȡ���е�courseNumber��Ϣ
    List<CourseInfo_QQ> courseInfo_QQList = (List<CourseInfo_QQ>)request.getAttribute("courseInfo_QQList");
    StudentSelectCourseInfo studentSelectCourseInfo = (StudentSelectCourseInfo)request.getAttribute("studentSelectCourseInfo");

%>
<HTML><HEAD><TITLE>�鿴ѡ����Ϣ</TITLE>
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
    <td width=30%>��¼���:</td>
    <td width=70%><%=studentSelectCourseInfo.getSelectId() %></td>
  </tr>

  <tr>
    <td width=30%>ѧ������:</td>
    <td width=70%>
      <select name="studentSelectCourseInfo.studentNumber.studentNumber" disabled>
      <%
        for(Student_QQ student_QQ:student_QQList) {
          String selected = "";
          if(student_QQ.getStudentNumber().equals(studentSelectCourseInfo.getStudentNumber().getStudentNumber()))
            selected = "selected";
      %>
          <option value='<%=student_QQ.getStudentNumber() %>' <%=selected %>><%=student_QQ.getStudentName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>�γ̶���:</td>
    <td width=70%>
      <select name="studentSelectCourseInfo.courseNumber.courseNumber" disabled>
      <%
        for(CourseInfo_QQ courseInfo_QQ:courseInfo_QQList) {
          String selected = "";
          if(courseInfo_QQ.getCourseNumber().equals(studentSelectCourseInfo.getCourseNumber().getCourseNumber()))
            selected = "selected";
      %>
          <option value='<%=courseInfo_QQ.getCourseNumber() %>' <%=selected %>><%=courseInfo_QQ.getCourseName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="����" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
