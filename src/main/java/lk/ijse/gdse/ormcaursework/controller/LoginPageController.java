
package lk.ijse.gdse.ormcaursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.gdse.ormcaursework.config.FactoryConfiguration;
import lk.ijse.gdse.ormcaursework.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginPageController {

    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField userNameLabel;

    @FXML
    void loginOnClick(ActionEvent event) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUserName("prasad");
        user.setPassword("1234");
        tx.commit();
        session.close();
    }

}
