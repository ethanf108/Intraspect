package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.Util.*;
import static util.Util.readShort;

/*
PermittedSubclasses_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 number_of_classes;
    u2 classes[number_of_classes];
}
 */

@AttributeName("PermittedSubclasses")
public class PermittedSubclassesAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short[] classes;

    private PermittedSubclassesAttribute(final short attributeNameIndex, final short[] classes) {
        this.attributeNameIndex = attributeNameIndex;
        this.classes = classes;
    }

    public static PermittedSubclassesAttribute read(final short ani, final InputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final short[] arr = new short[readShort(in)];
        for (int i = 0; i < arr.length; arr[i++] = readShort(in)) ;

        return new PermittedSubclassesAttribute(ani, arr);
    }


    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getAttributeLength() {
        return getNumberOfClasses() * 2 + 2;
    }

    public short getNumberOfClasses() {
        return (short) this.classes.length;
    }

    public short[] getClasses() {
        return this.classes;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, getAttributeLength());
        writeShort(out, getNumberOfClasses());

        for (short aClass : this.classes) {
            writeShort(out, aClass);
        }
    }
}