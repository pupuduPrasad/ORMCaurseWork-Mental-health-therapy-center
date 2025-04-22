package lk.ijse.gdse.ormcaursework.bo.custom;



import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.MedicalHistoryDTO;
import lk.ijse.gdse.ormcaursework.dto.PatientDTO;
import lk.ijse.gdse.ormcaursework.dto.PatientsInEveryProgramDTO;

import java.sql.SQLException;
import java.util.List;

public interface PatientBO extends SuperBO {
    boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    List<PatientDTO> getALL() throws Exception;
    boolean deletePatient(String patientID) throws SQLException, ClassNotFoundException;
    String getNextPatientID();
    List<MedicalHistoryDTO> getPatientHistory() throws SQLException, ClassNotFoundException;
    List<PatientsInEveryProgramDTO> getPatientsInEveryProgram();
}
