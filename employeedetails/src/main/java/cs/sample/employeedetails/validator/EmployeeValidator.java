package cs.sample.employeedetails.validator;
import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.dto.MessageInfo;

import java.util.ArrayList;
import java.util.List;

import static cs.sample.employeedetails.utility.EmployeeConstants.*;

public class EmployeeValidator {

    private EmployeeValidator(){}

    private static List<MessageInfo> listErrorMsg = new ArrayList<>();

    public static List<MessageInfo> empValidation(EmployeeDTO emp)
    {
        listErrorMsg = new ArrayList<>();
        validateEmployeeName(emp.getEmpName());
        validateDesignation(emp.getDesignation());
        validateGender(emp.getGender().getGenderCode());
        validateEmailId(emp.getEmailId());
        validateMobileNo(emp.getMobileNo());
        validateCity(emp.getCity());
        return listErrorMsg;
    }

    private static void validateEmployeeName(String empName)
    {
        if(empName==null || empName.isEmpty())
        {
            listErrorMsg.add(MessageInfo.setMessage(EMP_NAME, REQUIRED_FIELD));
        }
        else if(!empName.matches(CHAR_AND_SPACE)){
            listErrorMsg.add(MessageInfo.setMessage(EMP_NAME, CHAR_AND_SPACE_MSG));
        }
    }

    private static void validateDesignation(String designation) {
        if(designation==null || designation.isEmpty())
        {
            listErrorMsg.add(MessageInfo.setMessage(DESIGNATION, REQUIRED_FIELD));
        }
        else if(!designation.matches(CHAR_AND_SPACE))
        {
            listErrorMsg.add(MessageInfo.setMessage(DESIGNATION, CHAR_AND_SPACE_MSG));
        }
    }

    private static void validateGender(Integer gender) {
        if(gender == null || gender == 0)
        {
            listErrorMsg.add(MessageInfo.setMessage(GENDER, REQUIRED_FIELD));
        }
        else if(!gender.equals(1) || gender.equals(2)){
            listErrorMsg.add(MessageInfo.setMessage(GENDER, CHAR_AND_SPACE_MSG));
        }
    }

    private static void validateEmailId(String emailId) {
        if(emailId==null || emailId.isEmpty())
        {
            listErrorMsg.add(MessageInfo.setMessage(EMAIL_ID, REQUIRED_FIELD));
        }
        else if(!emailId.matches(EMAIL_ID_REGEX))
        {
            listErrorMsg.add(MessageInfo.setMessage(EMAIL_ID, "Please Enter a Valid EmailId."));
        }
    }

    private static void validateMobileNo(String mobileNo) {
        if(mobileNo==null || mobileNo.isEmpty())
        {
            listErrorMsg.add(MessageInfo.setMessage(MOBILE_NO, REQUIRED_FIELD));
        }
        else if(!mobileNo.matches(MOBILE_NO_REGEX))
        {
            listErrorMsg.add(MessageInfo.setMessage(MOBILE_NO,"Please Enter a valid Phone Number."));
        }
    }

    private static void validateCity(String city) {
        if(city==null || city.isEmpty())
        {
            listErrorMsg.add(MessageInfo.setMessage(CITY, REQUIRED_FIELD));
        }
        else if(!city.matches(CHAR_AND_SPACE)){
            listErrorMsg.add(MessageInfo.setMessage(CITY, CHAR_AND_SPACE_MSG));
        }
    }

}
