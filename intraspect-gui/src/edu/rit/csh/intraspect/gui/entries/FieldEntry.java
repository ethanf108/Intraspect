package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.FieldDesc;
import javafx.scene.layout.Pane;

public final class FieldEntry implements Entry {

    private final FieldDesc fieldDesc;
    private final ClassFile classFile;

    public FieldEntry(final FieldDesc fieldDesc, final ClassFile classFile) {
        this.fieldDesc = fieldDesc;
        this.classFile = classFile;
    }

    @Override
    public Pane buildPane() {
        return null;
    }
}
