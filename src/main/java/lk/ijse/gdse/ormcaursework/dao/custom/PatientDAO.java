package lk.ijse.gdse.ormcaursework.dao.custom;



import lk.ijse.gdse.ormcaursework.dao.CrudDAO;
import lk.ijse.gdse.ormcaursework.entity.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient,String> {
    List<Patient> searchPatientName(String searchByName);
    String search(String patientName);

}
