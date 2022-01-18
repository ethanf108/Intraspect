package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.AttributeReader;
import data.ClassFile;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Code")
public class CodeAttribute implements AttributeDesc {

    public static record ExceptionDesc(int startPc, int endPc, int handlerPc, int catchType) {

    }

    private final int attributeNameIndex;
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code;
    private final ExceptionDesc[] exceptionTable;
    private final AttributeDesc[] attributes;

    public CodeAttribute(int attributeNameIndex, int maxStack, int maxLocals, byte[] code, ExceptionDesc[] exceptionTable, AttributeDesc[] attributes) {
        this.attributeNameIndex = attributeNameIndex;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionDesc[] getExceptionTable() {
        return exceptionTable;
    }

    public AttributeDesc[] getAttributes() {
        return attributes;
    }

    public static CodeAttribute read(short ani, DataInputStream in, ClassFile ref) throws IOException {
        in.readInt();   // Ignore

        final int maxStack = in.readUnsignedShort();
        final int maxLocals = in.readUnsignedShort();
        final int codeLength = in.readInt();
        final byte[] code = new byte[codeLength];

        in.read(code);

        final int exceptionTableLength = in.readUnsignedShort();
        final ExceptionDesc[] exceptions = new ExceptionDesc[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptions[i] = new ExceptionDesc(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }
        final int attributesCount = in.readUnsignedShort();
        final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new CodeAttribute(ani, maxStack, maxLocals, code, exceptions, attributes);
    }

    @Override
    public int getDataLength() {
        int attributeLength = 0;
        for (AttributeDesc ad : this.attributes) {
            attributeLength += ad.getDataLength() + 6;
        }
        return 12 + this.code.length + 8 * this.exceptionTable.length + attributeLength;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, this.maxStack);
        writeShort(out, this.maxLocals);
        writeInt(out, this.code.length);
        out.write(this.code);
        writeShort(out, (short) this.exceptionTable.length);
        for (ExceptionDesc ed : this.exceptionTable) {
            writeShort(out, ed.startPc);
            writeShort(out, ed.endPc);
            writeShort(out, ed.handlerPc);
            writeShort(out, ed.catchType);
        }
        writeShort(out, (short) this.attributes.length);
        for (AttributeDesc ad : this.attributes) {
            ad.write(out);
        }
    }
}
