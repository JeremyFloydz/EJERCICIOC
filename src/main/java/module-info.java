module org.example.ejec {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejec to javafx.fxml;
    exports org.example.ejec;
}