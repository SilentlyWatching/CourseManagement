package com.shuangyulin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mysql.jdbc.Statement;
import com.shuangyulin.utils.HibernateUtil;

import com.shuangyulin.domain.Teacher_QQ;
import com.shuangyulin.domain.CourseInfo_QQ;

public class CourseInfo_QQDAO {

    /*每页显示记录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加图书信息*/
    public void AddCourseInfo_QQ(CourseInfo_QQ courseInfo_QQ) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(courseInfo_QQ);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询CourseInfo_QQ信息*/
    public ArrayList<CourseInfo_QQ> QueryCourseInfo_QQInfo(String courseNumber,String courseName,Teacher_QQ courseTeacher,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ courseInfo_QQ where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo_QQ.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo_QQ.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo_QQ.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始记录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List courseInfo_QQList = q.list();
            return (ArrayList<CourseInfo_QQ>) courseInfo_QQList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的CourseInfo_QQ记录*/
    public ArrayList<CourseInfo_QQ> QueryAllCourseInfo_QQInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ";
            Query q = s.createQuery(hql);
            List courseInfo_QQList = q.list();
            return (ArrayList<CourseInfo_QQ>) courseInfo_QQList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和记录数*/
    public void CalculateTotalPageAndRecordNumber(String courseNumber,String courseName,Teacher_QQ courseTeacher) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ courseInfo_QQ where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo_QQ.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo_QQ.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo_QQ.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            List courseInfo_QQList = q.list();
            recordNumber = courseInfo_QQList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public CourseInfo_QQ GetCourseInfo_QQByCourseNumber(String courseNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            CourseInfo_QQ courseInfo_QQ = (CourseInfo_QQ)s.get(CourseInfo_QQ.class, courseNumber);
            return courseInfo_QQ;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新CourseInfo_QQ信息*/
    public void UpdateCourseInfo_QQ(CourseInfo_QQ courseInfo_QQ) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(courseInfo_QQ);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除CourseInfo_QQ信息*/
    public void DeleteCourseInfo_QQ (String courseNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object courseInfo_QQ = s.get(CourseInfo_QQ.class, courseNumber);
            s.delete(courseInfo_QQ);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

}
