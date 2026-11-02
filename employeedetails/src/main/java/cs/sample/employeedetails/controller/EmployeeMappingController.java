package cs.sample.employeedetails.controller;

import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.dto.MessageInfo;
import cs.sample.employeedetails.dto.ResponseMessage;
import cs.sample.employeedetails.model.*;
import cs.sample.employeedetails.service.EmployeeMappingService;
import cs.sample.employeedetails.service.EmployeeService;
import cs.sample.employeedetails.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static cs.sample.employeedetails.utility.EmployeeConstants.*;
import static cs.sample.employeedetails.utility.EmployeeConstants.ERROR;

@RestController
@RequestMapping("/employee-mapping")
public class EmployeeMappingController {

    @Autowired
    private EmployeeMappingService empMapService;

    @GetMapping("/viewEmployee/{empId}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable Integer empId){
        Employee emp = empMapService.findEmployeeById(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/viewBank/{empId}")
    public ResponseEntity<Bank> getBankDetailsById(@PathVariable Integer empId){
        Bank bank = empMapService.findBankById(empId);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
            //Bank bank = employee.getBank();
            Employee emp = empMapService.saveEmployee(employee);
            return new ResponseEntity<>(emp, HttpStatus.BAD_REQUEST);
    }

    /*@PostMapping("/addAllEmployee")
    public ResponseEntity<List<Employee>> addAllEmployee(@RequestBody List<Employee> employee) {
        List<Employee> emp = (List<Employee>) empMapService.saveAllEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.BAD_REQUEST);
    }*/

    @GetMapping("/viewEmployees")
    public ResponseEntity<List<Employee>> getEmployeeList(){
        List<Employee> emp = empMapService.findAllEmployee();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/viewCourse/{empId}")
    public ResponseEntity<Course> getCourseDetailsById(@PathVariable Integer empId){
        Course course = empMapService.findCourseById(empId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/viewCourses")
    public ResponseEntity<List<Course>> getCourseList(){
        List<Course> course = empMapService.getCourse();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/viewProjects")
    public ResponseEntity<List<Project>> getProjectList(){
        List<Project> project = empMapService.getProject();
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/viewTeams")
    public ResponseEntity<List<Team>> getTeamList(){
        List<Team> teams = empMapService.getTeam();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/viewTeams/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Integer teamId){
        Team teams = empMapService.getTeamById(teamId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/viewEmployeeByTeamId/{teamId}")
    public ResponseEntity<Employee> getEmpTeamById(@PathVariable Integer teamId){
        Employee emp = empMapService.getEmpTeamById(teamId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/viewCustomerById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId){
        Customer customer = empMapService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
