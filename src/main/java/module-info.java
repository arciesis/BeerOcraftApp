module xyz.beerocraft {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens xyz.beerocraft to javafx.fxml;
    opens xyz.beerocraft.controller to javafx.fxml;
    exports xyz.beerocraft;
    exports xyz.beerocraft.controller;
}