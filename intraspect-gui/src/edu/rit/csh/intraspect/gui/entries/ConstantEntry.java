package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public record ConstantEntry(ConstantDesc constantDesc,
                            ClassFile classFile) implements Entry {

    @Override
    public Pane buildPane() {
        return new HBox(new Label(this.constantDesc.getName() + " " + this.constantDesc.getInfo()));
    }
}
