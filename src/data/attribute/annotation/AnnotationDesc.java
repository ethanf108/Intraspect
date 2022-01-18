package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AnnotationDesc {

    private final int typeIndex;
    private final ElementValue.ElementValuePair[] elementValuePairs;

    public AnnotationDesc(int typeIndex, ElementValue.ElementValuePair[] elementValuePairs) {
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public int getTypeIndex() {
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
        final int typeIndex = in.readUnsignedShort();
        final int numElementValuePairs = in.readUnsignedShort();
        final ElementValue.ElementValuePair[] elementValuePairs = new ElementValue.ElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {

        }
        return new AnnotationDesc(typeIndex, elementValuePairs);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.typeIndex);
        out.writeShort((short) this.elementValuePairs.length);
        for (ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            elementValuePair.write(out);
        }
    }
}
