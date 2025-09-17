package org.example.ormfinalproject.Entity;

public class Instructor {
    private String phone;
    private String name;
    private String email;
    private int instructorId;

    public Instructor(String phone, String name, String email, int instructorId) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.instructorId = instructorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
