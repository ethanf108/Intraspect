package edu.rit.csh.intraspect.gui;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ViewBuilders {

    private ViewBuilders() {}

    /**
     * Builds the overview tab for the class file.
     *
     * @param classFile The class file to build the overview tab for.
     * @return The overview tab for the class file.
     */
    public static Pane buildOverviewTab(final ClassFile classFile) {

        final GridPane content = new GridPane();

        final String className = classFile.getConstantDesc(classFile.getConstantDesc(classFile.getThisClassIndex(), ClassConstant.class).getUTF8Index(), UTF8Constant.class).getValue();
        final String minorVersion = classFile.getMinorVersion() + "";
        final String majorVersion = classFile.getMajorVersion() + "";

        content.add(new Label("Class name: "), 0, 0);
        content.add(new TextField(className), 1, 0);

        content.add(new Label("Minor version: "), 0, 1);
        content.add(new TextField(minorVersion), 1, 1);

        content.add(new Label("Major version: "), 0, 2);
        content.add(new TextField(majorVersion), 1, 2);

        return content;
    }

    /**
     * Builds the constant pool tab for the class file.
     *
     * @param classFile The class file to build the constant pool tab for.
     * @return The constant pool tab for the class file.
     */
    public static Pane buildConstantPoolTab(final ClassFile classFile) {

        final VBox content = new VBox();

        for (ConstantDesc constant : classFile.getConstants()) {

        }

        return content;

    }

    /**
     * Builds the fields tab for the class file.
     *
     * @param classFile The class file to build the fields tab for.
     * @return The fields tab for the class file.
     */
    public static Pane buildFieldsTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    /**
     * Builds the methods tab for the class file.
     *
     * @param classFile The class file to build the methods tab for.
     * @return The methods tab for the class file.
     */
    public static Pane buildMethodsTab(final ClassFile classFile) {
        return new AnchorPane();
    }

    /**
     * Builds the attributes tab for the class file.
     *
     * @param classFile The class file to build the attributes tab for.
     * @return The attributes tab for the class file.
     */
    public static Pane buildAttributesTab(final ClassFile classFile) {
        return new AnchorPane();
    }
}
