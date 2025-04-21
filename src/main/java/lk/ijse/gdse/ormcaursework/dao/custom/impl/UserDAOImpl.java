package lk.ijse.gdse.ormcaursework.dao.custom.impl;

//import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
//import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
//import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
//import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import lk.ijse.gdse.ormcaursework.bo.exeception.NotFoundException;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.dao.custom.UserDAO;
import lk.ijse.gdse.ormcaursework.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean update(User user, Session session) throws SQLException, ClassNotFoundException {
        try{
            session.persist(user);
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Updating user failed");
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();;
        Query<User> query = session.createQuery("from User ", User.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();;
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, pk);
            if (user == null) {
                throw new NotFoundException("User not found");
            }
            session.remove(user);
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
    public Optional<User> findByPK(String pk,Session session) {
        return Optional.of(null);
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPk = session
                .createQuery("SELECT t.id FROM User t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public boolean save(User user, Session session)  {
        try{
            session.persist(user);
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Saving user failed");
        }
    }

    @Override
    public boolean updateUser(String userName, String userEmail, String userNewPassword) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<User> query = session.createQuery("FROM User WHERE userName = :username AND userEmail = :email", User.class);
            query.setParameter("username", userName);
            query.setParameter("email", userEmail);

            User user = query.uniqueResult();

            if (user != null) {
                user.setUserPassword(userNewPassword);
                session.update(user);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean findUser(String UserName, Session session) {
        Query<User> query = session.createQuery("FROM User WHERE userName = :username", User.class);
        query.setParameter("username", UserName);

        User user = query.uniqueResult();
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User findPassWord(String UserName,String role, Session session) {
        Query<User> query = session.createQuery("FROM User WHERE userName = :username AND userRole = :role", User.class);
        query.setParameter("username", UserName);
        query.setParameter("role", role);

        return query.uniqueResult(); // User object will contain the hashed password
    }

    @Override
    public List<User> getALLByUserName(String UserName,Session session) {
        return session.createQuery("FROM User WHERE userName = :username", User.class)
                .setParameter("username", UserName)
                .list();
    }
}
