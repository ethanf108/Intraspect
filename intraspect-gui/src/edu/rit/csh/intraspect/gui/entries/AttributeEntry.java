package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import javafx.scene.layout.Pane;

public record AttributeEntry(AttributeDesc attributeDesc,
                             ClassFile classFile) implements Entry {

    @Override
    public Pane buildPane() {
        return new Pane();
    }
}
