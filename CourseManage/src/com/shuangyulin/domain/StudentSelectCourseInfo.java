package com.shuangyulin.domain;

public class StudentSelectCourseInfo {
    /*��¼���*/
    private int selectId;
    public int getSelectId() {
        return selectId;
    }
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    /*ѧ������*/
    private Student_QQ studentNumber;
    public Student_QQ getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(Student_QQ studentNumber) {
        this.studentNumber = studentNumber;
    }

    /*�γ̶���*/
    private CourseInfo_QQ courseNumber;
    public CourseInfo_QQ getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(CourseInfo_QQ courseNumber) {
        this.courseNumber = courseNumber;
    }

}