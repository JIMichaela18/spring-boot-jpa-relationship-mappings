package cs.sample.employeedetails.repo;

import cs.sample.employeedetails.model.Employee;
import cs.sample.employeedetails.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeeByGender(Integer gender);

    @Query("FROM Employee WHERE empId = :empId")
    Employee findEmpDataByEmpIdUsingAsync(@Param("empId") Integer empId);

    @Query("from Employee where team_id = :teamId")
    Employee findEmployeeByTeamId(@Param("teamId") Integer teamId);
}
