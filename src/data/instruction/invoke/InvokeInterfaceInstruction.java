package data.instruction.invoke;

import data.ClassFile;
import data.constant.InterfaceMethodRefConstant;
import data.instruction.InvokeInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xB9, mnemonic = "invokeinterface")
public final class InvokeInterfaceInstruction extends InvokeInstruction {

    private final int count;

    public InvokeInterfaceInstruction(final int methodIndex, final int count) {
        super(methodIndex);
        this.count = count;
    }

    public static InvokeInterfaceInstruction read(final DataInputStream in) throws IOException {
        final int methodIndex = in.readUnsignedShort();
        final int count = in.readUnsignedByte();
        in.readByte(); //Unused by spec
        return new InvokeInterfaceInstruction(methodIndex, count);
    }

    @Override
    public int getNumOperands() {
        return 4;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF, count, 0};
    }

    public int getCount() {
        return count;
    }

    @Override
    public Optional<InterfaceMethodRefConstant> getMethodRef(final ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.methodIndex) instanceof InterfaceMethodRefConstant imrc ? imrc : null);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.methodIndex) instanceof InterfaceMethodRefConstant;
    }
}
