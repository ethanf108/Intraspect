package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.*;

@AttributeName("PermittedSubclasses")
public class PermittedSubclassesAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] classes;

    private PermittedSubclassesAttribute(final int attributeNameIndex, final int[] classes) {
        this.attributeNameIndex = attributeNameIndex;
        this.classes = classes;
    }

    public static PermittedSubclassesAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final int[] arr = new int[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = in.readUnsignedShort()) ;

        return new PermittedSubclassesAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return getNumberOfClasses() * 2 + 2;
    }

    public short getNumberOfClasses() {
        return (short) this.classes.length;
    }

    public int[] getClasses() {
        return this.classes;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, getDataLength());
        writeShort(out, getNumberOfClasses());

        for (short aClass : this.classes) {
            writeShort(out, aClass);
        }
    }
}
