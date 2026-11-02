package cs.sample.employeedetails.controller;

import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.exception.CustomException;
import cs.sample.employeedetails.dto.MessageInfo;
import cs.sample.employeedetails.dto.ResponseMessage;
import cs.sample.employeedetails.service.EmployeeBankAsyncService;
import cs.sample.employeedetails.service.EmployeeService;
import cs.sample.employeedetails.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static cs.sample.employeedetails.utility.EmployeeConstants.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private EmployeeBankAsyncService empBankService;

    //test
    @GetMapping("/sayHello")
    public String hello(){
        return "hello";
    }

    //Read Employees
    @GetMapping("/viewEmployees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeDetails(){
        List<EmployeeDTO> findAllEmployee = empService.findAllEmployee();
        return new ResponseEntity<>(findAllEmployee, HttpStatus.OK);
    }

    //Read Employee By Id
    @GetMapping("/viewEmployee/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetailsById(@PathVariable Integer empId){
        EmployeeDTO emp = empService.findEmployeeById(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    //Create Employee
    @PostMapping("/addEmployee")
    public ResponseEntity<ResponseMessage> addEmployee(@RequestBody EmployeeDTO employee) {
        ResponseMessage respMsg = ResponseMessage.getInstance();
        List<MessageInfo> msgList =  EmployeeValidator.empValidation(employee);
        if(msgList.isEmpty()) {
            empService.saveEmployee(employee);
            respMsg.setStatus(SUCCESS);
            msgList.add(MessageInfo.setMessage(CREATE, CREATE_SUCCESS));
            respMsg.setMessageInfo(msgList);
            return new ResponseEntity<>(respMsg, HttpStatus.OK);
        } else {
            respMsg.setStatus(ERROR);
            respMsg.setMessageInfo(msgList);
            return new ResponseEntity<>(respMsg, HttpStatus.BAD_REQUEST);
        }
    }

    //Read Employee Data By Gender
    @GetMapping("/getDataByGender")
    public ResponseEntity<List<EmployeeDTO>> getRestApiData(@RequestParam Integer gender){
        List<EmployeeDTO> employeeList = empService.findEmployeeByGender(gender);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    //Read Data using REST Template
    @GetMapping("/restApiData")
    public ResponseEntity<Object> getRestApiData() throws CustomException {
        String uri = COUNTRY_API;
        //String uri = "https://countriesnow";//I/O Exception unknown host
        Object result = empService.getRestApiData(uri);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //ASYNC
    @GetMapping("/viewEmployeeAsync/{empId}")
    public ResponseEntity<EmployeeBankDTO> getEmployeeDetailsByIdAsync(@PathVariable Integer empId) throws ExecutionException, InterruptedException {
        EmployeeBankDTO emp = empBankService.getEmpAndBankDataByEmpIdUsingAsync(empId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
