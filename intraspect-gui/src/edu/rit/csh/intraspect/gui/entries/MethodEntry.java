package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.MethodDesc;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public final class MethodEntry implements Entry {

    private final MethodDesc methodDesk;
    private final ClassFile classFile;

    public MethodEntry(final MethodDesc methodDesk, final ClassFile classFile) {
        this.methodDesk = methodDesk;
        this.classFile = classFile;
    }

    public Pane buildPane() {
        return null;
    }
}
