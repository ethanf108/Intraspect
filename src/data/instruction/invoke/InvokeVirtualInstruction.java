package data.instruction.invoke;

import data.ClassFile;
import data.constant.MethodRefConstant;
import data.instruction.InvokeInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xB6, mnemonic = "invokevirtual")
public final class InvokeVirtualInstruction extends InvokeInstruction {

    public InvokeVirtualInstruction(final int methodIndex) {
        super(methodIndex);
    }

    public static InvokeVirtualInstruction read(final DataInputStream in) throws IOException {
        return new InvokeVirtualInstruction(in.readUnsignedShort());
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF};
    }

    @Override
    public Optional<MethodRefConstant> getMethodRef(final ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.methodIndex) instanceof MethodRefConstant mrc ? mrc : null);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.methodIndex) instanceof MethodRefConstant;
    }

}
