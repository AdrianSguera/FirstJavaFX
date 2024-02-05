module org.example.firstjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.firstjavafx to javafx.fxml;
    exports org.example.firstjavafx;
}