module lk.ijse.gdse.ormcaursework.ormcaursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;


    opens lk.ijse.gdse.ormcaursework to javafx.fxml;
    exports lk.ijse.gdse.ormcaursework;
}