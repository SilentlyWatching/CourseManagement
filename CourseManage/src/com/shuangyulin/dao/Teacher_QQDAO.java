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

public class Teacher_QQDAO {

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
    public void AddTeacher_QQ(Teacher_QQ teacher_QQ) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(teacher_QQ);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询Teacher_QQ信息*/
    public ArrayList<Teacher_QQ> QueryTeacher_QQInfo(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ teacher_QQ where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher_QQ.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher_QQ.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher_QQ.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher_QQ.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始记录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List teacher_QQList = q.list();
            return (ArrayList<Teacher_QQ>) teacher_QQList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的Teacher_QQ记录*/
    public ArrayList<Teacher_QQ> QueryAllTeacher_QQInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ";
            Query q = s.createQuery(hql);
            List teacher_QQList = q.list();
            return (ArrayList<Teacher_QQ>) teacher_QQList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和记录数*/
    public void CalculateTotalPageAndRecordNumber(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ teacher_QQ where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher_QQ.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher_QQ.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher_QQ.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher_QQ.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            List teacher_QQList = q.list();
            recordNumber = teacher_QQList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public Teacher_QQ GetTeacher_QQByTeacherNumber(String teacherNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            Teacher_QQ teacher_QQ = (Teacher_QQ)s.get(Teacher_QQ.class, teacherNumber);
            return teacher_QQ;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新Teacher_QQ信息*/
    public void UpdateTeacher_QQ(Teacher_QQ teacher_QQ) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(teacher_QQ);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除Teacher_QQ信息*/
    public void DeleteTeacher_QQ (String teacherNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object teacher_QQ = s.get(Teacher_QQ.class, teacherNumber);
            s.delete(teacher_QQ);
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
