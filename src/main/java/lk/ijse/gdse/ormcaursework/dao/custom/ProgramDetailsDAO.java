package lk.ijse.gdse.ormcaursework.dao.custom;


import lk.ijse.gdse.ormcaursework.dao.CrudDAO;
import lk.ijse.gdse.ormcaursework.entity.ProgramDetails;
import org.hibernate.Session;

import java.util.List;

public interface ProgramDetailsDAO extends CrudDAO<ProgramDetails,String> {
    List<ProgramDetails> getByPatientAndSession(String patientID, String id , Session session);
}
