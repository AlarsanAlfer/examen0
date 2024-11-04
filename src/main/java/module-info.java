module org.example.exjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.example.exjavafx to javafx.fxml;
    exports org.example.exjavafx;
}