package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.MethodDesc;

public record MethodEntry(MethodDesc methodDesk,
                          ClassFile classFile) implements Entry {

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
