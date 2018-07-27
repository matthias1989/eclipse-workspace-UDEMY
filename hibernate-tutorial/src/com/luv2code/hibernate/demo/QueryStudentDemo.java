package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			// query the students: lastName = 'Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("\n\nStudents who have lastName of Doe");
			displayStudents(theStudents);
			
			// query the students: lastName = 'Doe' OR firstName = 'Daffy'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have lastName of Doe or firstName of Daffy");
			displayStudents(theStudents);
			
			// query the students: email ends with gmail@com
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents who have an email that ends with gmail.com");
			displayStudents(theStudents);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		

	}

	private static void displayStudents(List<Student> theStudents) {
		// display the students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
