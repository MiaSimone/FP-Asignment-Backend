
package dto;

import entities.Employee;
import java.util.List;

/**
 *
 * @author miade
 */
public class EmployeeDTO {
    
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    
    private String depCode;
    private String depName;
    private String depDescription;
    
    public EmployeeDTO() {}

    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.firstName = e.getFirstName();
        this.lastName = e.getLastName();
        this.email = e.getEmail();
        this.depCode = e.getDepartment().getCode();
        this.depName = e.getDepartment().getDepartmentName();
        this.depDescription = e.getDepartment().getDepartmentDescription();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getDepCode() {
        return depCode;
    }
    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }
    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepDescription() {
        return depDescription;
    }
    public void setDepDescription(String depDescription) {
        this.depDescription = depDescription;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeDTO other = (EmployeeDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
