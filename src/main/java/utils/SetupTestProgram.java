package utils;


import entities.Department;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestProgram {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    Employee e1 = new Employee("Bo", "Carlsen", "test1@mail.com");
    Employee e2 = new Employee("Anders", "Andersen", "test2@mail.com");
    Employee e3 = new Employee("Camilla", "Petersen", "test3@mail.com");
    Employee e4 = new Employee("Sarah", "Poulsen", "test4@mail.com");
 
    Department d1 = new Department("FAS", "Fis AS", "This company can create building and parks.");
    Department d2 = new Department("GF", "Gardening Firm", "This company can create beautiful parks.");
    
    em.getTransaction().begin();

        e1.setDepartment(d1);

        e2.setDepartment(d1);

        e3.setDepartment(d1);

        e4.setDepartment(d2);
        
    em.persist(e1);
    em.persist(e2);
    em.persist(e3);
    em.persist(e4);
    
    em.persist(d1);
    em.persist(d2);
    
    em.getTransaction().commit();
    System.out.println("Created TEST data");
   
  }

}
