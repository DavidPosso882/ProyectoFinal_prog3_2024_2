module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.jshell;
    requires java.logging;


    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;
}