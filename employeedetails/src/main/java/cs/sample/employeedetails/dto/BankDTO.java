package cs.sample.employeedetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankDTO {

    private Integer bankId;

    private String bankName;

    private String accountNo;

    private String ifscCode;

    private Integer empId;

}
