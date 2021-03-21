/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author miade
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.deleteAllRows", query = "DELETE from Employee"),
    @NamedQuery(name = "Employee.getById", query = "SELECT e FROM Employee e WHERE e.employee_id= :id"),
    @NamedQuery(name = "Employee.getAllRows", query = "SELECT e from Employee e"),
})

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    
    private String firstName;
    private String lastName;
    private String email;
    
    @ManyToOne(cascade = { CascadeType.PERSIST })
    private Department department;
    
    public Employee() {}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department != null){
            this.department = department;
            department.setEmployees(this);
        } else {
            this.department = null;
        }
    }
    
    
    public Long getId() {
        return employee_id;
    }

    public void setId(Long id) {
        this.employee_id = id;
    }

    
}
