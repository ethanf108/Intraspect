module edu.rit.csh.intraspect.gui.intraspectgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires intraspect.base;

    opens edu.rit.csh.intraspect.gui.intraspectgui to javafx.fxml;
    exports edu.rit.csh.intraspect.gui.intraspectgui;
}