package edu.rit.csh.intraspect.gui.intraspectgui;

import edu.rit.csh.intraspect.data.ClassFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IntraspectApplication extends Application {

    @Override
    public void start(final Stage window) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "intraspect-view.fxml"));

        IntraspectController controller = new IntraspectController(window);
        fxmlLoader.setController(controller);

        final Scene scene = new Scene(fxmlLoader.load(), 500, 400);

        window.setResizable(false);

        window.setTitle("Intraspect GUI");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}