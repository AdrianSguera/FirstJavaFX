module com.ceica.firstjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ceica.firstjavafx to javafx.fxml;
    exports com.ceica.firstjavafx;
}