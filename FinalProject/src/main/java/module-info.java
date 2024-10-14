module com.example.finalproject {
    exports Clases;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;
}