package lk.ijse.gdse.ormcaursework.bo.custom.impl;

import lk.ijse.gdse.ormcaursework.bo.custom.UserBO;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.dao.DAOFactory;
import lk.ijse.gdse.ormcaursework.dao.DAOType;
import lk.ijse.gdse.ormcaursework.dao.custom.UserDAO;
import lk.ijse.gdse.ormcaursework.dto.UserDTO;
import lk.ijse.gdse.ormcaursework.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User();
            user.setUserID(userDTO.getUserID());
            user.setUserFullName(userDTO.getUserFullName());
            user.setUserEmail(userDTO.getUserEmail());
            user.setUserRole(userDTO.getUserRole());
            user.setUserName(userDTO.getUserName());
            user.setUserPassword(userDTO.getUserPassword());

            boolean isSaved= userDAO.save(user,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error saving user");
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateUser(String UserName, String UserEmail, String UserNewPassword) {
        try {
            return userDAO.updateUser(UserName,UserEmail,UserNewPassword);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error updating user");
        }
    }
    @Override
    public boolean findUser(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            boolean exists = userDAO.findUser(username,session);
            if (!exists) {
                transaction.rollback();
                return false;
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding user");
        } finally {
            session.close();
        }
    }

    @Override
    public String findPassWord(String username,String role) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = userDAO.findPassWord(username,role, session);
            if (user != null) {
                transaction.commit();
                return user.getUserPassword();
            } else {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding password");
        } finally {
            session.close();
        }
    }

    @Override
    public List<UserDTO> getUserDetails(String UserName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            List<User> users = userDAO.getALLByUserName(UserName,session);
            List<UserDTO> userDTOList = new ArrayList<>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO(
                        user.getUserID(),
                        user.getUserFullName(),
                        user.getUserEmail(),
                        user.getUserRole(),
                        user.getUserName(),
                        user.getUserPassword()
                );
                userDTOList.add(userDTO);
            }
            return userDTOList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserDTO userDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class,userDTO.getUserID());
            if (user != null) {
                user.setUserFullName(userDTO.getUserFullName());
                user.setUserEmail(userDTO.getUserEmail());
            }else {
                System.out.println("user not found");
            }
            boolean isSaved= userDAO.update(user,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error Updating user");
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updatePassWord(UserDTO userDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class,userDTO.getUserID());
            if (user != null) {
                user.setUserName(userDTO.getUserName());
                user.setUserPassword(userDTO.getUserPassword());
            }
            boolean isSaved= userDAO.update(user,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error Updating user");
        }finally {
            session.close();
        }
    }

    @Override
    public String getNextID() {
        Optional<String> lastPkOptional = userDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("U", "")) + 1;
            return String.format("U%03d", nextId);
        } else {
            return "U001";
        }
    }
}
