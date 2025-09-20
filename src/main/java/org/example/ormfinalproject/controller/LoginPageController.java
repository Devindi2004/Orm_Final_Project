package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> languageComboBox;

    // Initialize the controller, populating the language ComboBox
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languageComboBox.setItems(FXCollections.observableArrayList(
                "Afrikaans", "azərbaycan", "bosanski", "català", "Čeština", "Cymraeg", "Dansk", "Deutsch",
                "eesti", "English (United Kingdom)", "English (United States)", "Español (España)",
                "Español (Latinoamérica)", "euskara", "Filipino", "Français (Canada)", "Français (France)",
                "Gaeilge", "galego", "Hrvatski", "Indonesia", "isiZulu", "íslenska", "Italiano",
                "Kiswahili", "latviešu", "lietuvių", "magyar", "Melayu", "Nederlands", "norsk",
                "o‘zbek", "polski", "Português (Brasil)", "Português (Portugal)", "română", "shqip",
                "Slovenčina", "slovenščina", "srpski (latinica)", "Suomi", "Svenska", "Tiếng Việt",
                "Türkçe", "Ελληνικά", "беларуская", "български", "кыргызча", "қазақ тілі", "македонски",
                "монгол", "Русский", "српски (ћирилица)", "Українська", "ქართული", "հայերեն",
                "עברית", "اردو", "العربية", "فارسی", "አማርኛ", "नेपाली", "मराठी", "हिन्दी",
                "অসমীয়া", "বাংলা", "ਪੰਜਾਬੀ", "ગુજરાતી", "ଓଡ଼ିଆ", "தமிழ்", "తెలుగు", "ಕನ್ನಡ",
                "മലയാളം", "සිංහල", "ไทย", "ລາວ", "မြန်မာ", "ខ្មែរ", "한국어", "中文（香港）",
                "日本語", "简体中文", "繁體中文"
        ));
        languageComboBox.setValue("English (United States)");
    }

    // Handle Login button action
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }

        if (username.equals("devindi") && email.equals("d@gmail.com") && password.equals("1234")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoard.fxml"));
                Scene dashboardScene = new Scene(loader.load(), 900, 600);
                DashBoardController controller = loader.getController();
                controller.handleLogin(username); // Pass username to dashboard
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                stage.setScene(dashboardScene);
                stage.setTitle("DriveSmart Academy Dashboard");
                stage.show();
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load dashboard: " + e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, email, or password.");
        }
    }

    // Handle Register button action
    @FXML
    private void handleRegister(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Register", "Registration functionality not implemented.");
    }

    // Handle Forgot Password action
    @FXML
    private void handleForgotPassword() {
        String email = txtEmail.getText().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        if (!email.equals("punchihewadevindi@gmail.com")) {
            showAlert(Alert.AlertType.ERROR, "Email Not Found", "The provided email does not match our records.");
            return;
        }

        String otp = generateOTP();
        boolean emailSent = sendOTPEmail(email, otp);

        if (emailSent) {
            showAlert(Alert.AlertType.INFORMATION, "OTP Sent", "An OTP has been sent to " + email + ". Please check your inbox.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Email Error", "Failed to send OTP. Please try again later.");
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean sendOTPEmail(String recipientEmail, String otp) {
        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "your-email@gmail.com"; // Replace with your email
        String senderPassword = "your-app-password"; // Replace with your app-specific password

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("DriveSmart Academy - OTP for Password Reset");
            message.setText("Dear User,\n\nYour OTP for password reset is: " + otp + "\n\nThis OTP is valid for 10 minutes.\n\nBest Regards,\nDriveSmart Academy");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}