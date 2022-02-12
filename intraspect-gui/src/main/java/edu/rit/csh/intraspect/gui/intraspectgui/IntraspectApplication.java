package edu.rit.csh.intraspect.gui.intraspectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IntraspectApplication extends Application {

    /**
     * Main method of the program. Launches the GUI application.
     *
     * @param args The arguments passed to the application
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * JavaFX start method. Constructs and displays the application window.
     *
     * @param window The application window (A.K.A. the stage)
     * @throws IOException If the fxml file couldn't be loaded
     */
    @Override
    public void start(final Stage window) throws IOException {

        // Load the fxml file
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intraspect-view.fxml"));

        // Specify the controller
        fxmlLoader.setController(new IntraspectController(window));

        // Prevent the window from being resized
        //window.setResizable(false);

        // Set the title
        window.setTitle("Intraspect GUI");

        // Make and add the scene
        window.setScene(new Scene(fxmlLoader.load(), 600, 425));

        // Show the window
        window.show();
    }
}