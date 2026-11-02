package cs.sample.employeedetails.dto;

import cs.sample.employeedetails.model.Employee;
import cs.sample.employeedetails.model.Gender;
import org.springframework.beans.BeanUtils;

public class TransferDTOAndModel {

    public EmployeeDTO employeeToEmployeeDTO(Employee emp){
        EmployeeDTO empTransfer = new EmployeeDTO();
        BeanUtils.copyProperties(emp, empTransfer);
        GenderDTO genderTransfer = new GenderDTO(emp.getGender().getGenderCode(), emp.getGender().getGender());
        empTransfer.setGender(genderTransfer);
        return empTransfer;
    }

    public Employee employeeDTOToEmployee(EmployeeDTO empTransfer){
        Employee emp = new Employee();
        BeanUtils.copyProperties(empTransfer, emp);
        Gender gender = new Gender(empTransfer.getGender().getGenderCode(), empTransfer.getGender().getGender());
        emp.setGender(gender);
        return emp;
    }

}
