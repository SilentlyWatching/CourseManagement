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
import com.shuangyulin.dao.ScoreInfoDAO;
import com.shuangyulin.domain.ScoreInfo;
import com.shuangyulin.dao.Student_QQDAO;
import com.shuangyulin.domain.Student_QQ;
import com.shuangyulin.dao.CourseInfo_QQDAO;
import com.shuangyulin.domain.CourseInfo_QQ;
import com.shuangyulin.test.TestUtil;

public class ScoreInfoAction extends ActionSupport {

    /*界面层需要查询的属性: 学生对象*/
    private Student_QQ studentNumber;
    public void setStudentNumber(Student_QQ studentNumber) {
        this.studentNumber = studentNumber;
    }
    public Student_QQ getStudentNumber() {
        return this.studentNumber;
    }

    /*界面层需要查询的属性: 课程对象*/
    private CourseInfo_QQ courseNumber;
    public void setCourseNumber(CourseInfo_QQ courseNumber) {
        this.courseNumber = courseNumber;
    }
    public CourseInfo_QQ getCourseNumber() {
        return this.courseNumber;
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

    private int scoreId;
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }
    public int getScoreId() {
        return scoreId;
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
    ScoreInfoDAO scoreInfoDAO = new ScoreInfoDAO();

    /*待操作的ScoreInfo对象*/
    private ScoreInfo scoreInfo;
    public void setScoreInfo(ScoreInfo scoreInfo) {
        this.scoreInfo = scoreInfo;
    }
    public ScoreInfo getScoreInfo() {
        return this.scoreInfo;
    }

    /*跳转到添加ScoreInfo视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的Student_QQ信息*/
        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        /*查询所有的CourseInfo_QQ信息*/
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        return "add_view";
    }

    /*添加ScoreInfo信息*/
    @SuppressWarnings("deprecation")
    public String AddScoreInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQDAO student_QQDAO = new Student_QQDAO();
            Student_QQ studentNumber = student_QQDAO.GetStudent_QQByStudentNumber(scoreInfo.getStudentNumber().getStudentNumber());
            scoreInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
            CourseInfo_QQ courseNumber = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(scoreInfo.getCourseNumber().getCourseNumber());
            scoreInfo.setCourseNumber(courseNumber);
            }
            scoreInfoDAO.AddScoreInfo(scoreInfo);
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfo添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfo添加失败!"));
            return "error";
        }
    }

    /*查询ScoreInfo信息*/
    public String QueryScoreInfo() {
        if(currentPage == 0) currentPage = 1;
        List<ScoreInfo> scoreInfoList = scoreInfoDAO.QueryScoreInfoInfo(studentNumber, courseNumber, currentPage);
        /*计算总的页数和总的记录数*/
        scoreInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*获取到总的页码数目*/
        totalPage = scoreInfoDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = scoreInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("scoreInfoList",  scoreInfoList);
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

    /*前台查询ScoreInfo信息*/
    public String FrontQueryScoreInfo() {
        if(currentPage == 0) currentPage = 1;
        List<ScoreInfo> scoreInfoList = scoreInfoDAO.QueryScoreInfoInfo(studentNumber, courseNumber, currentPage);
        /*计算总的页数和总的记录数*/
        scoreInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*获取到总的页码数目*/
        totalPage = scoreInfoDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = scoreInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("scoreInfoList",  scoreInfoList);
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

    /*查询要修改的ScoreInfo信息*/
    public String ModifyScoreInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键scoreId获取ScoreInfo对象*/
        ScoreInfo scoreInfo = scoreInfoDAO.GetScoreInfoByScoreId(scoreId);

        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        ctx.put("scoreInfo",  scoreInfo);
        return "modify_view";
    }

    /*查询要修改的ScoreInfo信息*/
    public String FrontShowScoreInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键scoreId获取ScoreInfo对象*/
        ScoreInfo scoreInfo = scoreInfoDAO.GetScoreInfoByScoreId(scoreId);

        Student_QQDAO student_QQDAO = new Student_QQDAO();
        List<Student_QQ> student_QQList = student_QQDAO.QueryAllStudent_QQInfo();
        ctx.put("student_QQList", student_QQList);
        CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
        List<CourseInfo_QQ> courseInfo_QQList = courseInfo_QQDAO.QueryAllCourseInfo_QQInfo();
        ctx.put("courseInfo_QQList", courseInfo_QQList);
        ctx.put("scoreInfo",  scoreInfo);
        return "front_show_view";
    }

    /*更新修改ScoreInfo信息*/
    public String ModifyScoreInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQDAO student_QQDAO = new Student_QQDAO();
            Student_QQ studentNumber = student_QQDAO.GetStudent_QQByStudentNumber(scoreInfo.getStudentNumber().getStudentNumber());
            scoreInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQDAO courseInfo_QQDAO = new CourseInfo_QQDAO();
            CourseInfo_QQ courseNumber = courseInfo_QQDAO.GetCourseInfo_QQByCourseNumber(scoreInfo.getCourseNumber().getCourseNumber());
            scoreInfo.setCourseNumber(courseNumber);
            }
            scoreInfoDAO.UpdateScoreInfo(scoreInfo);
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfo信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfo信息更新失败!"));
            return "error";
       }
   }

    /*删除ScoreInfo信息*/
    public String DeleteScoreInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            scoreInfoDAO.DeleteScoreInfo(scoreId);
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfo删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfo删除失败!"));
            return "error";
        }
    }

}
