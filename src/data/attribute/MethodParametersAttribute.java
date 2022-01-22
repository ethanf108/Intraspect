package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("MethodParameters")
public class MethodParametersAttribute implements AttributeDesc {

    public static record Parameter(int nameIndex, int accessFlags) {

    }

    private final int attributeNameIndex;
    private final Parameter[] parameters;

    public MethodParametersAttribute(final int attributeNameIndex, final Parameter[] parameters) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameters = parameters;
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

    public static MethodParametersAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Ignore

        final Parameter[] params = new Parameter[in.readUnsignedByte()];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Parameter(in.readUnsignedShort(), in.readUnsignedShort());
        }

        return new MethodParametersAttribute(ani, params);
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
}
