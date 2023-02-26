module com.example.yandexscootertest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.yandexscootertest to javafx.fxml;
    exports com.example.yandexscootertest;
}