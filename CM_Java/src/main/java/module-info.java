module fr.esgi.cookmaster_java_2a3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens fr.esgi.cookmaster_java_2a3.model to javafx.fxml;
    opens fr.esgi.cookmaster_java_2a3.data to javafx.fxml;
    opens fr.esgi.cookmaster_java_2a3.main to javafx.fxml;
    opens fr.esgi.cookmaster_java_2a3.tools to javafx.fxml;

    exports fr.esgi.cookmaster_java_2a3.model;
    exports fr.esgi.cookmaster_java_2a3.data;
    exports fr.esgi.cookmaster_java_2a3.main;
    exports fr.esgi.cookmaster_java_2a3.tools;
}
