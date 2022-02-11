package edu.rit.csh.intraspect.gui.intraspectgui;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class IntraspectController {

    private static final FileChooser chooser = new FileChooser();
    private static final Alert exitConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

    static {
        chooser.getExtensionFilters().add(new ExtensionFilter("Class files (*.class)", "*.class"));
        chooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*"));
    }

    static {
        exitConfirmationAlert.setTitle("Exit Confirmation");
        exitConfirmationAlert.setHeaderText("Are you sure you want to exit?");
        exitConfirmationAlert.setContentText(null);
    }

    private final Stage window;

    private ClassFile classFile;

    @FXML
    private ScrollPane constantPoolTab;

    @FXML
    private TabPane tabPane;

    public IntraspectController(final Stage window) {
        this.window = window;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeApplication();
        });
    }

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

        // Constant pool tab
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        constantPoolTab.setContent(gridPane);

        int index = 0;

        for (ConstantDesc cd : classFile.getConstants()) {
            index++;
            if (cd instanceof EmptyWideConstant) {
                continue;
            }
            String className = cd.getClass().getSimpleName();
            className = className.substring(0, className.indexOf("Constant"));

            gridPane.add(new Label(className), 0, index);
            gridPane.add(new TextField(cd instanceof UTF8Constant u ? u.getValue() : ""), 1, index);
        }
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
