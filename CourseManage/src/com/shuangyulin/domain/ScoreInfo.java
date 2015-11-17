package com.shuangyulin.domain;

public class ScoreInfo {
    /*记录编号*/
    private int scoreId;
    public int getScoreId() {
        return scoreId;
    }
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
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

    /*成绩得分*/
    private float scoreValue;
    public float getScoreValue() {
        return scoreValue;
    }
    public void setScoreValue(float scoreValue) {
        this.scoreValue = scoreValue;
    }

    /*学生评价*/
    private String studentEvaluate;
    public String getStudentEvaluate() {
        return studentEvaluate;
    }
    public void setStudentEvaluate(String studentEvaluate) {
        this.studentEvaluate = studentEvaluate;
    }

}