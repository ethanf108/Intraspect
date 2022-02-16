package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.MethodDesc;
import javafx.scene.layout.Pane;

public record MethodEntry(MethodDesc methodDesk,
                          ClassFile classFile) implements Entry {

    @Override
    public Pane buildPane() {
        return new Pane();
    }
}
