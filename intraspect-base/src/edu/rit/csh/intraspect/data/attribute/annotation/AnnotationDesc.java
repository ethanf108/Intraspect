package edu.rit.csh.intraspect.data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AnnotationDesc {

    private final int typeIndex;
    private final ElementValue.ElementValuePair[] elementValuePairs;

    public AnnotationDesc(final int typeIndex, final ElementValue.ElementValuePair[] elementValuePairs) {
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public static AnnotationDesc read(final DataInputStream in) throws IOException {
        final int typeIndex = in.readUnsignedShort();

        final ElementValue.ElementValuePair[] elementValuePairs = new ElementValue.ElementValuePair[in.readUnsignedShort()];
        for (int i = 0; i < elementValuePairs.length; i++) {
            elementValuePairs[i] = ElementValue.ElementValuePair.read(in);
        }

        return new AnnotationDesc(typeIndex, elementValuePairs);
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public ElementValue.ElementValuePair[] getElementValuePairs() {
        return this.elementValuePairs;
    }

    public int getDataLength() {
        int length = 4;
        for (final ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            length += elementValuePair.getDataLength();
        }
        return length;
    }

    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.typeIndex);
        out.writeShort(this.elementValuePairs.length);
        for (final ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            elementValuePair.write(out);
        }
    }
}
