package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.FieldDesc;
import javafx.scene.layout.Pane;

public record FieldEntry(FieldDesc fieldDesc,
                         ClassFile classFile) implements Entry {

    @Override
    public Pane buildPane() {
        return new Pane();
    }
}
