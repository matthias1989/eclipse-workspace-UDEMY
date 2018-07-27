package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create student object
			System.out.println("Creating a new Student object...");
			Student theStudent = new Student("Daffy","Duck","daffy@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(theStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			// NEW CODE
			System.out.println("Saved student. Generated id: "+ theStudent.getId());
			
			// get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id: primary key
			System.out.println("\nGetting student with id: "+theStudent.getId());
			Student student = session.get(Student.class, theStudent.getId());
			
			System.out.println("Get complete: "+student);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		

	}

}
