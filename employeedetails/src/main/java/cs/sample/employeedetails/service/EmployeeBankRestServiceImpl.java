package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.BankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static cs.sample.employeedetails.utility.EmployeeConstants.*;

@Service
public class EmployeeBankRestServiceImpl implements EmployeeBankRestService{

    @Autowired
    RestTemplate restTemplate;

    public BankDTO getBankDetailByEmpId(Integer empId) {
        return restTemplate.getForObject(EMP_BANK_RT_GET_URL+ empId, BankDTO.class);

    }

    public List<BankDTO> getAllEmpBankDetails() {
        return restTemplate.exchange(EMP_BANK_RT_GET_ALL_URL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<BankDTO>>() {
                }).getBody();
    }

    @Override
    public ResponseEntity<BankDTO> saveBankDetails(BankDTO bank) {
        return restTemplate.postForEntity(EMP_BANK_RT_ADD_URL, bank, BankDTO.class);
    }
}
