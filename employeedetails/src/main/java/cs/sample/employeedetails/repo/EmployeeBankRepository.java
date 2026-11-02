package cs.sample.employeedetails.repo;

import cs.sample.employeedetails.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import static cs.sample.employeedetails.utility.EmployeeConstants.*;

@Repository
public interface EmployeeBankRepository extends JpaRepository<Bank, Integer>{
    @Query(FIND_EMPLOYEE_BY_EMP_ID_QUERY)
    Bank findBankDataByEmpIdUsingAsync(@Param("empId") Integer empId);
}
