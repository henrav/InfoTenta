module com.example.tentafragor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tentafragor to javafx.fxml;
    exports com.example.tentafragor;
}