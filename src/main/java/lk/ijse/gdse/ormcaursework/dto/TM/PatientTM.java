package lk.ijse.gdse.ormcaursework.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientTM {
    private String patientID;
    private String patientName;
    private String patientBirthDate;
    private String patientNIC;
    private String patientGender;
    private String patientAddress;
    private String patientPhone;
    private String patientEmail;
}
