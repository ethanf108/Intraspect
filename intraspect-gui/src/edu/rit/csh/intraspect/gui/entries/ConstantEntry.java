package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public final class ConstantEntry implements Entry {

    private final ConstantDesc constantDesc;
    private final ClassFile classFile;

    public ConstantEntry(final ConstantDesc constantDesc, final ClassFile classFile) {
        this.constantDesc = constantDesc;
        this.classFile = classFile;
    }

    public Pane buildPane() {
        return new HBox(new Label(constantDesc.getName() + " " + constantDesc.getInfo()));
    }
}
