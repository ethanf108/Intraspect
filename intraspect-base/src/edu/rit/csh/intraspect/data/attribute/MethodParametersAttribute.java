package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The MethodParameters attribute.
 */
@AttributeName("MethodParameters")
public final class MethodParametersAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;
    private final Parameter[] parameters;

    public MethodParametersAttribute(final int attributeNameIndex, final Parameter[] parameters) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameters = parameters;
    }

    public static MethodParametersAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Ignore

        final Parameter[] params = new Parameter[in.readUnsignedByte()];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Parameter(in.readUnsignedShort(), in.readUnsignedShort());
        }

        return new MethodParametersAttribute(ani, params);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public Parameter[] getParameters() {
        return this.parameters;
    }

    @Override
    public int getDataLength() {
        return 1 + 4 * this.parameters.length;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeByte(this.parameters.length);

        for (final Parameter p : this.parameters) {
            out.writeShort(p.nameIndex);
            out.writeShort(p.accessFlags);
        }
    }

    public static record Parameter(
            @ConstantPoolIndex(value = UTF8Constant.class, nullable = true) int nameIndex,
            int accessFlags
    ) {

    }
}
