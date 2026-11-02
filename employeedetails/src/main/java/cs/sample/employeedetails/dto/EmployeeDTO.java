package cs.sample.employeedetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Integer empId;

    private String empName;

    private String designation;

    private GenderDTO gender;

    private String emailId;

    private String mobileNo;

    private String city;

    private static final EmployeeDTO EMPLOYEE = new EmployeeDTO();

    public EmployeeDTO() {}

    public static EmployeeDTO getInstance() {
        return EMPLOYEE;
    }

}
