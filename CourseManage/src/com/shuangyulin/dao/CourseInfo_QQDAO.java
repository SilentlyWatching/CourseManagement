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

    /*ÿҳ��ʾ��¼��Ŀ*/
    private final int PAGE_SIZE = 10;

    /*�����ѯ���ܵ�ҳ��*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*�����ѯ�����ܼ�¼��*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*���ͼ����Ϣ*/
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

    /*��ѯCourseInfo_QQ��Ϣ*/
    public ArrayList<CourseInfo_QQ> QueryCourseInfo_QQInfo(String courseNumber,String courseName,Teacher_QQ courseTeacher,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ courseInfo_QQ where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo_QQ.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo_QQ.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo_QQ.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List courseInfo_QQList = q.list();
            return (ArrayList<CourseInfo_QQ>) courseInfo_QQList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�CourseInfo_QQ��¼*/
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

    /*�����ܵ�ҳ���ͼ�¼��*/
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

    /*����������ȡ����*/
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

    /*����CourseInfo_QQ��Ϣ*/
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

    /*ɾ��CourseInfo_QQ��Ϣ*/
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
