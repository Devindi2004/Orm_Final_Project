package org.example.ormfinalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.example.ormfinalproject.BO.custom.*;
import org.example.ormfinalproject.BO.custom.impl.InstructorBOImpl;
import org.example.ormfinalproject.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

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

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);
    LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LESSON);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
//    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

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

    public void initialize() throws SQLException, ClassNotFoundException {
        setStudent();
        setInstructor();
//        setLesson();
//        setPayment();
        //setUser();
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

    public void setStudent () throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        lblTotalStudents.setText(String.valueOf(allStudent.size()));
    }
    public void setInstructor () throws SQLException, ClassNotFoundException {
        ArrayList<InstructorDTO> allInstructor = instructorBO.getAllInstructor();
        lblTotalInstructors.setText(String.valueOf(allInstructor.size()));
    }
//    public void setLesson () throws SQLException, ClassNotFoundException {
//        ArrayList<LessonDTO> allLesson = lessonBO.getAllLesson();
//        lblTotalLessons.setText(String.valueOf(allLesson.size()));
//    }
//    public void setPayment () throws SQLException, ClassNotFoundException {
//        ArrayList<PaymentDTO> allPayment = paymentBO.getAllPayment();
//        lblTotalPayments.setText(String.valueOf(allPayment.size()));
//    }
//    public void setCourse () throws SQLException, ClassNotFoundException {
//        ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
//        lblTotalCourses.setText(String.valueOf(allCourse.size()));
//    }
//    public void setUser () throws SQLException, ClassNotFoundException {
//        ArrayList<UserDTO> allUser = userBO.getAllUser();
//        lblTotalUsers.setText(String.valueOf(allUser.size()));
//    }
}
