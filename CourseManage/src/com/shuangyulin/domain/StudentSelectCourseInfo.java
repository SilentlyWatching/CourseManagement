package com.shuangyulin.domain;

public class StudentSelectCourseInfo {
    /*记录编号*/
    private int selectId;
    public int getSelectId() {
        return selectId;
    }
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    /*学生对象*/
    private Student_QQ studentNumber;
    public Student_QQ getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(Student_QQ studentNumber) {
        this.studentNumber = studentNumber;
    }

    /*课程对象*/
    private CourseInfo_QQ courseNumber;
    public CourseInfo_QQ getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(CourseInfo_QQ courseNumber) {
        this.courseNumber = courseNumber;
    }

}