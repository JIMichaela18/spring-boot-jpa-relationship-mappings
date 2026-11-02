package cs.sample.employeedetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderDTO {

    private Integer genderCode;
    private String gender;

    private static final GenderDTO GenderDTO = new GenderDTO();

    public static GenderDTO getInstance() {
        return GenderDTO;
    }

}
