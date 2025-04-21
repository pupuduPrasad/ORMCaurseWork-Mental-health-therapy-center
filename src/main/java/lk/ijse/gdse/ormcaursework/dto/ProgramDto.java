package lk.ijse.gdse.ormcaursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor /*pop up window tm*/
public class ProgramDto {
    private String therapyID;
    private String therapyName;
    private String therapyDescription;
    private Double therapyFee;
}
