module org.example.conecta4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.conecta4 to javafx.fxml;
    exports org.example.conecta4;
}