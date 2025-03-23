module lk.ijse.gdse.ormcaursework.ormcaursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.gdse.ormcaursework to javafx.fxml;
    exports lk.ijse.gdse.ormcaursework;
}