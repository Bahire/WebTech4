/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Student {
    private String firstname;
    private String lastname;
    private String email;
    @Id
    @Column(name="student_id")
    private String studentId;
    private Date dob;
    private String faculty;
    private String department;
//    private byte[] photo;
//    private byte[] diploma;

    public Student() {
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    
    
    
    public Student(String firstname, String lastname, String email, String studentId, Date dob, String faculty, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.studentId = studentId;
        this.dob = dob;
        this.faculty = faculty;
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    
    
}
