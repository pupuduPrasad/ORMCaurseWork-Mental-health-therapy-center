package lk.ijse.gdse.ormcaursework.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientsInEveryProgramTM {
    String patientId;
    String patientName;
    String patientAddress;
    String patientContact;
}
