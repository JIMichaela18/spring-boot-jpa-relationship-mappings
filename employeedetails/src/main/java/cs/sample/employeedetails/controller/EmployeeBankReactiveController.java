package cs.sample.employeedetails.controller;

import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.dto.BankDTO;
import cs.sample.employeedetails.service.EmployeeBankReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeBankReactiveController {

    @Autowired
    EmployeeBankReactiveService empBankReactiveService;

    @GetMapping("/getBankDetail/{empId}")
    public Mono<BankDTO> getBankDetail(@PathVariable Integer empId){
        return empBankReactiveService.getBankDetailByEmpId(empId);
    }

    @GetMapping("/getBankDetails")
    public Flux<BankDTO> getAllEmpBankDetails(){
        return empBankReactiveService.getAllEmpBankDetails();
    }

    @PostMapping("/saveBankDetails")
    public Mono<BankDTO> saveBankDetails(@RequestBody BankDTO bankDTO){
        return empBankReactiveService.saveBankDetails(bankDTO);
    }

    //sync call
    @GetMapping("/getBankDetailSync/{empId}")
    public ResponseEntity<BankDTO> getBankDetailSync(@PathVariable Integer empId){
        return empBankReactiveService.getBankDetailByEmpIdUsingSync(empId);
    }

   @GetMapping("/getBankAndEmpDetails/{empId}")
   public Mono<EmployeeBankDTO> getBankAndEmpDetails(@PathVariable Integer empId){
       return empBankReactiveService.getEmpBankDetails(empId);
   }

}
