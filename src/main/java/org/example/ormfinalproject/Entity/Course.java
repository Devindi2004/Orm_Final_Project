package org.example.ormfinalproject.Entity;

public class Course {
    private int courseId;
    private String name;
    private String duration;
    private String fee;

    public Course(int courseId, String name, String duration, String fee) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
