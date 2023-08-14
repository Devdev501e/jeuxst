module com.example.jeuxst {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jeuxst to javafx.fxml;
    exports com.example.jeuxst;
}