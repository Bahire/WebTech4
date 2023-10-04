/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentDao {
    public Student registerStudent(Student student){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.save(student);
        tr.commit();
        return student;
    }
    
    public Student findStudentById(Student student){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Student theStudent = (Student) ss.get(Student.class, student.getStudentId());
        if(theStudent != null){
            return theStudent;
        }
        return null;
    }
    
    public Student updateStudent(Student student){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        Student stud = findStudentById(student);
        if(stud != null){
            stud.setDepartment(student.getDepartment());
            stud.setDob(student.getDob());
            stud.setFaculty(student.getFaculty());
            stud.setFirstname(stud.getFirstname());
            stud.setLastname(student.getLastname());
            ss.update(stud);
            tr.commit();
            ss.close();
            return stud;
        }
        return null;
    }
    
    public void deleteStudent(Student student){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Student stud = findStudentById(student);
        if(stud != null){
            ss.delete(stud);
        }
        ss.close();
    }
    
    public List<Student> allStudentList(){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<Student> studentList = ss.createQuery("select stud from Student student").list();
        ss.close();
        return studentList;
    }
    
}
