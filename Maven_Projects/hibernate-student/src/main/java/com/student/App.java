package com.student;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.student.entity.Student;
import com.student.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student("Anurag Bera", 6, "CSE");
        session.save(s);

        tx.commit();
        session.close();

        System.out.println("Student inserted successfully!");
    }
}
