package lk.ijse.gdse.ormcaursework.dao.custom.impl;

import lk.ijse.gdse.ormcaursework.bo.exeception.NotFoundException;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.dao.custom.TherapistDAO;
import lk.ijse.gdse.ormcaursework.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {

    @Override
    public boolean save(Therapist therapist, Session session){
        try {
            session.persist(therapist);
            session.flush();
            return true;
        }catch (Exception e) {
            throw new RuntimeException("Therapist saving failed in therapistDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(Therapist therapist, Session session) throws SQLException, ClassNotFoundException {
        try {
            session.merge(therapist);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Therapist update failed"+e.getMessage());
        }
    }
    @Override
    public List<Therapist> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
            List<Therapist> therapists = query.list();
            return therapists;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteByPk(String pk)  {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,pk);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }
            session.remove(therapist);
            transaction.commit();
            return true;
        } catch (NotFoundException e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override /* search in  therapy programs bo*/
    public Optional<Therapist> findByPK(String pk, Session session) {
        Therapist therapist = null;
        try {
            String sql = "SELECT * FROM therapist WHERE doctorID = :id";
            NativeQuery<Therapist> query = session.createNativeQuery(sql, Therapist.class);
            query.setParameter("id", pk);

            therapist = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(therapist);
    }


    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            String lastPk = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        }catch (Exception e) {
            throw new RuntimeException("Therapist lastPK not found"+e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
