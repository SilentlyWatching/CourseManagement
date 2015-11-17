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

    /*�������Ҫ��ѯ������: �γ̱��*/
    private String courseNumber;
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    public String getCourseNumber() {
        return this.courseNumber;
    }

    /*�������Ҫ��ѯ������: �γ�����*/
    private String courseName;
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return this.courseName;
    }

    /*�������Ҫ��ѯ������: �Ͽ���ʦ*/
    private Teacher_QQ courseTeacher;
    public void setCourseTeacher(Teacher_QQ courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
    public Teacher_QQ getCourseTeacher() {
        return this.courseTeacher;
    }

    /*��ǰ�ڼ�ҳ*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*һ������ҳ*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();

    /*��������CourseInfo_QQ����*/
    private CourseInfo_QQ courseInfo_QQ;
    public void setCourseInfo_QQ(CourseInfo_QQ courseInfo_QQ) {
        this.courseInfo_QQ = courseInfo_QQ;
    }
    public CourseInfo_QQ getCourseInfo_QQ() {
        return this.courseInfo_QQ;
    }

    /*��ת�����CourseInfo_QQ��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�Teacher_QQ��Ϣ*/
        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        return "add_view";
    }

    /*���CourseInfo_QQ��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤�γ̱���Ƿ��Ѿ�����*/
        String courseNumber = courseInfo_QQ.getCourseNumber();
        CourseInfo_QQ db_courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);
        if(null != db_courseInfo_QQ) {
            ctx.put("error",  java.net.URLEncoder.encode("�ÿγ̱���Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
            Teacher_QQ courseTeacher = teacher_QQDAO.GetTeacher_QQByTeacherNumber(courseInfo_QQ.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQDAO.AddCourseInfo_QQ(courseInfo_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ���ʧ��!"));
            return "error";
        }
    }

    /*��ѯCourseInfo_QQ��Ϣ*/
    public String QueryCourseInfo_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryCourseInfo_QQInfo(courseNumber, courseName, courseTeacher, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        courseInfo_QQDAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = courseInfo_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*ǰ̨��ѯCourseInfo_QQ��Ϣ*/
    public String FrontQueryCourseInfo_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryCourseInfo_QQInfo(courseNumber, courseName, courseTeacher, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        courseInfo_QQDAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = courseInfo_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�CourseInfo_QQ��Ϣ*/
    public String ModifyCourseInfo_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������courseNumber��ȡCourseInfo_QQ����*/
        CourseInfo_QQ courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);

        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        ctx.put("courseInfo_QQ",  courseInfo_QQ);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�CourseInfo_QQ��Ϣ*/
    public String FrontShowCourseInfo_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������courseNumber��ȡCourseInfo_QQ����*/
        CourseInfo_QQ courseInfo_QQ = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(courseNumber);

        Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryAllTeacher_QQInfo();
        ctx.put("teacher_QQList", teacher_QQList);
        ctx.put("courseInfo_QQ",  courseInfo_QQ);
        return "front_show_view";
    }

    /*�����޸�CourseInfo_QQ��Ϣ*/
    public String ModifyCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();
            Teacher_QQ courseTeacher = teacher_QQDAO.GetTeacher_QQByTeacherNumber(courseInfo_QQ.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQDAO.UpdateCourseInfo_QQ(courseInfo_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��CourseInfo_QQ��Ϣ*/
    public String DeleteCourseInfo_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            courseInfo_QQDAO.DeleteCourseInfo_QQ(courseNumber);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQɾ��ʧ��!"));
            return "error";
        }
    }

}
