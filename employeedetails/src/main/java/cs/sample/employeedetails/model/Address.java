package cs.sample.employeedetails.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address_info")
    private String addressInfo;

}
