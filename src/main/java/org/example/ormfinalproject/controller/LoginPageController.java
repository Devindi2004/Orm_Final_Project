package org.example.ormfinalproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
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
        // Populate language ComboBox with languages from the provided document
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
        languageComboBox.setValue("English (United States)"); // Default selection
    }

    // Handle Login button action
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        // Validate inputs
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }

        // Check against provided credentials
        if (username.equals("devindi") && email.equals("punchihewadevindi@gmail.com") && password.equals("1234")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            // Proceed to next scene or dashboard (not implemented)
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, email, or password.");
        }
    }

    // Handle Register button action
    @FXML
    private void handleRegister(ActionEvent event) {
        // Placeholder for registration logic
        showAlert(Alert.AlertType.INFORMATION, "Register", "Registration functionality not implemented.");
    }

    // Handle Forgot Password action
    @FXML
    private void handleForgotPassword() {
        String email = txtEmail.getText().trim();

        // Validate email
        if (email.isEmpty() || !isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        // Check if email matches the provided one
        if (!email.equals("punchihewadevindi@gmail.com")) {
            showAlert(Alert.AlertType.ERROR, "Email Not Found", "The provided email does not match our records.");
            return;
        }

        // Generate OTP
        String otp = generateOTP();

        // Send OTP to email
        boolean emailSent = sendOTPEmail(email, otp);

        if (emailSent) {
            showAlert(Alert.AlertType.INFORMATION, "OTP Sent", "An OTP has been sent to " + email + ". Please check your inbox.");
            // Optionally, open a dialog for OTP verification (not implemented)
        } else {
            showAlert(Alert.AlertType.ERROR, "Email Error", "Failed to send OTP. Please try again later.");
        }
    }

    // Generate a 6-digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
        return String.valueOf(otp);
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Send OTP via email using JavaMail API
    private boolean sendOTPEmail(String recipientEmail, String otp) {
        // Email server configuration (using Gmail SMTP for example)
        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "your-email@gmail.com"; // Replace with your email
        String senderPassword = "your-app-password"; // Replace with your app-specific password

        // Set properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("DriveSmart Academy - OTP for Password Reset");
            message.setText("Dear User,\n\nYour OTP for password reset is: " + otp + "\n\nThis OTP is valid for 10 minutes.\n\nBest Regards,\nDriveSmart Academy");

            // Send the email
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}