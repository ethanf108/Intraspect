package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The PermittedSubclasses attribute.
 */
@AttributeName("PermittedSubclasses")
public final class PermittedSubclassesAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    @ConstantPoolIndex(ClassConstant.class)
    private final int[] classes;

    private PermittedSubclassesAttribute(final int attributeNameIndex, final int[] classes) {
        this.attributeNameIndex = attributeNameIndex;
        this.classes = classes;
    }

    public static PermittedSubclassesAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final int[] arr = new int[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = in.readUnsignedShort()) {
            ;
        }

        return new PermittedSubclassesAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.getNumberOfClasses() * 2 + 2;
    }

    public int getNumberOfClasses() {
        return this.classes.length;
    }

    public int[] getClasses() {
        return this.classes;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.getNumberOfClasses());

        for (final int aClass : this.classes) {
            out.writeShort(aClass);
        }
    }
}
