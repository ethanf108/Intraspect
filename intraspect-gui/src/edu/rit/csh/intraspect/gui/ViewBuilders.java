package edu.rit.csh.intraspect.gui;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.FieldDesc;
import edu.rit.csh.intraspect.data.MethodDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.gui.entries.AttributeEntry;
import edu.rit.csh.intraspect.gui.entries.ConstantEntry;
import edu.rit.csh.intraspect.gui.entries.FieldEntry;
import edu.rit.csh.intraspect.gui.entries.MethodEntry;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Contains static methods for building JavaFX nodes for the class file view.
 */
public class ViewBuilders {

    /**
     * Private constructor to prevent instantiation.
     */
    private ViewBuilders() {
    }

    /**
     * Builds the overview tab for the class file.
     *
     * @param classFile The class file to build the overview tab for.
     * @return The overview tab for the class file.
     */
    public static Pane buildOverviewTab(final ClassFile classFile, final IntraspectController intraspectController) {

        final GridPane content = new GridPane();

        content.setHgap(5);
        content.setVgap(5);

        final String className = classFile.getConstantDesc(classFile.getConstantDesc(classFile.getThisClassIndex(), ClassConstant.class).getUTF8Index(), UTF8Constant.class).getValue();
        final String superClass = classFile.getConstantDesc(classFile.getConstantDesc(classFile.getSuperClassIndex(), ClassConstant.class).getUTF8Index(), UTF8Constant.class).getValue();

        final String constantPoolSize = classFile.getConstants().length + "";
        final String interfaceCount = classFile.getInterfaces().length + "";
        final String fieldCount = classFile.getFields().length + "";
        final String methodCount = classFile.getMethods().length + "";
        final String attributeCount = classFile.getAttributes().length + "";

        content.add(new Label("Class name:"), 0, 0);
        content.add(new Label(className), 1, 0);

        // Super class info
        {
            final Hyperlink superClassLink = new Hyperlink(superClass);
            superClassLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(5);
                superClassLink.setVisited(false);
            });
            content.add(new Label("Super class:"), 0, 1);
            content.add(superClassLink, 1, 1);
        }

        // Minor version info
        {
            final Spinner<Integer> minorVersionSpinner = new Spinner<>(0, Integer.MAX_VALUE, classFile.getMinorVersion());
            minorVersionSpinner.valueProperty().addListener((observableValue, oldVal, newVal) -> {
                System.out.println("Updating minor version to: " + newVal);
            });
            content.add(new Label("Minor version:"), 0, 2);
            content.add(minorVersionSpinner, 1, 2);
        }

        // Major version info
        {
            final Spinner<Integer> majorVersionSpinner = new Spinner<>(0, Integer.MAX_VALUE, classFile.getMajorVersion().getMajorVersion());
            majorVersionSpinner.valueProperty().addListener((observableValue, oldVal, newVal) -> {
                System.out.println("Updating major version to: " + newVal);
            });
            content.add(new Label("Major version:"), 0, 3);
            content.add(majorVersionSpinner, 1, 3);
            content.add(new Label(classFile.getMajorVersion().getJavaVersionString().orElse("-")), 2, 3);
        }

        // Constant pool info
        {
            final Hyperlink constantPoolLink = new Hyperlink(constantPoolSize);
            constantPoolLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(1);
                constantPoolLink.setVisited(false);
            });
            content.add(new Label("Constant count:"), 0, 4);
            content.add(constantPoolLink, 1, 4);
        }

        // Interfaces info
        {
            final Hyperlink interfacesLink = new Hyperlink(interfaceCount);
            interfacesLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(5);
                interfacesLink.setVisited(false);
            });
            content.add(new Label("Interface count: "), 0, 5);
            content.add(interfacesLink, 1, 5);
        }

        // Field info
        {
            final Hyperlink fieldLink = new Hyperlink(fieldCount);
            fieldLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(2);
                fieldLink.setVisited(false);
            });
            content.add(new Label("Field count:"), 0, 6);
            content.add(fieldLink, 1, 6);
        }

        // Method info
        {
            final Hyperlink methodLink = new Hyperlink(methodCount);
            methodLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(3);
                methodLink.setVisited(false);
            });
            content.add(new Label("Method count:"), 0, 7);
            content.add(methodLink, 1, 7);
        }

        // Attribute info
        {
            final Hyperlink attributeLink = new Hyperlink(attributeCount);
            attributeLink.setOnAction(e -> {
                intraspectController.tabPane.getSelectionModel().select(4);
                attributeLink.setVisited(false);
            });
            content.add(new Label("Attribute count:"), 0, 8);
            content.add(attributeLink, 1, 8);
        }

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

        for (final ConstantDesc constant : classFile.getConstants()) {
            final Pane pane = new ConstantEntry(constant, classFile).buildPane();
            content.getChildren().add(pane);
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
        final VBox content = new VBox();

        for (final FieldDesc field : classFile.getFields()) {
            final Pane pane = new FieldEntry(field, classFile).buildPane();
            content.getChildren().add(pane);
        }

        return content;
    }

    /**
     * Builds the methods tab for the class file.
     *
     * @param classFile The class file to build the methods tab for.
     * @return The methods tab for the class file.
     */
    public static Pane buildMethodsTab(final ClassFile classFile) {
        final VBox content = new VBox();

        for (final MethodDesc method : classFile.getMethods()) {
            final Pane pane = new MethodEntry(method, classFile).buildPane();
            content.getChildren().add(pane);
        }

        return content;
    }

    /**
     * Builds the attributes tab for the class file.
     *
     * @param classFile The class file to build the attributes tab for.
     * @return The attributes tab for the class file.
     */
    public static Pane buildAttributesTab(final ClassFile classFile) {
        final VBox content = new VBox();

        for (final AttributeDesc attribute : classFile.getAttributes()) {
            final Pane pane = new AttributeEntry(attribute, classFile).buildPane();
            content.getChildren().add(pane);
        }

        return content;
    }

    /**
     * Builds the inheritance tab for the class file.
     *
     * @param classFile The class file to build the inheritance tab for.
     * @return The inheritance tab for the class file.
     */
    public static Pane buildInheritanceTab(final ClassFile classFile) {
        final VBox content = new VBox();

        for (final int interfaceIndex : classFile.getInterfaces()) {
            content.getChildren().add(new Label("Interface at index: " + interfaceIndex));
        }

        return content;
    }
}
