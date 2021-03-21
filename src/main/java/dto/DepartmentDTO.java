
package dto;

import entities.Department;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miade
 */
public class DepartmentDTO {
    
    private long id;
    private String code;
    private String name;
    private String description;
    

    public DepartmentDTO() {}

    public DepartmentDTO(Department d) {
        this.id = d.getId();
        this.code = d.getCode();
        this.name = d.getDepartmentName();
        this.description = d.getDepartmentDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final DepartmentDTO other = (DepartmentDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
