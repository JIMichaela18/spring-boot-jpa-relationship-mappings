package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.BankDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeBankRestService {
    BankDTO getBankDetailByEmpId(Integer empId);

    List<BankDTO> getAllEmpBankDetails();

    ResponseEntity<BankDTO> saveBankDetails(BankDTO bank);
}
