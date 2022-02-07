package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.AttributeReader;
import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.InstructionCache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Code attribute.
 */
@AttributeName("Code")
public class CodeAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int maxStack;
    private final int maxLocals;
    private final Instruction[] code;
    private final ExceptionDesc[] exceptionTable;
    private final AttributeDesc[] attributes;

    private transient int instructionByteLengthCache;

    public CodeAttribute(final int attributeNameIndex, final int maxStack, final int maxLocals, final Instruction[] code, final ExceptionDesc[] exceptionTable, final AttributeDesc[] attributes) {
        this.attributeNameIndex = attributeNameIndex;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    public static CodeAttribute read(final int ani, final DataInputStream in, final ClassFile ref) throws IOException {
        in.readInt();   // Ignore

        final int maxStack = in.readUnsignedShort();
        final int maxLocals = in.readUnsignedShort();

        int instructionBytes = in.readInt();
        final int instructionByteCache = instructionBytes;

        List<Instruction> instructions = new ArrayList<>();

        while (instructionBytes > 0) {
            final Instruction instruction = InstructionCache.read(in, 3 - (instructionByteCache - instructionBytes) % 4);
            instructionBytes -= instruction.getDataLength();
            instructions.add(instruction);
        }

        final ExceptionDesc[] exceptions = new ExceptionDesc[in.readUnsignedShort()];
        for (int i = 0; i < exceptions.length; i++) {
            exceptions[i] = ExceptionDesc.read(in);
        }

        final AttributeDesc[] attributes = new AttributeDesc[in.readUnsignedShort()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }

        CodeAttribute ret = new CodeAttribute(ani, maxStack, maxLocals, instructions.toArray(new Instruction[0]), exceptions, attributes);
        ret.instructionByteLengthCache = instructionByteCache;
        return ret;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public Instruction[] getCode() {
        return this.code;
    }

    public ExceptionDesc[] getExceptionTable() {
        return this.exceptionTable;
    }

    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

    @Override
    public int getDataLength() {
        int attributeLength = 0;
        for (final AttributeDesc ad : this.attributes) {
            attributeLength += ad.getDataLength() + 6;
        }
        return 12 + this.instructionByteLengthCache + 8 * this.exceptionTable.length + attributeLength;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.maxStack);
        out.writeShort(this.maxLocals);

        out.writeInt(this.instructionByteLengthCache);
        for (Instruction instruction : this.code) {
            instruction.write(out);
        }

        out.writeShort(this.exceptionTable.length);
        for (final ExceptionDesc ed : this.exceptionTable) {
            ed.write(out);
        }

        out.writeShort(this.attributes.length);
        for (final AttributeDesc ad : this.attributes) {
            ad.write(out);
        }
    }

    public record ExceptionDesc(int startPc, int endPc, int handlerPc, int catchType) {

        public static ExceptionDesc read(DataInputStream in) throws IOException {
            return new ExceptionDesc(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.startPc);
            out.writeShort(this.endPc);
            out.writeShort(this.handlerPc);
            out.writeShort(this.catchType);
        }
    }
}
