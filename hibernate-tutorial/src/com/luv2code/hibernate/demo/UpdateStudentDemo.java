package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			// start a transaction
			session.beginTransaction();
			
			System.out.println("\nGetting Student with id "+ studentId);
			Student student = session.get(Student.class, studentId);
			System.out.println("Updating student");
			student.setFirstName("Scooby");		
		
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Updating email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		
		

	}

}
