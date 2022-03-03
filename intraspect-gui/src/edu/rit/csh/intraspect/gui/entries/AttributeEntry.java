package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.attribute.AttributeDesc;

public record AttributeEntry(AttributeDesc attributeDesc,
                             ClassFile classFile) implements Entry {

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet");
    }

}
