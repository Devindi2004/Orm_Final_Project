package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.CourseBO;
import org.example.ormfinalproject.model.CourseDTO;
import org.example.ormfinalproject.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Long.parseLong;

public class CoursePageController {

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFees;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<CourseDTO> tblCourses;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadtable();
    }

    private void loadtable() {
        ArrayList<CourseDTO> courseDTOS = courseBO.getAllCourse();

        ObservableList<CourseDTO> data = FXCollections.observableArrayList();

        for (CourseDTO courseDTO : courseDTOS) {
            data.add(courseDTO);
        }

        tblCourses.setItems(data);
    }

    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }

    @FXML
    void handleAddCourse(ActionEvent event) {
        long courseId = Long.parseLong(txtCourseId.getText());
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String fee = txtFees.getText();

        CourseDTO courseDTO = new CourseDTO(
               courseId,
                name,
                duration,
                fee
        );

        boolean isSave = courseBO.save(courseDTO);

        if (isSave) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtFees.clear();
    }

    @FXML
    void handleDeleteCourse(ActionEvent event) {
        Long id = Long.valueOf(txtCourseId.getText());

        if (id == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a class to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the class with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = courseBO.delete(id);
            if (isDelete) {
                loadtable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
        }
    }

    @FXML
    void handleUpdateCourse(ActionEvent event) {
        long courseId = Long.parseLong(txtCourseId.getText());
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        String fee = txtFees.getText();

        CourseDTO courseDTO = new CourseDTO(
                courseId,
                name,
                duration,
                fee
        );

        boolean isUpdate = courseBO.update(courseDTO);

        if (isUpdate) {
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        CourseDTO selectedItem = tblCourses.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtCourseId.setText(String.valueOf(selectedItem.getCourseId()));
            txtCourseName.setText(selectedItem.getName());
            txtDuration.setText(selectedItem.getDuration());
            txtFees.setText(selectedItem.getFee());
        }
    }
}
