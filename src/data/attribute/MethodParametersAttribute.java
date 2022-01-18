package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("MethodParameters")
public class MethodParametersAttribute implements AttributeDesc {

    public static record Parameter(short nameIndex, short accessFlags) {

    }

    private final short attributeNameIndex;
    private final Parameter[] parameters;

    public MethodParametersAttribute(short attributeNameIndex, Parameter[] parameters) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameters = parameters;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public int getDataLength() {
        return 1 + 4 * this.parameters.length;
    }

    public static MethodParametersAttribute read(short ani, DataInputStream in) throws IOException {
        readInt(in);    // Ignore
        final byte numParameters = (byte) in.read();
        final Parameter[] params = new Parameter[numParameters];
        for (int i = 0; i < numParameters; i++) {
            params[i] = new Parameter(in.readUnsignedShort(), in.readUnsignedShort());
        }
        return new MethodParametersAttribute(ani, params);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeInt(out, this.getDataLength());
        out.write((byte) this.parameters.length);
        for (Parameter p : this.parameters) {
            writeShort(out, p.nameIndex);
            writeShort(out, p.accessFlags);
        }
    }

}
