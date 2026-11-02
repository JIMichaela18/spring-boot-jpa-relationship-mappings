package cs.sample.employeedetails.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cs.sample.employeedetails.utility.EmployeeConstants.FIND_GENDER_BY_EMP_ID_QUERY;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "empId")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Employee.findEmployeeByGender",
            query = FIND_GENDER_BY_EMP_ID_QUERY)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name")
    private String empName;

    private String designation;

    @OneToOne
    @JoinColumn(name = "gender")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", fetch= FetchType.EAGER)
    @JsonManagedReference
    private Bank bank;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "emp_id",referencedColumnName="emp_id")
    private List<Course> courseList = new ArrayList<>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    /*@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @Fetch(FetchMode.JOIN)
    private Team team;*/

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_project",
            joinColumns = { @JoinColumn(name = "emp_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<Project> projects = new HashSet<>();

    public Employee(Integer empId, String empName, String designation, Gender gender, String emailId, String mobileNo, String city) {
        this.empId = empId;
        this.empName = empName;
        this.designation = designation;
        this.gender = gender;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.city = city;
    }

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_no")
    private String mobileNo;

    private String city;


    private static final Employee EMPLOYEE = new Employee();

    public static Employee getInstance() {
        return EMPLOYEE;
    }

}
