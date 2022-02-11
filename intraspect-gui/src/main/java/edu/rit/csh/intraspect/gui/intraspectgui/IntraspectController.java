package edu.rit.csh.intraspect.gui.intraspectgui;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.ClassFiles;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class IntraspectController {

    @FXML
    private TextField classFileField;

    @FXML
    private TextField majorVersionField;

    @FXML
    private TextField minorVersionField;

    @FXML
    private TabPane tabPane;

    @FXML
    private void classFileFieldChanged() {

    }

    @FXML
    private void majorVersionFieldChanged() {

    }

    @FXML
    private void minorVersionFieldChanged() {

    }

    private static final FileChooser chooser = new FileChooser();
    static {
        final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Class files (*.class)", "*.class");
        chooser.getExtensionFilters().add(extFilter);
    }

    private final Stage window;

    public IntraspectController(final Stage window) {
        this.window = window;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeApplication();
        });
    }

    private ClassFile classFile;

    @FXML
    private void openFile() {
        final File file = chooser.showOpenDialog(this.window);
        {
            if (Objects.isNull(file))
                return;

            try {
                this.classFile = ClassFile.readClassFile(new FileInputStream(file));
                update();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (Objects.isNull(this.classFile)) {
            tabPane.setDisable(true);
            return;
        }

        tabPane.setDisable(false);

        classFileField.setText(ClassFiles.classSimpleString(this.classFile));
        majorVersionField.setText(this.classFile.getMajorVersion().getMajorVersion() + "");
    }

    private static final Alert exitConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    static {
        exitConfirmationAlert.setTitle("Exit Confirmation");
        exitConfirmationAlert.setHeaderText("Are you sure you want to exit?");
        exitConfirmationAlert.setContentText(null);
    }

    @FXML
    private void closeApplication() {
        exitConfirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    @FXML
    private void initialize() {
        update();
    }
}
