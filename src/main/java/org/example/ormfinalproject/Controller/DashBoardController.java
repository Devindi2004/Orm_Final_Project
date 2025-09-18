package org.example.ormfinalproject.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DashBoardController {

    @FXML
    private AnchorPane Ank1;

    @FXML
    private Button btnManageCourses;

    @FXML
    private Button btnManageInstructors;

    @FXML
    private Button btnManageLessons;

    @FXML
    private Button btnManagePayments;

    @FXML
    private Button btnManageStudents;

    @FXML
    private Button btnManageUsers;

    @FXML
    private Button btnSearch;

    @FXML
    private VBox courseId;

    @FXML
    private Label dSchool;

    @FXML
    private VBox instructorId;

    @FXML
    private Label lblTotalCourses;

    @FXML
    private Label lblTotalInstructors;

    @FXML
    private Label lblTotalLessons;

    @FXML
    private Label lblTotalPayments;

    @FXML
    private Label lblTotalStudents;

    @FXML
    private Label lblTotalUsers;

    @FXML
    private VBox lessonId;

    @FXML
    private VBox paymentId;

    @FXML
    private VBox studentId;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox userId;

    @FXML
    private VBox v1;

    @FXML
    void handleManageCourses(ActionEvent event) {
        nevigateTo("/view/course.fxml");
    }

    @FXML
    void handleManageInstructors(ActionEvent event) {
        nevigateTo("/view/instructor_Manage.fxml");
    }

    @FXML
    void handleManageLessons(ActionEvent event) {
        nevigateTo("/view/lesson_Manage.fxml");
    }

    @FXML
    void handleManagePayments(ActionEvent event) {
        nevigateTo("/view/payment_Manage.fxml");
    }

    @FXML
    void handleManageStudents(ActionEvent event) {
        nevigateTo("/view/student_Manage.fxml");
    }

    @FXML
    void handleManageUsers(ActionEvent event) {
        nevigateTo("/view/user.fxml");
    }

    @FXML
    void handleSearch(ActionEvent event) {

    }
    private void nevigateTo(String s) {
        try {
            Ank1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(Ank1.widthProperty());
            pane.prefHeightProperty().bind(Ank1.heightProperty());

            Ank1.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }
}
