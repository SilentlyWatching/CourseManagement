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
import com.shuangyulin.dao.Student_QQDAO;
import com.shuangyulin.domain.Student_QQ;
import com.shuangyulin.dao.ClassInfoDAO;
import com.shuangyulin.domain.ClassInfo;
import com.shuangyulin.test.TestUtil;

public class Student_QQAction extends ActionSupport {

/*ͼƬ�ֶ�studentPhoto��������*/
	 private File studentPhotoFile;
	 private String studentPhotoFileFileName;
	 private String studentPhotoFileContentType;
	 public File getStudentPhotoFile() {
		return studentPhotoFile;
	}
	public void setStudentPhotoFile(File studentPhotoFile) {
		this.studentPhotoFile = studentPhotoFile;
	}
	public String getStudentPhotoFileFileName() {
		return studentPhotoFileFileName;
	}
	public void setStudentPhotoFileFileName(String studentPhotoFileFileName) {
		this.studentPhotoFileFileName = studentPhotoFileFileName;
	}
	public String getStudentPhotoFileContentType() {
		return studentPhotoFileContentType;
	}
	public void setStudentPhotoFileContentType(String studentPhotoFileContentType) {
		this.studentPhotoFileContentType = studentPhotoFileContentType;
	}
    /*�������Ҫ��ѯ������: ѧ��*/
    private String studentNumber;
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getStudentNumber() {
        return this.studentNumber;
    }

    /*�������Ҫ��ѯ������: ����*/
    private String studentName;
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentName() {
        return this.studentName;
    }

    /*�������Ҫ��ѯ������: ���ڰ༶*/
    private ClassInfo studentClassNumber;
    public void setStudentClassNumber(ClassInfo studentClassNumber) {
        this.studentClassNumber = studentClassNumber;
    }
    public ClassInfo getStudentClassNumber() {
        return this.studentClassNumber;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String studentBirthday;
    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }
    public String getStudentBirthday() {
        return this.studentBirthday;
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
    Student_QQDAO student_QQDAO = new Student_QQDAO();

    /*��������Student_QQ����*/
    private Student_QQ student_QQ;
    public void setStudent_QQ(Student_QQ student_QQ) {
        this.student_QQ = student_QQ;
    }
    public Student_QQ getStudent_QQ() {
        return this.student_QQ;
    }

    /*��ת�����Student_QQ��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�ClassInfo��Ϣ*/
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        return "add_view";
    }

    /*���Student_QQ��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddStudent_QQ() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤ѧ���Ƿ��Ѿ�����*/
        String studentNumber = student_QQ.getStudentNumber();
        Student_QQ db_student_QQ = student_QQDAO.GetStudent_QQByStudentNumber(studentNumber);
        if(null != db_student_QQ) {
            ctx.put("error",  java.net.URLEncoder.encode("��ѧ���Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            ClassInfoDAO classInfoDAO = new ClassInfoDAO();
            ClassInfo studentClassNumber = classInfoDAO.GetClassInfoByClassNumber(student_QQ.getStudentClassNumber().getClassNumber());
            student_QQ.setStudentClassNumber(studentClassNumber);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String studentPhotoFileName = ""; 
       	 	if(studentPhotoFile != null) {
       	 		InputStream is = new FileInputStream(studentPhotoFile);
       			String fileContentType = this.getStudentPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, studentPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}
            if(studentPhotoFile != null)
            	student_QQ.setStudentPhoto("upload/" + studentPhotoFileName);
            else
            	student_QQ.setStudentPhoto("upload/NoImage.jpg");
            student_QQDAO.AddStudent_QQ(student_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQ��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQ���ʧ��!"));
            return "error";
        }
    }

    /*��ѯStudent_QQ��Ϣ*/
    public String QueryStudent_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(studentBirthday == null) studentBirthday = "";
        List<Student_QQ> student_QQList = student_QQDAO.QueryStudent_QQInfo(studentNumber, studentName, studentClassNumber, studentBirthday, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        student_QQDAO.CalculateTotalPageAndRecordNumber(studentNumber, studentName, studentClassNumber, studentBirthday);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = student_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = student_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("student_QQList",  student_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        ctx.put("studentName", studentName);
        ctx.put("studentClassNumber", studentClassNumber);
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("studentBirthday", studentBirthday);
        return "query_view";
    }

    /*ǰ̨��ѯStudent_QQ��Ϣ*/
    public String FrontQueryStudent_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(studentBirthday == null) studentBirthday = "";
        List<Student_QQ> student_QQList = student_QQDAO.QueryStudent_QQInfo(studentNumber, studentName, studentClassNumber, studentBirthday, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        student_QQDAO.CalculateTotalPageAndRecordNumber(studentNumber, studentName, studentClassNumber, studentBirthday);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = student_QQDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = student_QQDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("student_QQList",  student_QQList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        ctx.put("studentName", studentName);
        ctx.put("studentClassNumber", studentClassNumber);
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("studentBirthday", studentBirthday);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�Student_QQ��Ϣ*/
    public String ModifyStudent_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������studentNumber��ȡStudent_QQ����*/
        Student_QQ student_QQ = student_QQDAO.GetStudent_QQByStudentNumber(studentNumber);

        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("student_QQ",  student_QQ);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Student_QQ��Ϣ*/
    public String FrontShowStudent_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������studentNumber��ȡStudent_QQ����*/
        Student_QQ student_QQ = student_QQDAO.GetStudent_QQByStudentNumber(studentNumber);

        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("student_QQ",  student_QQ);
        return "front_show_view";
    }

    /*�����޸�Student_QQ��Ϣ*/
    public String ModifyStudent_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            ClassInfoDAO classInfoDAO = new ClassInfoDAO();
            ClassInfo studentClassNumber = classInfoDAO.GetClassInfoByClassNumber(student_QQ.getStudentClassNumber().getClassNumber());
            student_QQ.setStudentClassNumber(studentClassNumber);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String studentPhotoFileName = ""; 
       	 	if(studentPhotoFile != null) {
       	 		InputStream is = new FileInputStream(studentPhotoFile);
       			String fileContentType = this.getStudentPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, studentPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
            student_QQ.setStudentPhoto("upload/" + studentPhotoFileName);
       	 	}
            student_QQDAO.UpdateStudent_QQ(student_QQ);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQ��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQ��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Student_QQ��Ϣ*/
    public String DeleteStudent_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            student_QQDAO.DeleteStudent_QQ(studentNumber);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQɾ��ʧ��!"));
            return "error";
        }
    }

}
