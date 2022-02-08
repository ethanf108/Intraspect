package edu.rit.csh.intraspect.data.instruction.invoke;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.DynamicConstant;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xBA, mnemonic = "invokedynamic")
public final class InvokeDynamicInstruction extends InvokeInstruction {

    public InvokeDynamicInstruction(final int methodIndex) {
        super(methodIndex);
    }

    public static InvokeDynamicInstruction read(final DataInputStream in) throws IOException {
        final InvokeDynamicInstruction ret = new InvokeDynamicInstruction(in.readUnsignedShort());
        in.read();
        in.read();
        return ret;
    }

    @Override
    public int getNumOperands() {
        return 4;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF, 0, 0};
    }

    @Override
    public Optional<DynamicConstant> getMethodRef(final ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.methodIndex) instanceof DynamicConstant dc ? dc : null);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.methodIndex) instanceof DynamicConstant;
    }
}
