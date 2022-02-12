module intraspect.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires intraspect.base;

    opens edu.rit.csh.intraspect.gui to javafx.fxml;
    exports edu.rit.csh.intraspect.gui;
}