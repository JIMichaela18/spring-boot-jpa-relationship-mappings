package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.dto.BankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static cs.sample.employeedetails.utility.EmployeeConstants.*;

@Service
public class EmployeeBankReactiveServiceImpl implements EmployeeBankReactiveService {

    @Autowired
    WebClient webClient;

    public Mono<BankDTO> getBankDetailByEmpId(Integer empId) {
        System.out.println("Hi Inside getBankDetailByEmpId");
        return webClient.get()
                .uri("/getDetail/" + empId)
                .retrieve()
                .bodyToMono(BankDTO.class);
    }

    public Flux<BankDTO> getAllEmpBankDetails() {
        return webClient.get()
                .uri("/getAllDetails")
                .retrieve()
                .bodyToFlux(BankDTO.class)
                .timeout(Duration.ofMillis(10_000));
    }

    public Mono<BankDTO> saveBankDetails(BankDTO bank) {
        return webClient.post()
                .uri("/save")
                .body(Mono.just(bank), BankDTO.class)
                .retrieve()
                .bodyToMono(BankDTO.class)
                .timeout(Duration.ofMillis(10000));
    }

    public ResponseEntity<BankDTO> getBankDetailByEmpIdUsingSync(Integer empId) {
        return WebClient.create(EMP_BANK_RT_BASE_URL).get()
                .uri("/getDetail/" + empId)
                .retrieve()
                .toEntity(BankDTO.class).block();
    }

    public Mono<EmployeeBankDTO> getEmpDetailByEmpId(Integer empId) {
        return webClient.get()
                .uri("-employee/getDetail/" + empId)
                .retrieve()
                .bodyToMono(EmployeeBankDTO.class);
    }

    public Mono<EmployeeBankDTO> getEmpBankDetails(Integer empId){
        System.out.println("1--START");
        Mono<EmployeeBankDTO> employeeData = this.getEmpDetailByEmpId(empId);
        System.out.println("1--END");
        System.out.println("2--START");
        Mono<BankDTO> bankData = this.getBankDetailByEmpId(empId);
        System.out.println("2--END");
        System.out.println("3--START");
        Mono<EmployeeBankDTO> combineData = Mono.zip(employeeData, bankData).map(employeeAddData -> {
            System.out.println("3 In");
            employeeAddData.getT1().setBank(employeeAddData.getT2());
            return employeeAddData.getT1();
        });
        System.out.println("3--END");
        System.out.println("4--LAST");
        //combineData.subscribe(cd -> System.out.println(cd.getEmpName()));
        return combineData;
    }

}
