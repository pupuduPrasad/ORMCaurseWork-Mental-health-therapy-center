package lk.ijse.gdse.ormcaursework.dao.custom.impl;


import lk.ijse.gdse.ormcaursework.bo.exeception.DuplicateException;
import lk.ijse.gdse.ormcaursework.bo.exeception.NotFoundException;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.dao.custom.TProgramDAO;
import lk.ijse.gdse.ormcaursework.entity.TPrograms;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TProgramDAOImpl implements TProgramDAO {
    @Override
    public boolean save(TPrograms tPrograms, Session session) throws SQLException {
        try{
            session.persist(tPrograms);
            return true;
        }catch (DuplicateException e){
            throw new DuplicateException("Therapy already exists in therapistDAOImpl" + e.getMessage());
        }catch (Exception e){
            throw new SQLException("Therapy save failed in programDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(TPrograms tPrograms, Session session) {
        try {
            session.merge(tPrograms);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TPrograms> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<TPrograms> query = session.createQuery("from TPrograms ", TPrograms.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally{
            session.close();
        }
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TPrograms tPrograms = session.get(TPrograms.class, pk);
            if (tPrograms == null) {
                throw new NotFoundException("Program not found");
            }
            session.remove(tPrograms);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<TPrograms> findByPK(String pk, Session session) {
       return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            String lastPk = session
                    .createQuery("SELECT t.id FROM TPrograms t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }
}

