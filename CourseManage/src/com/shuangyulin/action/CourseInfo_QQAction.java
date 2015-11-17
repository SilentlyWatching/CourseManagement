package com.shuangyulin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shuangyulin.dao.CourseInfo_QQDAO;
import com.shuangyulin.domain.CourseInfo_QQ;
import com.shuangyulin.dao.Teacher_QQDAO;
import com.shuangyulin.domain.Teacher_QQ;
import com.shuangyulin.test.TestUtil;

public class CourseInfo_QQAction extends ActionSupport {

    /*界面层需要查询的属性: 课程编号*/
    private String courseNumber;
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    public String getCourseNumber() {
        return this.courseNumber;
    }

    /*界面层需要查询的属性: 课程名称*/
    private String courseName;
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return this.courseName;
    }

    /*界面层需要查询的属性: 上课老师*/
    private Teacher_QQ courseTeacher;
    public void setCourseTeacher(Teacher_QQ courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
    public Teacher_QQ getCourseTeacher() {
        return this.courseTeacher;
    }

    /*当前第几页*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*一共多少页*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*当前查询的总记录数目*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*业务层对象*/
    CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();

    /*待操作的CourseInfo_QQ对象*/
    private CourseInfo_QQ courseInfo_QQ;
    public void setCourseInfo_QQ(CourseInfo_QQ courseInfo_QQ) {
        this.courseInfo_QQ = courseInfo_QQ;
    }
    public CourseInfo_QQ getCourseInfo_QQ() {
        return this.courseInfo_QQ;
    }

    /*跳转到添加CourseInfo_QQ视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的Teacher_QQ信息*/
        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        return "add_view";
    }

    /*添加CourseInfo_QQ信息*/
    @SuppressWarnings("deprecation")
    public String AddCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        /*验证课程编号是否已经存在*/
        String courseNumber = courseInfo_QQ.getCourseNumber();
        CourseInfo_QQ db_courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);
        if(null != db_courseInfo_QQ) {
            ctx.put("error",  java.net.URLEncoder.encode("该课程编号已经存在!"));
            return "error";
        }
        try {
            if(true) {
            Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
            Teacher_QQ courseTeacher = teacher_QQDAO.GetTeacher_QQByTeacherNumber(courseInfo_QQ.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQDAO.AddCourseInfo_QQ(courseInfo_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ添加失败!"));
            return "error";
        }
    }

    /*查询CourseInfo_QQ信息*/
    public String QueryCourseInfo_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryCourseInfo_QQInfo(courseNumber, courseName, courseTeacher, currentPage);
        /*计算总的页数和总的记录数*/
        courseInfo_QQDAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*获取到总的页码数目*/
        totalPage = courseInfo_QQDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = courseInfo_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("courseInfo_QQList",  courseInfo_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("courseNumber", courseNumber);
        ctx.put("courseName", courseName);
        ctx.put("courseTeacher", courseTeacher);
        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        return "query_view";
    }

    /*前台查询CourseInfo_QQ信息*/
    public String FrontQueryCourseInfo_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryCourseInfo_QQInfo(courseNumber, courseName, courseTeacher, currentPage);
        /*计算总的页数和总的记录数*/
        courseInfo_QQDAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*获取到总的页码数目*/
        totalPage = courseInfo_QQDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = courseInfo_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("courseInfo_QQList",  courseInfo_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("courseNumber", courseNumber);
        ctx.put("courseName", courseName);
        ctx.put("courseTeacher", courseTeacher);
        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        return "front_query_view";
    }

    /*查询要修改的CourseInfo_QQ信息*/
    public String ModifyCourseInfo_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键courseNumber获取CourseInfo_QQ对象*/
        CourseInfo_QQ courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);

        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        ctx.put("courseInfo_QQ",  courseInfo_QQ);
        return "modify_view";
    }

    /*查询要修改的CourseInfo_QQ信息*/
    public String FrontShowCourseInfo_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键courseNumber获取CourseInfo_QQ对象*/
        CourseInfo_QQ courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);

        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        ctx.put("courseInfo_QQ",  courseInfo_QQ);
        return "front_show_view";
    }

    /*更新修改CourseInfo_QQ信息*/
    public String ModifyCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
            Teacher_QQ courseTeacher = teacher_QQDAO.GetTeacher_QQByTeacherNumber(courseInfo_QQ.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQDAO.UpdateCourseInfo_QQ(courseInfo_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ信息更新失败!"));
            return "error";
       }
   }

    /*删除CourseInfo_QQ信息*/
    public String DeleteCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            courseInfo_QQDAO.DeleteCourseInfo_QQ(courseNumber);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ删除失败!"));
            return "error";
        }
    }

}
