package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

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
			
			// delete the student
			//System.out.println("Deleting student: "+student);
			//session.delete(student);
			
			// delete student with id = 3
			System.out.println("Deling student with id =3");
			session.createQuery("delete from Student where id = 3").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		

	}

}
