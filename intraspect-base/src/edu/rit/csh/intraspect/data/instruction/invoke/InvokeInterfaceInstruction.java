package edu.rit.csh.intraspect.data.instruction.invoke;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.InterfaceMethodRefConstant;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xB9, mnemonic = "invokeinterface")
public final class InvokeInterfaceInstruction extends InvokeInstruction {

    private final int count;

    @AssembleInject
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
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF, this.count, 0};
    }

    public int getCount() {
        return this.count;
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
