package lk.ijse.gdse.ormcaursework.controller.popups;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.gdse.ormcaursework.bo.BOFactory;
import lk.ijse.gdse.ormcaursework.bo.BOType;
import lk.ijse.gdse.ormcaursework.bo.custom.TProgramBO;
import lk.ijse.gdse.ormcaursework.controller.AppointmentsController;
import lk.ijse.gdse.ormcaursework.dto.ProgramDto;
import lk.ijse.gdse.ormcaursework.dto.TM.ProgramTM;
import lk.ijse.gdse.ormcaursework.dto.TM.TProgramTM;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectProgramsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableIID.setCellValueFactory(new PropertyValueFactory<>("therapyID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("therapyName"));
        tableProgramDetails.setCellValueFactory(new PropertyValueFactory<>("therapyDescription"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("therapyFee"));

        try {
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Load Page", ButtonType.OK).show();
        }
    }

    @FXML
    private TableView<ProgramTM> Table;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button select;

    @FXML
    private TableColumn<TProgramTM, Double> tableFee;

    @FXML
    private TableColumn<TProgramTM, String> tableIID;

    @FXML
    private TableColumn<TProgramTM, String> tableName;

    @FXML
    private TableColumn<TProgramTM, String> tableProgramDetails;

    TProgramBO tProgramBO = BOFactory.getInstance().getBO(BOType.THERAPY_PROGRAMS);

    @Setter
    private AppointmentsController appointmentsController;

    @FXML
    void selectBtnAction(ActionEvent event) {
        String ID = idLabel.getText();
        String Name = nameLabel.getText();
        if (appointmentsController != null) {
            appointmentsController.setDetails(ID, Name);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void tableAction(MouseEvent event) {
        ProgramTM selectedPatient = Table.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            idLabel.setText(selectedPatient.getTherapyID());
            nameLabel.setText(selectedPatient.getTherapyName());
        }

    }
    private void loadTable() throws Exception {
        List<ProgramDto> therapyProgramDTOS =  tProgramBO.getALLTPrograms();
        ObservableList<ProgramTM> tProgramTMS = FXCollections.observableArrayList();
        for (ProgramDto programDto : therapyProgramDTOS) {
            ProgramTM programTM = new ProgramTM(
                    programDto.getTherapyID(),
                    programDto.getTherapyName(),
                    programDto.getTherapyDescription(),
                    programDto.getTherapyFee()
            );
            tProgramTMS.add(programTM);
        }
        Table.setItems(tProgramTMS);
    }
}
