module lk.ijse.gdse.ormcaursework.ormcaursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires spring.security.crypto;
    requires net.sf.jasperreports.core;
    requires java.sql;
    requires static lombok;
    requires java.mail;
    requires mysql.connector.j;
    requires spring.security.core;
    requires java.desktop;

    opens lk.ijse.gdse.ormcaursework to javafx.fxml;
    exports lk.ijse.gdse.ormcaursework;
    exports lk.ijse.gdse.ormcaursework.controller to javafx.fxml;
    opens lk.ijse.gdse.ormcaursework.controller to javafx.fxml;
    opens lk.ijse.gdse.ormcaursework.config to jakarta.persistence;
    opens lk.ijse.gdse.ormcaursework.entity to org.hibernate.orm.core;
    exports lk.ijse.gdse.ormcaursework.entity;
    exports lk.ijse.gdse.ormcaursework.controller.popups;
    opens lk.ijse.gdse.ormcaursework.controller.popups to javafx.fxml;

    exports lk.ijse.gdse.ormcaursework.controller.Login;
    opens lk.ijse.gdse.ormcaursework.controller.Login to javafx.fxml;

    exports lk.ijse.gdse.ormcaursework.dto to org.hibernate.orm.core;
    exports lk.ijse.gdse.ormcaursework.controller.Login.UtilClasses;
    opens lk.ijse.gdse.ormcaursework.controller.Login.UtilClasses to javafx.fxml;

    opens lk.ijse.gdse.ormcaursework.dto.TM to javafx.base;
}