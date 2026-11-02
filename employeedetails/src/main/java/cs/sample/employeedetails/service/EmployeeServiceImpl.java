package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.dto.TransferDTOAndModel;
import cs.sample.employeedetails.exception.CustomException;
import cs.sample.employeedetails.exception.CustomRuntimeException;
import cs.sample.employeedetails.model.Employee;
import cs.sample.employeedetails.repo.EmployeeRepository;
import cs.sample.employeedetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static cs.sample.employeedetails.utility.EmployeeConstants.CREATE_ERROR;
import static cs.sample.employeedetails.utility.EmployeeConstants.DATA_NOT_EXISTS;

@Service
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;


    public List<EmployeeDTO> findAllEmployee() {
        TransferDTOAndModel transferEmp = new TransferDTOAndModel();
        List<Employee> allEmployee = empRepo.findAll();
        if(allEmployee == null || allEmployee.isEmpty()){
            throw new NoSuchElementException(DATA_NOT_EXISTS);
        }
        List<EmployeeDTO> allEmployeeDTO = new ArrayList<>();
        for ( Employee emp : allEmployee ) allEmployeeDTO.add(transferEmp.employeeToEmployeeDTO(emp));
        return allEmployeeDTO;

    }

    @Transactional
    public Employee saveEmployee(EmployeeDTO empTransfer) {
        TransferDTOAndModel transferEmp = new TransferDTOAndModel();
        Employee empSave = empRepo.save(transferEmp.employeeDTOToEmployee(empTransfer));
        if(empSave.getEmpId() == null || empSave.getEmpId() == 0){
            throw new CustomRuntimeException(CREATE_ERROR, HttpStatus.BAD_REQUEST);
        }
        return empSave;
    }

    public EmployeeDTO findEmployeeById(Integer id) {
        TransferDTOAndModel transferEmp = new TransferDTOAndModel();
        Employee emp = empRepo.findById(id).get();
        if(emp.getEmpId() == null || emp.getEmpId() == 0) {
            throw new NoSuchElementException(DATA_NOT_EXISTS);
        }
        return transferEmp.employeeToEmployeeDTO(emp);
    }

    public List<EmployeeDTO> findEmployeeByGender(Integer gender){
        TransferDTOAndModel transferEmp = new TransferDTOAndModel();
        List<Employee> empByGender = empRepo.findEmployeeByGender(gender);
        if(empByGender == null || empByGender.isEmpty()){
            throw new NoSuchElementException(DATA_NOT_EXISTS);
        }
        List<EmployeeDTO> empByGenderDTO = new ArrayList<>();
        for ( Employee emp : empByGender ) empByGenderDTO.add(transferEmp.employeeToEmployeeDTO(emp));
        return empByGenderDTO;
    }

    public Object getRestApiData(String uri) throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        String result = null;
        try{
            if(!uri.contains("https")){
                throw new CustomException("Invalid Response from the given URL", HttpStatus.BAD_GATEWAY);
            }
            result = restTemplate.getForObject(uri, String.class);
        }catch (Exception e){
            throw new ResourceAccessException("Invalid Response from the given URL");
        }
        return result;
    }


}


