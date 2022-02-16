package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.NameAndTypeConstant;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The EnclosingMethod attribute.
 */
@AttributeName("EnclosingMethod")
public final class EnclosingMethodAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 4;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    @ConstantPoolIndex(ClassConstant.class)
    private final int classIndex;

    @ConstantPoolIndex(value = NameAndTypeConstant.class, nullable = true)
    private final int methodIndex;

    private EnclosingMethodAttribute(final int attributeNameIndex, final int classIndex, final int methodIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.classIndex = classIndex;
        this.methodIndex = methodIndex;
    }

    public static EnclosingMethodAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new EnclosingMethodAttribute(ani, in.readUnsignedShort(), in.readUnsignedShort());
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.classIndex);
        out.writeShort(this.methodIndex);
    }

    @Override
    public int getDataLength() {
        return EnclosingMethodAttribute.ATTRIBUTE_LENGTH;
    }
}
