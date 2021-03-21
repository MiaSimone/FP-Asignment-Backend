
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DepartmentDTO;
import dto.EmployeeDTO;
import dto.EmployeesDTO;
import entities.Employee;
import errorhandling.NotFoundException;
import exceptions.MissingInput;
import facades.DepartmentFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author miade
 */

@Path("department")
public class DepartmentResource {
    
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    private static final DepartmentFacade FACADE =  DepartmentFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Employee endpoint\"}";
    }
    
    @Path("all_employees")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        EmployeesDTO emps = FACADE.getAllEmployees();
        return GSON.toJson(emps);
    }
    
    @GET
    @Path("/search_employee/{input}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployee(@PathParam("input") int input) throws NotFoundException{
        EmployeeDTO empDTO = FACADE.getEmployee(input);
        
        return GSON.toJson(empDTO);
    }
    
    @GET
    @Path("/random")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomEmployee() throws NotFoundException{
        int randomNumber = randomGenerator();
        
        EmployeeDTO empDTO = FACADE.getEmployee(randomNumber);
        
        return GSON.toJson(empDTO);
    }

    private int randomGenerator() {
        EmployeesDTO emps = FACADE.getAllEmployees();
        List<Integer> randomList = new ArrayList<>();
        for (int i = 1; i < (emps.getAll().size()+1); i++) {
            randomList.add(i);
        }
        Random rand = new Random();
        
        int randomNumber = randomList.get(rand.nextInt(randomList.size()));
        
        return randomNumber;
        
    }
    
    
    @GET
    @Path("/search_department/{input}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getDepartment(@PathParam("input") int input) throws NotFoundException{
        System.out.println("Input: " + input);
        DepartmentDTO depDTO = FACADE.getDepartment(input);
        return GSON.toJson(depDTO);
    }
    
    
    
    @POST	
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addEmployee(String emp) throws MissingInput {
       EmployeeDTO empDTO = GSON.fromJson(emp, EmployeeDTO.class); 
       Employee e = new Employee(empDTO.getFirstName(), empDTO.getLastName(), empDTO.getEmail());
       
       String depCode = empDTO.getDepCode();
       String depName = empDTO.getDepName();
       String depDes = empDTO.getDepDescription();
       
       EmployeeDTO eAdded = FACADE.addEmployee(e, depCode, depName, depDes);
       return GSON.toJson(eAdded);
    }
    
    
    
}
