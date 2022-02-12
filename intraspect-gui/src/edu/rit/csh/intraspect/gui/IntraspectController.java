package edu.rit.csh.intraspect.gui;

import edu.rit.csh.intraspect.data.ClassFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class IntraspectController {

    private static final String GITHUB_LINK = "https://github.com/ethanf108/Intraspect";

    private static final FileChooser chooser = new FileChooser();
    private static final Alert exitConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);

    static {
        aboutAlert.setTitle("About Intraspect");
        final Hyperlink hyperlink = new Hyperlink("View on GitHub");
        hyperlink.setOnAction(event -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(GITHUB_LINK));
            } catch (final IOException | java.net.URISyntaxException ignored) {
            }
        });
        aboutAlert.getDialogPane().setContent(new VBox(new Label("Intraspect is a tool for analyzing Java class files."), hyperlink));
    }


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

    // ---------------------------------
    // ----- Application Tab Panes -----
    // ---------------------------------
    @FXML
    private MenuItem closeFileMenuOption;

    @FXML
    private ScrollPane overviewTab;

    @FXML
    private ScrollPane constantPoolTab;

    @FXML
    private ScrollPane fieldsTab;

    @FXML
    private ScrollPane methodsTab;

    @FXML
    private ScrollPane attributesTab;

    @FXML
    private TabPane tabPane;


    /**
     * Sole constructor which takes the application window as an argument
     *
     * @param window The application window
     */
    public IntraspectController(final Stage window) {
        this.window = window;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeApplication();
        });
    }

    // ---------------------------------
    // ----- Action event handlers -----
    // ---------------------------------
    @FXML
    private void showAboutAlert() {
        aboutAlert.showAndWait();
    }

    @FXML
    private void openFile() {

        // Set the initial directory to the user's home dir
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show the dialog
        final File file = chooser.showOpenDialog(this.window);
        {
            // If the user cancelled, don't do anything
            if (Objects.isNull(file))
                return;

            // Attempt to open the file and update the window upon success
            try {
                this.classFile = ClassFile.readClassFile(new FileInputStream(file));
                update();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void closeFile() {
        // Set the class file to null
        this.classFile = null;

        // Update the window
        update();
    }

    private void update() {
        if (Objects.isNull(this.classFile)) {

            // Disable the tabs
            tabPane.setDisable(true);

            // Disable the option to close the file
            closeFileMenuOption.setDisable(true);

            // Return
            return;
        }

        // Enable the tabs
        tabPane.setDisable(false);

        // Enable the option to close the file
        closeFileMenuOption.setDisable(false);

        // Build overview tab
        overviewTab.setContent(ViewBuilders.buildOverviewTab(this.classFile));

        // Build constant pool tab
        constantPoolTab.setContent(ViewBuilders.buildConstantPoolTab(this.classFile));

        // Build fields tab
        fieldsTab.setContent(ViewBuilders.buildFieldsTab(this.classFile));

        // Build methods tab
        methodsTab.setContent(ViewBuilders.buildMethodsTab(this.classFile));

        // Build attributes tab
        attributesTab.setContent(ViewBuilders.buildAttributesTab(this.classFile));
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
        // When the window is loaded, initialize it
        update();
    }
}
