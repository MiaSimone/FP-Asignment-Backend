
package dto;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miade
 */
public class EmployeesDTO {
    
    
    List<EmployeeDTO> all = new ArrayList();

    public EmployeesDTO(List<Employee> employeeEntities) {
        employeeEntities.forEach((e) -> {
            all.add(new EmployeeDTO(e));
        });
    }

    public List<EmployeeDTO> getAll() {
        return all;
    }
}
