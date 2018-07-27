package com.luv2code.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Employee;

public class MainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			
			// save new object
			session.beginTransaction();
			Employee employee = new Employee("Stan","Vortman","Intelij");
			session.save(employee);
			session.getTransaction().commit();
			
			// get the saved object by its primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			Employee queriedEmployee = session.get(Employee.class, employee.getId());
			System.out.println("employee with id " + employee.getId() + " => " + employee);
			session.getTransaction().commit();
			
			// query all objects with company: ITTech
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Employee> employees = session.createQuery("from Employee e where e.company='ITTech'").getResultList();
			System.out.println("All employees with company: ITTech");
			for(Employee tempEmployee : employees) {
				System.out.println(tempEmployee);
			}
			session.getTransaction().commit();
			
			// delete object by its primary key
			session = factory.getCurrentSession();
			session.beginTransaction();		
			session.createQuery("delete from Employee where firstName='Stan' and lastName='Vortman'").executeUpdate();
			session.getTransaction().commit();
			
			
		}
		finally{
			factory.close();
		}
	}

}
