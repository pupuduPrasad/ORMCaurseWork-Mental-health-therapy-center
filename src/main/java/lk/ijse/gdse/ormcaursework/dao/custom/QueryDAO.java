package lk.ijse.gdse.ormcaursework.dao.custom;

//import lk.ijse.project.mentalHealthTherapyCenter.dto.MedicalHistoryDTO;
//import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientsInEveryProgramDTO;
//import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
//import lk.ijse.project.mentalHealthTherapyCenter.repostory.SuperDAO;

import lk.ijse.gdse.ormcaursework.dao.SuperDAO;
import lk.ijse.gdse.ormcaursework.dto.MedicalHistoryDTO;
import lk.ijse.gdse.ormcaursework.dto.PatientsInEveryProgramDTO;
import lk.ijse.gdse.ormcaursework.dto.ViewSessionDTO;

import java.util.List;

public interface QueryDAO  extends SuperDAO {
    List<ViewSessionDTO>getAllAppointments();
    List<MedicalHistoryDTO> getALLMedicalHistory();
    List<PatientsInEveryProgramDTO> getPatientsInEveryProgram();
}
