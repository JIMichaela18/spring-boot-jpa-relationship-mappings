package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.BankDTO;
import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.model.Bank;
import cs.sample.employeedetails.model.Employee;
import cs.sample.employeedetails.repo.EmployeeBankRepository;
import cs.sample.employeedetails.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

@Service
public class EmployeeBankAsyncServiceImpl implements EmployeeBankAsyncService{

    @Autowired
    EmployeeBankRepository empBankRepo;

    @Autowired
    EmployeeRepository empRepo;

    @Async("asyncExecutor")
    public CompletableFuture<Employee> findEmpDataByEmpIdUsingAsync(Integer empId) throws InterruptedException {
        System.out.println("In 1");
        Thread.sleep(6000);
        CompletableFuture<Employee> findEmpDataById = CompletableFuture.completedFuture(empRepo.findEmpDataByEmpIdUsingAsync(empId));
        System.out.println("Out 1");
        return findEmpDataById;
    }

    @Async("asyncExecutor")
    public CompletableFuture<Bank> findBankDataByEmpIdUsingAsync(Integer empId) throws InterruptedException {
        System.out.println("In 2");
        CompletableFuture<Bank> findBankDataById = CompletableFuture.completedFuture(empBankRepo.findBankDataByEmpIdUsingAsync(empId));
        System.out.println("Out 2");
        return findBankDataById;
    }

    public EmployeeBankDTO getEmpAndBankDataByEmpIdUsingAsync(Integer empId) throws ExecutionException, InterruptedException {
        System.out.println("START");
        CompletableFuture<Employee> getEmpData = findEmpDataByEmpIdUsingAsync(empId);
        CompletableFuture<Bank> getBankData = findBankDataByEmpIdUsingAsync(empId);
        CompletableFuture.allOf(getEmpData,getBankData).join();
        System.out.println("AFTER RESULTS");
        EmployeeBankDTO empBankData = EmployeeBankDTO.builder()
                .empId(getEmpData.get().getEmpId())
                .empName(getEmpData.get().getEmpName())
                .gender(getEmpData.get().getGender().getGender())
                .emailId(getEmpData.get().getEmailId())
                .designation(getEmpData.get().getDesignation())
                .city(getEmpData.get().getCity())
                .bank(new BankDTO(getBankData.get().getBankId(),getBankData.get().getBankName(), getBankData.get().getAccountNo(), getBankData.get().getIfscCode(), getBankData.get().getEmpId()))
                .build();
        System.out.println("END");
        return empBankData;
    }
}
