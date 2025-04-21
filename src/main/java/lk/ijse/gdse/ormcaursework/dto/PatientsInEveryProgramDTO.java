package lk.ijse.gdse.ormcaursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientsInEveryProgramDTO {
    String patientId;
    String patientName;
    String patientAddress;
    String patientContact;
}
