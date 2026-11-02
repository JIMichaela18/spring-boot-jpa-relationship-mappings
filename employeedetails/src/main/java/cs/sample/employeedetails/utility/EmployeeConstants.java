package cs.sample.employeedetails.utility;

public class EmployeeConstants {
    public static final String REQUIRED_FIELD = "This field is required.";
    public static final String CHAR_AND_SPACE = "^[A-Za-z ]+$";
    public static final String CHAR_AND_SPACE_MSG = "Only Alphabets and space is allowed.";
    public static final String EMAIL_ID_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String MOBILE_NO_REGEX = "^[6-9]{1}[0-9]{9}$";
    public static final String EMP_NAME = "empName";
    public static final String DESIGNATION = "designation";
    public static final String EMAIL_ID = "emailId";
    public static final String MOBILE_NO = "mobileNo";
    public static final String GENDER = "gender";
    public static final String CITY = "city";
    public static final String COUNTRY_API = "https://countriesnow.space/api/v0.1/countries";
    public static final String CREATE = "CREATE";
    public static final String CREATE_SUCCESS =  "Employee Data Created Successfully";
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String DATA_NOT_EXISTS = "Employee data does not exists.";
    public static final String CREATE_ERROR =  "Employee Record Not Created.";
    public static final String EMP_BANK_RT_BASE_URL =  "http://localhost:8070/bank/api/";
    public static final String EMP_BANK_RT_GET_URL =  EMP_BANK_RT_BASE_URL + "getDetail/";
    public static final String EMP_BANK_RT_GET_ALL_URL =  EMP_BANK_RT_BASE_URL + "getAllDetails";
    public static final String EMP_BANK_RT_ADD_URL =  EMP_BANK_RT_BASE_URL + "save";
    public static final String FIND_EMPLOYEE_BY_EMP_ID_QUERY = "FROM Bank where empId =:empId";
    public static final String FIND_GENDER_BY_EMP_ID_QUERY = "SELECT e from Employee e JOIN Gender g ON e.gender = g.genderCode WHERE g.genderCode =:gender";
}
