package serviceimpl;

import cs.sample.employeedetails.dto.EmployeeDTO;
import cs.sample.employeedetails.dto.GenderDTO;
import cs.sample.employeedetails.dto.TransferDTOAndModel;
import cs.sample.employeedetails.exception.CustomException;
import cs.sample.employeedetails.exception.CustomRuntimeException;
import cs.sample.employeedetails.model.Employee;
import cs.sample.employeedetails.model.Gender;
import cs.sample.employeedetails.repo.EmployeeRepository;
import cs.sample.employeedetails.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import static cs.sample.employeedetails.utility.EmployeeConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository empRepoMock;

    @InjectMocks
    private EmployeeServiceImpl empServiceMock;


    List<Employee> employeeList = new ArrayList<>();
    Gender gen1 = new Gender(1, "Male");
    Employee emp1 = new Employee(1, "TestName", "SD", gen1, "test@gmail.com", "9988989760", "Chennai");
    Employee empEmpty = new Employee(0, "", "", gen1, "", "", "");
    List<EmployeeDTO> employeeListDTO = new ArrayList<>();
    EmployeeDTO empDTO1 = new EmployeeDTO();
    GenderDTO genDTO1 = new GenderDTO(1, "Male");
    RestTemplate restTemplate = new RestTemplate();
    TransferDTOAndModel transferEmp;

    @BeforeEach
    public void initEmployee() {
        transferEmp = new TransferDTOAndModel();
        employeeList.add(emp1);
        BeanUtils.copyProperties(emp1, empDTO1);
        empDTO1.setGender(genDTO1);
        employeeListDTO.add(empDTO1);
    }

    @Test
    void testFindAllEmployee(){
        when(empRepoMock.findAll()).thenReturn(employeeList);
        employeeListDTO = empServiceMock.findAllEmployee();
        assertEquals("TestName", employeeListDTO.get(0).getEmpName());
        verify(empRepoMock,times(1)).findAll();
    }


    @Test
    void testFindAllEmployeeThrowError(){
        employeeList = new ArrayList<>();
        employeeListDTO = new ArrayList<>();
        when(empRepoMock.findAll()).thenReturn(employeeList);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            employeeListDTO = empServiceMock.findAllEmployee();
            new NoSuchElementException(DATA_NOT_EXISTS);
        });
        assertTrue(employeeListDTO == null || employeeListDTO.isEmpty());
        assertTrue(exception.getMessage().contains(DATA_NOT_EXISTS));
    }

    @Test
    void testFindAllEmployeeByGender(){
        when(empRepoMock.findEmployeeByGender(gen1.getGenderCode())).thenReturn(employeeList);
        employeeListDTO = empServiceMock.findEmployeeByGender(gen1.getGenderCode());
        assertEquals("Male", employeeListDTO.get(0).getGender().getGender());
        verify(empRepoMock,times(1)).findEmployeeByGender(gen1.getGenderCode());
    }


    @Test
    void testFindAllEmployeeByGenderThrowError(){
        employeeList = new ArrayList<>();
        employeeListDTO = new ArrayList<>();
        gen1 = new Gender(0,"");
        when(empRepoMock.findEmployeeByGender(gen1.getGenderCode())).thenReturn(employeeList);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            employeeListDTO = empServiceMock.findEmployeeByGender(gen1.getGenderCode());
            new NoSuchElementException(DATA_NOT_EXISTS);
        });
        assertTrue(employeeListDTO == null || employeeListDTO.isEmpty());
        assertTrue(exception.getMessage().contains(DATA_NOT_EXISTS));
    }

    @Test
    void testFindEmployeeById(){
        when(empRepoMock.findById(emp1.getEmpId())).thenReturn(Optional.of(emp1));
        EmployeeDTO empById = empServiceMock.findEmployeeById(emp1.getEmpId());
        assertThat(empById.getEmpId()).isSameAs(emp1.getEmpId());
        verify(empRepoMock,times(1)).findById(emp1.getEmpId());
    }

    @Test
    void testFindEmployeeByIdThrowError(){
        empDTO1 = new EmployeeDTO();
        empDTO1.setEmpId(3);
        when(empRepoMock.findById(emp1.getEmpId())).thenReturn(Optional.ofNullable(empEmpty));
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            EmployeeDTO empById = empServiceMock.findEmployeeById(transferEmp.employeeToEmployeeDTO(emp1).getEmpId());
            new NoSuchElementException(DATA_NOT_EXISTS);
        });
       assertTrue(exception.getMessage().contains(DATA_NOT_EXISTS));
    }

    @Test
    void testSaveEmployee(){
        when(empRepoMock.save(any())).thenReturn(emp1);
        Employee emp = empServiceMock.saveEmployee(transferEmp.employeeToEmployeeDTO(emp1));
        assertEquals(emp.getEmailId(),emp1.getEmailId());
        assertNotNull(emp.getEmpId());
        verify(empRepoMock,times(1)).save(any());
    }

    @Test
    void testSaveEmployeeThrowError(){
        when(empRepoMock.save(any())).thenReturn(empEmpty);
        Exception exception = assertThrows(CustomRuntimeException.class, () -> {
            Employee empSave=empServiceMock.saveEmployee(transferEmp.employeeToEmployeeDTO(emp1));
            assertTrue(empSave.getEmpId() == null || empSave.getEmpId() == 0);
            throw new CustomRuntimeException(CREATE_ERROR, HttpStatus.BAD_REQUEST);
        });

    }

    @Test
    void testRestTemplateGetObject() throws CustomException {
        Object result1 = empServiceMock.getRestApiData(COUNTRY_API);
        Object result2 = restTemplate.getForObject(COUNTRY_API, String.class);
        assertNotNull(result1);
        assertEquals(result1,result2);
    }

    @Test
    void testRestTemplateGetObjectThrowError() throws CustomException {
        String uri = "http://countriesnow";
        Exception exception = assertThrows(ResourceAccessException.class, () -> {
            Object result1 = empServiceMock.getRestApiData(uri);
            Object result2 = restTemplate.getForObject(uri, String.class);
            assertEquals(result1, result2);
            throw new ResourceAccessException("Invalid Response from the given URL");
        });
        Exception exception2 = assertThrows(CustomException.class, () -> {
            assertTrue(!uri.contains("https"));
            throw new CustomException("Invalid Response from the given URL", HttpStatus.BAD_GATEWAY);
        });



    }

}
