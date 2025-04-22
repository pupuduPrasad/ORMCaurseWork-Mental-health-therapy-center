package lk.ijse.gdse.ormcaursework.bo.custom;


import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.*;

import java.util.List;
import java.util.Optional;

public interface AppointmentBO extends SuperBO {
    boolean addAppointment(ProgramDetailsDTO programDetailsDTO , SessionDTO sessionDTO, PaymentDTO paymentDTO);
    boolean updateAppointments(ProgramDetailsDTO programDetailsDTO,SessionDTO sessionDTO,PaymentDTO paymentDTO);
    String getNextPatientID();
    String getNextSessionID();
    String getNextPaymentID();
    List<PatientDTO> searchPatientBYName(String searchBYName);
    List<ViewSessionDTO>getAllAppointments();
    List<String> loadDoctorIds() throws Exception;
    String searchPatientID(String patientName);
    boolean cancelAppointment(String id);
    Optional<String> getLastAptID();
}
