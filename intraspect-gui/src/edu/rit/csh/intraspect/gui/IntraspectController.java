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
    private ClassFileWrapper openedClassFile = new ClassFileWrapper();

    @FXML
    private MenuItem saveFileMenuOption;
    @FXML
    private MenuItem closeFileMenuOption;
    @FXML
    private MenuItem deleteItemMenuOption;

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

    private ScrollPane[] tabs;

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
    private void deleteItem() {
       // list.getItems().remove(list.getSelectionModel().getSelectedIndex()).remove();
    }

    @FXML
    private void showAboutAlert() {
        // Show the about alert and stall the thread
        // We don't really care about the result
        aboutAlert.showAndWait();
    }

    @FXML
    private void openFile() {

        // Show the dialog
        final File file = chooser.showOpenDialog(this.window);
        {
            // If the user cancelled, don't do anything
            if (Objects.isNull(file)) {
                return;
            }

            // Attempt to open the file and update the window upon success
            this.openedClassFile.load(file);

            chooser.setInitialDirectory(this.openedClassFile.file.getParentFile());

            this.update();

        }
    }

    @FXML
    private void saveFile() {
        this.openedClassFile.save();
    }

    @FXML
    private void closeFile() {
        // Set the class file to null
        this.openedClassFile.close();

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

    //private ListView<Entry> list;

    /**
     * Updates the window to reflect any changes to the class file
     */
    void update() {
        // Check if the class file is null
        final boolean fileOpen = this.openedClassFile.isOpen();

        if (fileOpen) {
            this.window.setTitle("Intraspect - " + this.openedClassFile.file.getName());

            this.buildTabs();

            this.tabPane.setDisable(false);
            this.closeFileMenuOption.setDisable(false);
            this.saveFileMenuOption.setDisable(false);
        } else {
            this.window.setTitle("Intraspect");

            this.clearTabs();

            this.tabPane.setDisable(true);
            this.closeFileMenuOption.setDisable(true);
            this.saveFileMenuOption.setDisable(true);
        }
    }


    private void clearTabs() {
        for (final ScrollPane tab : this.tabs) {
            tab.setContent(null);
        }
    }

    private void buildTabs() {
        this.overviewTab.setContent(ViewBuilders.buildOverviewTab(this.openedClassFile.classFile, this));
        this.constantPoolTab.setContent(ViewBuilders.buildConstantPoolTab(this.openedClassFile.classFile, this));
        this.fieldsTab.setContent(ViewBuilders.buildFieldsTab(this.openedClassFile.classFile, this));
        this.methodsTab.setContent(ViewBuilders.buildMethodsTab(this.openedClassFile.classFile, this));
        this.attributesTab.setContent(ViewBuilders.buildAttributesTab(this.openedClassFile.classFile, this));
        this.inheritanceTab.setContent(ViewBuilders.buildInheritanceTab(this.openedClassFile.classFile, this));
    }

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        this.tabs = new ScrollPane[]{this.overviewTab, this.constantPoolTab, this.fieldsTab, this.methodsTab, this.attributesTab, this.inheritanceTab};

        // When the window is loaded, initialize it
        this.update();
    }

    private static class ClassFileWrapper {

        private boolean open;

        private ClassFile classFile;
        private File file;

        public ClassFileWrapper() {
        }

        public boolean isOpen() {
            return this.open;
        }

        public void close() {
            this.classFile = null;
            this.open = false;
        }

        public boolean load(final File file) {
            this.file = file;

            try {
                this.classFile = ClassFile.readClassFile(new FileInputStream(file));
            } catch (final IOException e) {
                return false;
            }

            this.open = true;

            return true;
        }

        public boolean save(final File file) {
            try {
                this.classFile.write(new FileOutputStream(file));
            } catch (final IOException e) {
                return false;
            }
            return false;
        }

        public boolean save() {
            return this.save(this.file);
        }
    }

/*
    private static record OpenedFile(ClassFile classFile, File file) {
        public static OpenedFile open(final File file) throws IOException {
            final FileInputStream inputStream = new FileInputStream(file);
            final ClassFile classFile = ClassFile.readClassFile(inputStream);

            return new OpenedFile(classFile, file);
        }

        public void save() throws IOException {
            this.classFile.write(new FileOutputStream(this.file));
        }
    }*/
}
