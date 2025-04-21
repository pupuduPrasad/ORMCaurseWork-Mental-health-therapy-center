package lk.ijse.gdse.ormcaursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViewSessionDTO {
    private String sessionID;
    private String sessionDate;
    private String sessionNotes;
    private String sessionTime;
    private String doctorID;
    private List<String> programs;  // Store program IDs as a list
    private String patientName;
    private String paymentID;
    private Double paymentAmount;
    private String paymentMethod;
    private String appointmentStatus;
}
