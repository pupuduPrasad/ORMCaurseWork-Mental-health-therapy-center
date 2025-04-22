package lk.ijse.gdse.ormcaursework.controller.popups;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.gdse.ormcaursework.bo.BOFactory;
import lk.ijse.gdse.ormcaursework.bo.BOType;
import lk.ijse.gdse.ormcaursework.bo.custom.TherapistBO;
import lk.ijse.gdse.ormcaursework.controller.AppointmentsController;
import lk.ijse.gdse.ormcaursework.dto.DoctorDTO;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignDoctorsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load", ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label docAvailable;

    @FXML
    private Circle circleD;

    @FXML
    private ComboBox<String> docComboBox;

    @FXML
    private Label docIdFromCombo;

    @FXML
    private Label docNameFromCombo;

    @FXML
    private Label docQualificationsFromCombo;

    @FXML
    private ImageView image;

    @FXML
    private Button select;

    @Setter
    private AppointmentsController appointmentsController;

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    private void refreshPage() throws Exception {
        docIdFromCombo.setText("");
        docNameFromCombo.setText("");
        docQualificationsFromCombo.setText("");
        docAvailable.setText("");
        circleD.setVisible(false);
        load();
    }

    private void load (){
        try {
            List<DoctorDTO> doctors = therapistBO.getDocNames();
            if (doctors == null || doctors.isEmpty()) {
                return;
            }
            ObservableList<String> doctorNames = FXCollections.observableArrayList();
            for (DoctorDTO d : doctors) {
                doctorNames.add(d.getDoctorName());
            }
            if (docComboBox.getItems() == null || !docComboBox.getItems().equals(doctorNames)) {
                Platform.runLater(() -> docComboBox.setItems(doctorNames));
            }
            docComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    DoctorDTO selectedDoctor = findDoctorByName(doctors, newValue);

                    docIdFromCombo.setText(selectedDoctor.getDoctorID());
                    docNameFromCombo.setText(selectedDoctor.getDoctorName());
                    docQualificationsFromCombo.setText(selectedDoctor.getDoctorQualifications());
                    docAvailable.setText(selectedDoctor.getDoctorAvailability());
                    docAvailable.setText(selectedDoctor.getDoctorAvailability());

                    String availability = selectedDoctor.getDoctorAvailability().trim();

                    if (availability.equalsIgnoreCase("Available")) {
                        circleD.setFill(Paint.valueOf("#1fff29"));
                        select.setDisable(false);
                    } else if (availability.equalsIgnoreCase("Not Available")) {
                        circleD.setFill(Paint.valueOf("red"));
                        select.setDisable(true);
                    }

                    circleD.setVisible(true);

                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DoctorDTO findDoctorByName(List<DoctorDTO> doctors, String name) {
        for (DoctorDTO doctor : doctors) {
            if (doctor.getDoctorName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    @FXML
    void selectBtnAction(ActionEvent event) {
        if (appointmentsController != null) {
            String ID = docIdFromCombo.getText();
            String Name = docNameFromCombo.getText();
            String Availability = docAvailable.getText();
            appointmentsController.setAddDoctors(ID, Name,Availability);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
