package lk.ijse.gdse.ormcaursework.bo.custom;



import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
    boolean updateUser(String UserName,String UserEmail, String UserNewPassword);
    boolean findUser(String UserName);
    String getNextID();
    String findPassWord(String username, String role);
    List<UserDTO> getUserDetails(String UserName);
    boolean update(UserDTO userDTO);
    boolean updatePassWord(UserDTO userDTO);
}
