package cs.sample.employeedetails.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gender_code")
    private Integer genderCode;
    private String gender;


    private static final Gender GENDER = new Gender();

    public static Gender getInstance() {
        return GENDER;
    }
}
