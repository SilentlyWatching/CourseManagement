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
import com.shuangyulin.dao.StudentSelectCourseInfoDAO;
import com.shuangyulin.domain.StudentSelectCourseInfo;
import com.shuangyulin.dao.Student_QQDAO;
import com.shuangyulin.domain.Student_QQ;
import com.shuangyulin.dao.CourseInfo_QQDAO;
import com.shuangyulin.domain.CourseInfo_QQ;
import com.shuangyulin.test.TestUtil;

public class StudentSelectCourseInfoAction extends ActionSupport {

    /*�������Ҫ��ѯ������: ѧ������*/
    private Student_QQ studentNumber;
    public void setStudentNumber(Student_QQ studentNumber) {
        this.studentNumber = studentNumber;
    }
    public Student_QQ getStudentNumber() {
        return this.studentNumber;
    }

    /*�������Ҫ��ѯ������: �γ̶���*/
    private CourseInfo_QQ courseNumber;
    public void setCourseNumber(CourseInfo_QQ courseNumber) {
        this.courseNumber = courseNumber;
    }
    public CourseInfo_QQ getCourseNumber() {
        return this.courseNumber;
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

    private int selectId;
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }
    public int getSelectId() {
        return selectId;
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
    StudentSelectCourseInfoDAO studentSelectCourseInfoDAO = new StudentSelectCourseInfoDAO();

    /*��������StudentSelectCourseInfo����*/
    private StudentSelectCourseInfo studentSelectCourseInfo;
    public void setStudentSelectCourseInfo(StudentSelectCourseInfo studentSelectCourseInfo) {
        this.studentSelectCourseInfo = studentSelectCourseInfo;
    }
    public StudentSelectCourseInfo getStudentSelectCourseInfo() {
        return this.studentSelectCourseInfo;
    }

    /*��ת�����StudentSelectCourseInfo��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�Student_QQ��Ϣ*/
        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        /*��ѯ���е�CourseInfo_QQ��Ϣ*/
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        return "add_view";
    }

    /*���StudentSelectCourseInfo��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQDAO student_QQDAO = new Student_QQDAO();
            Student_QQ studentNumber = student_QQDAO.GetStudent_QQByStudentNumber(studentSelectCourseInfo.getStudentNumber().getStudentNumber());
            studentSelectCourseInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
            CourseInfo_QQ courseNumber = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(studentSelectCourseInfo.getCourseNumber().getCourseNumber());
            studentSelectCourseInfo.setCourseNumber(courseNumber);
            }
            studentSelectCourseInfoDAO.AddStudentSelectCourseInfo(studentSelectCourseInfo);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯStudentSelectCourseInfo��Ϣ*/
    public String QueryStudentSelectCourseInfo() {
        if(currentPage == 0) currentPage = 1;
        List<StudentSelectCourseInfo> studentSelectCourseInfoList = studentSelectCourseInfoDAO.QueryStudentSelectCourseInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        studentSelectCourseInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = studentSelectCourseInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = studentSelectCourseInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("studentSelectCourseInfoList",  studentSelectCourseInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        ctx.put("courseNumber", courseNumber);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        return "query_view";
    }

    /*ǰ̨��ѯStudentSelectCourseInfo��Ϣ*/
    public String FrontQueryStudentSelectCourseInfo() {
        if(currentPage == 0) currentPage = 1;
        List<StudentSelectCourseInfo> studentSelectCourseInfoList = studentSelectCourseInfoDAO.QueryStudentSelectCourseInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        studentSelectCourseInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = studentSelectCourseInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = studentSelectCourseInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("studentSelectCourseInfoList",  studentSelectCourseInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        ctx.put("courseNumber", courseNumber);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�StudentSelectCourseInfo��Ϣ*/
    public String ModifyStudentSelectCourseInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������selectId��ȡStudentSelectCourseInfo����*/
        StudentSelectCourseInfo studentSelectCourseInfo = studentSelectCourseInfoDAO.GetStudentSelectCourseInfoBySelectId(selectId);

        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        ctx.put("studentSelectCourseInfo",  studentSelectCourseInfo);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�StudentSelectCourseInfo��Ϣ*/
    public String FrontShowStudentSelectCourseInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������selectId��ȡStudentSelectCourseInfo����*/
        StudentSelectCourseInfo studentSelectCourseInfo = studentSelectCourseInfoDAO.GetStudentSelectCourseInfoBySelectId(selectId);

        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        ctx.put("studentSelectCourseInfo",  studentSelectCourseInfo);
        return "front_show_view";
    }

    /*�����޸�StudentSelectCourseInfo��Ϣ*/
    public String ModifyStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQDAO student_QQDAO = new Student_QQDAO();
            Student_QQ studentNumber = student_QQDAO.GetStudent_QQByStudentNumber(studentSelectCourseInfo.getStudentNumber().getStudentNumber());
            studentSelectCourseInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
            CourseInfo_QQ courseNumber = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(studentSelectCourseInfo.getCourseNumber().getCourseNumber());
            studentSelectCourseInfo.setCourseNumber(courseNumber);
            }
            studentSelectCourseInfoDAO.UpdateStudentSelectCourseInfo(studentSelectCourseInfo);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��StudentSelectCourseInfo��Ϣ*/
    public String DeleteStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            studentSelectCourseInfoDAO.DeleteStudentSelectCourseInfo(selectId);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
