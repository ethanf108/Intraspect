package data.attribute.annotation;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public class AnnotationDesc {

    private final short typeIndex;
    private final ElementValue.ElementValuePair[] elementValuePairs;

    public AnnotationDesc(short typeIndex, ElementValue.ElementValuePair[] elementValuePairs) {
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public short getTypeIndex() {
        return typeIndex;
    }

    public ElementValue.ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

    public int getDataLength() {
        int length = 4;
        for (ElementValue.ElementValuePair elementValuePair : elementValuePairs) {
            length += elementValuePair.getDataLength();
        }
        return length;
    }

    public static AnnotationDesc read(DataInputStream in) throws IOException {
        final short typeIndex = in.readUnsignedShort();
        final short numElementValuePairs = in.readUnsignedShort();
        final ElementValue.ElementValuePair[] elementValuePairs = new ElementValue.ElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {

        }
        return new AnnotationDesc(typeIndex, elementValuePairs);
    }

    public void write(OutputStream out) throws IOException {
        writeShort(out, this.typeIndex);
        writeShort(out, (short) this.elementValuePairs.length);
        for (ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            elementValuePair.write(out);
        }
    }
}
