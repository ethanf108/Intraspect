package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("NestMembers")
public class NestMembersAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short[] classes;

    private NestMembersAttribute(final short attributeNameIndex, final short[] classes) {
        this.attributeNameIndex = attributeNameIndex;
        this.classes = classes;
    }

    public static NestMembersAttribute read(final short ani, final DataInputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final short[] arr = new short[readShort(in)];
        for (int i = 0; i < arr.length; arr[i++] = readShort(in)) ;

        return new NestMembersAttribute(ani, arr);
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
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
        writeInt(out, getDataLength());
        writeShort(out, getNumberOfClasses());

        for (short aClass : this.classes) {
            writeShort(out, aClass);
        }
    }
}