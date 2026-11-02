package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.exception.CustomException;
import cs.sample.employeedetails.model.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract interface EmployeeService {
    public List<EmployeeDTO> findAllEmployee();
    public Employee saveEmployee(EmployeeDTO emp);
    public EmployeeDTO findEmployeeById(Integer empId);
    public List<EmployeeDTO> findEmployeeByGender(Integer gender);
    public Object getRestApiData(String uri) throws CustomException;
}
