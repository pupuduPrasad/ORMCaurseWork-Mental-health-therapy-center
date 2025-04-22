package lk.ijse.gdse.ormcaursework.bo.custom.impl;




import lk.ijse.gdse.ormcaursework.bo.custom.PatientBO;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.dao.DAOFactory;
import lk.ijse.gdse.ormcaursework.dao.DAOType;
import lk.ijse.gdse.ormcaursework.dao.custom.PatientDAO;
import lk.ijse.gdse.ormcaursework.dao.custom.QueryDAO;
import lk.ijse.gdse.ormcaursework.dto.*;
import lk.ijse.gdse.ormcaursework.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = new Patient();
            patient.setPatientID(patientDTO.getPatientID());
            patient.setPatientName(patientDTO.getPatientName());
            patient.setPatientBirthDate(patientDTO.getPatientBirthDate());
            patient.setPatientNIC(patientDTO.getPatientNIC());
            patient.setPatientGender(patientDTO.getPatientGender());
            patient.setPatientAddress(patientDTO.getPatientAddress());
            patient.setPatientPhone(patientDTO.getPatientPhone());
            patient.setPatientEmail(patientDTO.getPatientEmail());

           boolean isUpdated = patientDAO.update(patient,session);
           if (isUpdated) {
               transaction.commit();
               return true;
           }else {
               transaction.rollback();
               return false;
           }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Update failed");
        }finally {
            session.close();
        }
    }
    @Override
    public boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = new Patient();
            patient.setPatientID(patientDTO.getPatientID());
            patient.setPatientName(patientDTO.getPatientName());
            patient.setPatientBirthDate(patientDTO.getPatientBirthDate());
            patient.setPatientNIC(patientDTO.getPatientNIC());
            patient.setPatientGender(patientDTO.getPatientGender());
            patient.setPatientAddress(patientDTO.getPatientAddress());
            patient.setPatientPhone(patientDTO.getPatientPhone());
            patient.setPatientEmail(patientDTO.getPatientEmail());

            boolean isUpdated = patientDAO.save(patient,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Update failed");
        }finally {
            session.close();
        }
    }
    @Override
    public List<PatientDTO> getALL() throws Exception {
        List<Patient> patients = patientDAO.getAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatientID(),
                    patient.getPatientName(),
                    patient.getPatientBirthDate(),
                    patient.getPatientNIC(),
                    patient.getPatientGender(),
                    patient.getPatientAddress(),
                    patient.getPatientPhone(),
                    patient.getPatientEmail()
            ));
        }
        return patientDTOS;
    }

    @Override
    public boolean deletePatient(String patientID) throws SQLException, ClassNotFoundException {
        try{
            return patientDAO.deleteByPk(patientID);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("Delete failed");
        }
    }
    @Override
    public String getNextPatientID() {
        Optional<String> lastPkOptional = patientDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;  // Extract number and increment
            return String.format("P%03d", nextId);
        } else {
            return "P001";
        }
    }

    @Override
    public List<MedicalHistoryDTO> getPatientHistory(){
        return queryDAO.getALLMedicalHistory();
    }

    @Override
    public List<PatientsInEveryProgramDTO> getPatientsInEveryProgram() {
        return queryDAO.getPatientsInEveryProgram();
    }
}
