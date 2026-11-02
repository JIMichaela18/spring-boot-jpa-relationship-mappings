package cs.sample.employeedetails.service;

import cs.sample.employeedetails.model.*;

import java.util.List;
import java.util.Optional;

public interface EmployeeMappingService {

    Employee findEmployeeById(Integer empId);

    Bank findBankById(Integer empId);

    Course findCourseById(Integer empId);

    Employee saveEmployee(Employee employee);

    List<Course> getCourse();

    List<Employee> saveAllEmployee(List<Employee> employee);

    List<Employee> findAllEmployee();

    List<Project> getProject();

    List<Team> getTeam();

    Team getTeamById(Integer teamId);

    Employee getEmpTeamById(Integer teamId);

    Customer getCustomerById(Integer customerId);
}
