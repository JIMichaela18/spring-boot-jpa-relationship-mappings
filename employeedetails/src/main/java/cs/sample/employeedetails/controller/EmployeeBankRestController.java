package cs.sample.employeedetails.controller;

import cs.sample.employeedetails.dto.BankDTO;
import cs.sample.employeedetails.service.EmployeeBankRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-rest")
public class EmployeeBankRestController  {

    @Autowired
    EmployeeBankRestService empBankRestService;

    @GetMapping("/getBankDetail/{empId}")
    public BankDTO getBankDetail(@PathVariable Integer empId){
        return empBankRestService.getBankDetailByEmpId(empId);
    }

    @GetMapping("/getBankDetails")
    public List<BankDTO> getAllEmpBankDetails(){
        return empBankRestService.getAllEmpBankDetails();
    }

    @PostMapping("/saveBankDetails")
    public ResponseEntity<BankDTO> saveBankDetails(@RequestBody BankDTO bank){
        return empBankRestService.saveBankDetails(bank);
    }

}
