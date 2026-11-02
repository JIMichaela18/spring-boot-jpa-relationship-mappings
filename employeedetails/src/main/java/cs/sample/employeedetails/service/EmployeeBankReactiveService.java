package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.dto.BankDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeBankReactiveService {

     Mono<BankDTO> getBankDetailByEmpId(Integer empId);

     Flux<BankDTO> getAllEmpBankDetails();

     Mono<BankDTO> saveBankDetails(BankDTO bank);

     ResponseEntity<BankDTO> getBankDetailByEmpIdUsingSync(Integer empId);

     Mono<EmployeeBankDTO> getEmpDetailByEmpId(Integer empId);

     public Mono<EmployeeBankDTO> getEmpBankDetails(Integer empId);

}
