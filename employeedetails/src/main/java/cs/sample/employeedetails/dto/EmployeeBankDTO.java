package cs.sample.employeedetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeBankDTO {

    private Integer empId;

    private String empName;

    private String designation;

    private String gender;

    private String emailId;

    private String mobileNo;

    private String city;

    private BankDTO bank;

}
