package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {		
			// start a transaction
			session.beginTransaction();
			
			// option 2: Hibernate query with HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId",
					Instructor.class);
			
			// set parameter on query
			int theId = 1;
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			System.out.println("luv2code: Instructor: "+tempInstructor);

			
			// commit the transaction
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("\nluv2Code: The session is now closed!\n");
			
			// get course for the instructor
			System.out.println("luv2code: Courses: "+tempInstructor.getCourses());
			


			
			System.out.println("luv2code: Done!");
			
		}
		finally {
			// add clean up code
			session.close();
			factory.close();
		}
		
		

	}

}
