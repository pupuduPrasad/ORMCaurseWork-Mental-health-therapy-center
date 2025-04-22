package lk.ijse.gdse.ormcaursework.bo.custom;

import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.DoctorDTO;

import java.sql.SQLException;
import java.util.List;

public interface TherapistBO extends SuperBO {
    List<DoctorDTO> getALLDoctors() throws Exception;
    boolean saveTherapist(DoctorDTO doctorDTO);
    boolean updateTherapist(DoctorDTO doctorDTO);
    boolean deleteTherapist(String DoctorID) throws SQLException, ClassNotFoundException;
    String getNextTherapyID();
    List<DoctorDTO>getDocNames() throws Exception;
}
