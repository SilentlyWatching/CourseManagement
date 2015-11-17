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

    private int scoreId;
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }
    public int getScoreId() {
        return scoreId;
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
    ScoreInfoDAO scoreInfoDAO = new ScoreInfoDAO();

    /*��������ScoreInfo����*/
    private ScoreInfo scoreInfo;
    public void setScoreInfo(ScoreInfo scoreInfo) {
        this.scoreInfo = scoreInfo;
    }
    public ScoreInfo getScoreInfo() {
        return this.scoreInfo;
    }

    /*��ת�����ScoreInfo��ͼ*/
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

    /*���ScoreInfo��Ϣ*/
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
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯScoreInfo��Ϣ*/
    public String QueryScoreInfo() {
        if(currentPage == 0) currentPage = 1;
        List<ScoreInfo> scoreInfoList = scoreInfoDAO.QueryScoreInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        scoreInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = scoreInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*ǰ̨��ѯScoreInfo��Ϣ*/
    public String FrontQueryScoreInfo() {
        if(currentPage == 0) currentPage = 1;
        List<ScoreInfo> scoreInfoList = scoreInfoDAO.QueryScoreInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        scoreInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = scoreInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
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

    /*��ѯҪ�޸ĵ�ScoreInfo��Ϣ*/
    public String ModifyScoreInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������scoreId��ȡScoreInfo����*/
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

    /*��ѯҪ�޸ĵ�ScoreInfo��Ϣ*/
    public String FrontShowScoreInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������scoreId��ȡScoreInfo����*/
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

    /*�����޸�ScoreInfo��Ϣ*/
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
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��ScoreInfo��Ϣ*/
    public String DeleteScoreInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            scoreInfoDAO.DeleteScoreInfo(scoreId);
            ctx.put("message",  java.net.URLEncoder.encode("ScoreInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ScoreInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
