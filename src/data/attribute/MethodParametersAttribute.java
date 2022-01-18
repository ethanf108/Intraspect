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

    public MethodParametersAttribute(int attributeNameIndex, Parameter[] parameters) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameters = parameters;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public int getDataLength() {
        return 1 + 4 * this.parameters.length;
    }

    public static MethodParametersAttribute read(int ani, DataInputStream in) throws IOException {
        in.readInt();    // Ignore
        final int numParameters = in.readUnsignedByte();
        final Parameter[] params = new Parameter[numParameters];
        for (int i = 0; i < numParameters; i++) {
            params[i] = new Parameter(in.readUnsignedShort(), in.readUnsignedShort());
        }
        return new MethodParametersAttribute(ani, params);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeByte(this.parameters.length);
        for (Parameter p : this.parameters) {
            out.writeShort(p.nameIndex);
            out.writeShort(p.accessFlags);
        }
    }

}
