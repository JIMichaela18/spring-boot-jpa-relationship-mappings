package cs.sample.employeedetails.service;

import cs.sample.employeedetails.dto.EmployeeBankDTO;
import cs.sample.employeedetails.model.Bank;
import cs.sample.employeedetails.model.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface EmployeeBankAsyncService {
    CompletableFuture<Employee> findEmpDataByEmpIdUsingAsync(Integer empId) throws InterruptedException;
    CompletableFuture<Bank> findBankDataByEmpIdUsingAsync(Integer empId) throws InterruptedException;
    EmployeeBankDTO getEmpAndBankDataByEmpIdUsingAsync(Integer empId) throws ExecutionException, InterruptedException;
}
