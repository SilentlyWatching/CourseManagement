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
import com.shuangyulin.dao.Teacher_QQDAO;
import com.shuangyulin.domain.Teacher_QQ;
import com.shuangyulin.test.TestUtil;

public class Teacher_QQAction extends ActionSupport {

/*ͼƬ�ֶ�teacherPhoto��������*/
	 private File teacherPhotoFile;
	 private String teacherPhotoFileFileName;
	 private String teacherPhotoFileContentType;
	 public File getTeacherPhotoFile() {
		return teacherPhotoFile;
	}
	public void setTeacherPhotoFile(File teacherPhotoFile) {
		this.teacherPhotoFile = teacherPhotoFile;
	}
	public String getTeacherPhotoFileFileName() {
		return teacherPhotoFileFileName;
	}
	public void setTeacherPhotoFileFileName(String teacherPhotoFileFileName) {
		this.teacherPhotoFileFileName = teacherPhotoFileFileName;
	}
	public String getTeacherPhotoFileContentType() {
		return teacherPhotoFileContentType;
	}
	public void setTeacherPhotoFileContentType(String teacherPhotoFileContentType) {
		this.teacherPhotoFileContentType = teacherPhotoFileContentType;
	}
    /*�������Ҫ��ѯ������: ��ʦ���*/
    private String teacherNumber;
    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
    public String getTeacherNumber() {
        return this.teacherNumber;
    }

    /*�������Ҫ��ѯ������: ��ʦ����*/
    private String teacherName;
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherName() {
        return this.teacherName;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String teacherBirthday;
    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }
    public String getTeacherBirthday() {
        return this.teacherBirthday;
    }

    /*�������Ҫ��ѯ������: ��ְ����*/
    private String teacherArriveDate;
    public void setTeacherArriveDate(String teacherArriveDate) {
        this.teacherArriveDate = teacherArriveDate;
    }
    public String getTeacherArriveDate() {
        return this.teacherArriveDate;
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
    Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();

    /*��������Teacher_QQ����*/
    private Teacher_QQ teacher_QQ;
    public void setTeacher_QQ(Teacher_QQ teacher_QQ) {
        this.teacher_QQ = teacher_QQ;
    }
    public Teacher_QQ getTeacher_QQ() {
        return this.teacher_QQ;
    }

    /*��ת�����Teacher_QQ��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Teacher_QQ��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤��ʦ����Ƿ��Ѿ�����*/
        String teacherNumber = teacher_QQ.getTeacherNumber();
        Teacher_QQ db_teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);
        if(null != db_teacher_QQ) {
            ctx.put("error",  java.net.URLEncoder.encode("�ý�ʦ����Ѿ�����!"));
            return "error";
        }
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, teacherPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}
            if(teacherPhotoFile != null)
            	teacher_QQ.setTeacherPhoto("upload/" + teacherPhotoFileName);
            else
            	teacher_QQ.setTeacherPhoto("upload/NoImage.jpg");
            teacher_QQDAO.AddTeacher_QQ(teacher_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ���ʧ��!"));
            return "error";
        }
    }

    /*��ѯTeacher_QQ��Ϣ*/
    public String QueryTeacher_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryTeacher_QQInfo(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        teacher_QQDAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = teacher_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = teacher_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("teacher_QQList",  teacher_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("teacherNumber", teacherNumber);
        ctx.put("teacherName", teacherName);
        ctx.put("teacherBirthday", teacherBirthday);
        ctx.put("teacherArriveDate", teacherArriveDate);
        return "query_view";
    }

    /*ǰ̨��ѯTeacher_QQ��Ϣ*/
    public String FrontQueryTeacher_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryTeacher_QQInfo(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        teacher_QQDAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = teacher_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = teacher_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("teacher_QQList",  teacher_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("teacherNumber", teacherNumber);
        ctx.put("teacherName", teacherName);
        ctx.put("teacherBirthday", teacherBirthday);
        ctx.put("teacherArriveDate", teacherArriveDate);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�Teacher_QQ��Ϣ*/
    public String ModifyTeacher_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������teacherNumber��ȡTeacher_QQ����*/
        Teacher_QQ teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ",  teacher_QQ);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Teacher_QQ��Ϣ*/
    public String FrontShowTeacher_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������teacherNumber��ȡTeacher_QQ����*/
        Teacher_QQ teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ",  teacher_QQ);
        return "front_show_view";
    }

    /*�����޸�Teacher_QQ��Ϣ*/
    public String ModifyTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, teacherPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
            teacher_QQ.setTeacherPhoto("upload/" + teacherPhotoFileName);
       	 	}
            teacher_QQDAO.UpdateTeacher_QQ(teacher_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Teacher_QQ��Ϣ*/
    public String DeleteTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            teacher_QQDAO.DeleteTeacher_QQ(teacherNumber);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQɾ��ʧ��!"));
            return "error";
        }
    }

}
