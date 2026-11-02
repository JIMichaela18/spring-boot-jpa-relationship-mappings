package cs.sample.employeedetails.service;

import cs.sample.employeedetails.model.*;
import cs.sample.employeedetails.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Repository
public class EmployeeMappingServiceImpl implements EmployeeMappingService{

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmployeeBankRepository empBankRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Override
    public Employee findEmployeeById(Integer empId) {
        Optional<Employee> findEmpById = empRepo.findById(empId);
        return findEmpById.get();
    }

    @Override
    public Bank findBankById(Integer empId) {
        Optional<Bank> findBankDataById = empBankRepo.findById(empId);
        return findBankDataById.get();
    }

    @Override
    public Course findCourseById(Integer empId) {
        Optional<Course> findCourseDataById = courseRepo.findById(empId);
        return findCourseDataById.get();
    }

    @Override
    public Employee saveEmployee(Employee emp) {
        return empRepo.save(emp);
    }

    public List<Course> getCourse(){
        return courseRepo.findAll();
    }

    @Override
    public List<Employee> saveAllEmployee(List<Employee> employee) {
        return empRepo.saveAll(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return  empRepo.findAll();
    }

    @Override
    public List<Project> getProject() {
        return projectRepo.findAll();
    }

    @Override
    public List<Team> getTeam() {
        return teamRepo.findAll();
    }

    @Override
    public Team getTeamById(Integer teamId) {
        return teamRepo.findById(teamId).get();
    }

    @Override
    public Employee getEmpTeamById(Integer teamId) {
        return empRepo.findEmployeeByTeamId(teamId);
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return custRepo.getById(customerId);
    }
}
