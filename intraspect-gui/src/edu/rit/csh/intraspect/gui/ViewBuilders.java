package edu.rit.csh.intraspect.gui;

import edu.rit.csh.intraspect.data.ClassFile;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ViewBuilders {

    private ViewBuilders() {}

    public static Pane buildOverviewTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    public static Pane buildConstantPoolTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    public static Pane buildFieldsTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    public static Pane buildMethodsTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    public static Pane buildAttributesTab(final ClassFile classFile) {
        return new AnchorPane();
    }
}
