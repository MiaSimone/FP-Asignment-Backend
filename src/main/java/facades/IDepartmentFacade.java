
package facades;

import dto.DepartmentDTO;
import dto.EmployeeDTO;
import dto.EmployeesDTO;
import entities.Employee;
import errorhandling.NotFoundException;
import exceptions.MissingInput;
import java.util.List;

/**
 *
 * @author miade
 */
public interface IDepartmentFacade {
    
    public EmployeesDTO getAllEmployees(); 
    public EmployeeDTO getEmployee(int id) throws NotFoundException;
    public DepartmentDTO getDepartment(int id) throws NotFoundException;
    public EmployeeDTO addEmployee(Employee emp, String depCode, String depName, String depDes) throws MissingInput;   
}
