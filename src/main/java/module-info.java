module com.example.jeuxst {
    requires javafx.controls;
    requires javafx.fxml;
   exports POO;

    opens com.example.jeuxst to javafx.fxml;
    exports com.example.jeuxst;
}
