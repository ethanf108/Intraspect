package edu.rit.csh.intraspect.gui;

import edu.rit.csh.intraspect.data.ClassFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class IntraspectController {

    /**
     * The link to the GitHub repository for this project.
     */
    private static final String GITHUB_LINK = "https://github.com/ethanf108/Intraspect";
    private static final FileChooser chooser = new FileChooser();
    private static final Alert exitConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);

    static {
        aboutAlert.setTitle("About Intraspect");
        aboutAlert.setHeaderText("A tool for reading, analyzing, and editing Java class files.");

        final Hyperlink hyperlink = new Hyperlink("View on GitHub");
        {
            hyperlink.setOnAction(event -> {
                try {
                    hyperlink.setVisited(false);
                    Desktop.getDesktop().browse(new java.net.URI(GITHUB_LINK));
                } catch (final IOException | URISyntaxException ignored) {
                }
            });
        }

        aboutAlert.getDialogPane().setContent(hyperlink);
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

    /**
     * The stage that this controller is associated with.
     */
    private final Stage window;

    @FXML
    TabPane tabPane;
    /**
     * The class file that is currently being analyzed.
     */
    private OpenedFile openedClassFile;

    @FXML
    private MenuItem saveFileMenuOption;
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
    private ScrollPane inheritanceTab;

    /**
     * Sole constructor which takes the application window as an argument
     *
     * @param window The application window
     */
    public IntraspectController(final Stage window) {
        this.window = window;
        window.setOnCloseRequest(e -> {
            e.consume();    // Consume the event so that the window doesn't close
            this.closeApplication();
        });
    }

    @FXML
    private void showAboutAlert() {
        // Show the about alert and stall the thread
        // We don't really care about the result
        aboutAlert.showAndWait();
    }

    @FXML
    private void openFile() {

        // Set the initial directory to the user's home dir
        if (Objects.isNull(this.openedClassFile)) {
            chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        } else {
            chooser.setInitialDirectory(this.openedClassFile.file.getParentFile());
        }

        // Show the dialog
        final File file = chooser.showOpenDialog(this.window);
        {
            // If the user cancelled, don't do anything
            if (Objects.isNull(file)) {
                return;
            }

            // Attempt to open the file and update the window upon success
            try {
                this.openedClassFile = OpenedFile.open(file);
                this.update();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveFile() {
        try {
            this.openedClassFile.save();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeFile() {
        // Set the class file to null
        this.openedClassFile = null;

        // Update the window
        this.update();
    }

    @FXML
    private void closeApplication() {
        exitConfirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    /**
     * Updates the window to reflect any changes to the class file
     */
    private void update() {
        // Check if the class file is null
        final boolean isFileOpen = Objects.nonNull(this.openedClassFile);

        final ClassFile classFile = isFileOpen ? this.openedClassFile.classFile : null;

        // Update tabs
        this.overviewTab.setContent(isFileOpen ? ViewBuilders.buildOverviewTab(classFile, this) : null);
        this.constantPoolTab.setContent(isFileOpen ? ViewBuilders.buildConstantPoolTab(classFile) : null);
        this.fieldsTab.setContent(isFileOpen ? ViewBuilders.buildFieldsTab(classFile) : null);
        this.methodsTab.setContent(isFileOpen ? ViewBuilders.buildMethodsTab(classFile) : null);
        this.attributesTab.setContent(isFileOpen ? ViewBuilders.buildAttributesTab(classFile) : null);
        this.inheritanceTab.setContent(isFileOpen ? ViewBuilders.buildInheritanceTab(classFile) : null);

        // Enable/disable menu options
        this.tabPane.setDisable(!isFileOpen);
        this.closeFileMenuOption.setDisable(!isFileOpen);
        this.saveFileMenuOption.setDisable(!isFileOpen);
    }

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // When the window is loaded, initialize it
        this.update();
    }

    private static record OpenedFile(ClassFile classFile, File file) {
        public static OpenedFile open(final File file) throws IOException {
            final FileInputStream inputStream = new FileInputStream(file);
            final ClassFile classFile = ClassFile.readClassFile(inputStream);

            return new OpenedFile(classFile, file);
        }

        public void save() throws IOException {
            this.classFile.write(new FileOutputStream(this.file));
        }
    }
}
