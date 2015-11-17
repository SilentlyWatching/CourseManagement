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

/*图片字段teacherPhoto参数接收*/
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
    /*界面层需要查询的属性: 教师编号*/
    private String teacherNumber;
    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
    public String getTeacherNumber() {
        return this.teacherNumber;
    }

    /*界面层需要查询的属性: 教师姓名*/
    private String teacherName;
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherName() {
        return this.teacherName;
    }

    /*界面层需要查询的属性: 出生日期*/
    private String teacherBirthday;
    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }
    public String getTeacherBirthday() {
        return this.teacherBirthday;
    }

    /*界面层需要查询的属性: 入职日期*/
    private String teacherArriveDate;
    public void setTeacherArriveDate(String teacherArriveDate) {
        this.teacherArriveDate = teacherArriveDate;
    }
    public String getTeacherArriveDate() {
        return this.teacherArriveDate;
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
    Teacher_QQDAO teacher_QQDAO = new Teacher_QQDAO();

    /*待操作的Teacher_QQ对象*/
    private Teacher_QQ teacher_QQ;
    public void setTeacher_QQ(Teacher_QQ teacher_QQ) {
        this.teacher_QQ = teacher_QQ;
    }
    public Teacher_QQ getTeacher_QQ() {
        return this.teacher_QQ;
    }

    /*跳转到添加Teacher_QQ视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加Teacher_QQ信息*/
    @SuppressWarnings("deprecation")
    public String AddTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        /*验证教师编号是否已经存在*/
        String teacherNumber = teacher_QQ.getTeacherNumber();
        Teacher_QQ db_teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);
        if(null != db_teacher_QQ) {
            ctx.put("error",  java.net.URLEncoder.encode("该教师编号已经存在!"));
            return "error";
        }
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*处理图片上传*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("上传图片格式不正确!"));
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
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ添加失败!"));
            return "error";
        }
    }

    /*查询Teacher_QQ信息*/
    public String QueryTeacher_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryTeacher_QQInfo(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*计算总的页数和总的记录数*/
        teacher_QQDAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*获取到总的页码数目*/
        totalPage = teacher_QQDAO.getTotalPage();
        /*当前查询条件下总记录数*/
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

    /*前台查询Teacher_QQ信息*/
    public String FrontQueryTeacher_QQ() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ> teacher_QQList = teacher_QQDAO.QueryTeacher_QQInfo(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*计算总的页数和总的记录数*/
        teacher_QQDAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*获取到总的页码数目*/
        totalPage = teacher_QQDAO.getTotalPage();
        /*当前查询条件下总记录数*/
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

    /*查询要修改的Teacher_QQ信息*/
    public String ModifyTeacher_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键teacherNumber获取Teacher_QQ对象*/
        Teacher_QQ teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ",  teacher_QQ);
        return "modify_view";
    }

    /*查询要修改的Teacher_QQ信息*/
    public String FrontShowTeacher_QQQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键teacherNumber获取Teacher_QQ对象*/
        Teacher_QQ teacher_QQ = teacher_QQDAO.GetTeacher_QQByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ",  teacher_QQ);
        return "front_show_view";
    }

    /*更新修改Teacher_QQ信息*/
    public String ModifyTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*处理图片上传*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("上传图片格式不正确!"));
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
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ信息更新失败!"));
            return "error";
       }
   }

    /*删除Teacher_QQ信息*/
    public String DeleteTeacher_QQ() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            teacher_QQDAO.DeleteTeacher_QQ(teacherNumber);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ删除失败!"));
            return "error";
        }
    }

}
