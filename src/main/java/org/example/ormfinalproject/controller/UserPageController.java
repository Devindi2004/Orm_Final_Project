package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.ormfinalproject.BO.custom.BOFactory;
import org.example.ormfinalproject.BO.custom.UserBO;
import org.example.ormfinalproject.model.StudentDTO;
import org.example.ormfinalproject.model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserPageController {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private ImageView backButton;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<UserDTO> tblUsers;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadtable();
    }

    private void loadtable() {
        ArrayList<UserDTO> userDTOS = userBO.getAllUser();

        ObservableList<UserDTO> data = FXCollections.observableArrayList();

        for (UserDTO userDTO : userDTOS) {
            data.add(userDTO);
        }

        tblUsers.setItems(data);
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

    @FXML
    void backButton(MouseEvent event) {

    }

    @FXML
    void handleClear(ActionEvent event) {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();
    }

    @FXML
    void handleDeleteUser(ActionEvent event) {

    }

    @FXML
    void handleSaveUser(ActionEvent event) {

    }

    @FXML
    void handleUpdateUser(ActionEvent event) {

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {

    }

}
