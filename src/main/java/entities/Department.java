/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author miade
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Department.getById", query = "SELECT d FROM Department d WHERE d.id= :id"),
    @NamedQuery(name = "Department.getAllRows", query = "SELECT d from Department d")
})

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    private String name;
    private String description;
    
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList();

    
    public Department() {}

    public Department(String code, String name, String des) {
        this.code = code;
        this.name = name;
        this.description = des;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartmentName() {
        return name;
    }
    public void setDepartmentName(String departmentName) {
        this.name = departmentName;
    }

    public String getDepartmentDescription() {
        return description;
    }
    public void setDepartmentDescription(String departmentDescription) {
        this.description = departmentDescription;
    }

    
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Employee emp) {
        if (emp != null){
            employees.add(emp);
        }
    }

    
}
