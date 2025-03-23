module lk.ijse.gdse.ormcaursework.ormcaursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;

    opens lk.ijse.gdse.ormcaursework to javafx.fxml;
    exports lk.ijse.gdse.ormcaursework;
    exports lk.ijse.gdse.ormcaursework.controller to javafx.fxml;
    opens lk.ijse.gdse.ormcaursework.controller to javafx.fxml;
    opens lk.ijse.gdse.ormcaursework.config to jakarta.persistence;
    opens lk.ijse.gdse.ormcaursework.entity to org.hibernate.orm.core;
//    opens lk.ijse.gdse.ormcaursework.dto to org.hibernate.orm.core;

}