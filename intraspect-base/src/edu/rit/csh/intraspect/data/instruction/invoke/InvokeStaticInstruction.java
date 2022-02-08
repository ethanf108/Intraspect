package edu.rit.csh.intraspect.data.instruction.invoke;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.MethodRefConstant;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xB8, mnemonic = "invokestatic")
public final class InvokeStaticInstruction extends InvokeInstruction {

    public InvokeStaticInstruction(final int methodIndex) {
        super(methodIndex);
    }

    public static InvokeStaticInstruction read(final DataInputStream in) throws IOException {
        return new InvokeStaticInstruction(in.readUnsignedShort());
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
