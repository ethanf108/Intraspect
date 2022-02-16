package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.MethodDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import javafx.scene.layout.Pane;

public final class AttributeEntry implements Entry {

    private final AttributeDesc attributeDesc;
    private final ClassFile classFile;

    public AttributeEntry(final AttributeDesc attributeDesc, final ClassFile classFile) {
        this.attributeDesc = attributeDesc;
        this.classFile = classFile;
    }

    public Pane buildPane() {
        return null;
    }
}
