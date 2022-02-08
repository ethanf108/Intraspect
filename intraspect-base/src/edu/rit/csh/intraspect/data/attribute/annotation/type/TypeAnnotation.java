package edu.rit.csh.intraspect.data.attribute.annotation.type;

import edu.rit.csh.intraspect.data.attribute.annotation.ElementValue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeAnnotation {

    private final TargetInfo targetInfo;
    private final TypePath[] targetPath;
    private final int typeIndex;
    private final ElementValue.ElementValuePair[] elementValuePairs;

    public TypeAnnotation(final TargetInfo targetInfo, final TypePath[] targetPath, final int typeIndex, final ElementValue.ElementValuePair[] elementValuePairs) {
        this.targetInfo = targetInfo;
        this.targetPath = targetPath;
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public static TypeAnnotation read(final DataInputStream in) throws IOException {

        final TargetInfo targetInfo = TargetInfo.read(in);

        final int typePathLength = in.readUnsignedByte();
        final TypePath[] targetPath = new TypePath[typePathLength];
        for (int i = 0; i < targetPath.length; i++) {
            targetPath[i] = TypePath.read(in);
        }

        final int typeIndex = in.readUnsignedShort();

        final int numElementValuePairs = in.readUnsignedShort();
        final ElementValue.ElementValuePair[] elementValuePairs = new ElementValue.ElementValuePair[numElementValuePairs];
        for (int i = 0; i < elementValuePairs.length; i++) {
            elementValuePairs[i] = ElementValue.ElementValuePair.read(in);
        }

        return new TypeAnnotation(targetInfo, targetPath, typeIndex, elementValuePairs);
    }

    public TargetInfo getTargetInfo() {
        return this.targetInfo;
    }

    public TypePath[] getTargetPath() {
        return this.targetPath;
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public ElementValue.ElementValuePair[] getElementValuePairs() {
        return this.elementValuePairs;
    }

    public int getDataLength() {
        int dataLength = 6 + targetInfo.getDataLength() + (targetPath.length << 1);

        for (final ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            dataLength += elementValuePair.getDataLength();
        }

        return dataLength;
    }

    public void write(final DataOutputStream out) throws IOException {

        this.targetInfo.write(out);

        for (final TypePath typePath : this.targetPath) {
            typePath.write(out);
        }

        out.writeShort(this.typeIndex);

        out.writeShort(this.elementValuePairs.length);

        for (final ElementValue.ElementValuePair elementValuePair : this.elementValuePairs) {
            elementValuePair.write(out);
        }
    }

    public static record TypePath(int typePathKind, int typeArgumentIndex) {

        public static TypePath read(final DataInputStream in) throws IOException {
            return new TypePath(in.readUnsignedByte(), in.readUnsignedByte());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeByte(this.typePathKind);
            out.writeByte(this.typeArgumentIndex);
        }
    }

}
