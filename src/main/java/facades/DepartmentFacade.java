/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.DepartmentDTO;
import dto.EmployeeDTO;
import dto.EmployeesDTO;
import entities.Department;
import entities.Employee;
import errorhandling.NotFoundException;
import exceptions.MissingInput;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author miade
 */
public class DepartmentFacade implements IDepartmentFacade{
    
    private static DepartmentFacade instance;
    private static EntityManagerFactory emf;
    
    private DepartmentFacade() {}
    
    public static DepartmentFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DepartmentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
// GET:
    
    @Override
    public EmployeesDTO getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            return new EmployeesDTO(em.createNamedQuery("Employee.getAllRows").getResultList());
        } finally{  
            em.close();
        }   
    }
    
    @Override
    public EmployeeDTO getEmployee(int id) throws NotFoundException {
       long e_id = id;
       
       EntityManager em = getEntityManager();
       try {
           Employee emp = em.find(Employee.class, e_id);
           
           if (emp == null) {
                throw new NotFoundException(String.format("No employee with provided id found.", id));
            } else {
                return new EmployeeDTO(emp);
           }
       } finally {
           em.close();
       }
    }

    
    @Override
    public DepartmentDTO getDepartment(int id)throws NotFoundException {
        long d_id = id;
       
        EntityManager em = getEntityManager();
        try {
            Department dep = em.find(Department.class, d_id);

            if (dep == null) {
                 throw new NotFoundException(String.format("No department with provided id found.", id));
             } else {
                 return new DepartmentDTO(dep);
            }
        } finally {
            em.close();
        }
    }
    
    
// POST
    
    
    @Override
    public EmployeeDTO addEmployee(Employee emp, String depCode, String depName, String depDes) throws MissingInput{
        
        checkFormMissingInput(emp.getFirstName(), emp.getLastName());
        
        emp.setDepartment(new Department(depCode, depName, depDes));
        Department department = emp.getDepartment();
        
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
                List<Department> departments = getDepartmentsFromDB(em, department);
                checkForExcistingDepartment(departments, emp, depCode, depName, depDes);
                em.persist(emp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(emp);
    }
    
    private List<Department> getDepartmentsFromDB(EntityManager em, Department department) {
        Query q = em.createQuery(
          "SELECT d FROM Department d WHERE d.code= :code AND d.name= :name AND d.description= :description"
        );
        q.setParameter("code", department.getCode());
        q.setParameter("name", department.getDepartmentName());
        q.setParameter("description", department.getDepartmentDescription());
        // Laver en liste med alle departments:
        List<Department> departments = q.getResultList();
        
        return departments;
    }

    private void checkForExcistingDepartment(List<Department> departments, Employee emp, String street, String zip, String city) {
        if (departments.size() > 0){
            emp.setDepartment(departments.get(0));
        } else {
            emp.setDepartment(new Department(street, zip, city));
        }
    }

    private void checkFormMissingInput(String firstName, String lastName) throws MissingInput {
        if ((firstName.length() == 0) || (lastName.length() == 0)){
            throw new MissingInput("First Name and/or Last Name is missing");
        }
    }
    
    
    
    
    
    
    
}
