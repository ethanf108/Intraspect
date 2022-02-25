package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.DoubleConstant;
import edu.rit.csh.intraspect.data.constant.LongConstant;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.data.instruction.load.LoadConstantInstruction;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x14, mnemonic = "ldc2_w")
public final class Ldc2_wInstruction extends LoadConstantInstruction {

    @AssembleInject
    public Ldc2_wInstruction(final int constantPoolIndex) {
        super(constantPoolIndex);
    }

    public static Ldc2_wInstruction read(final DataInputStream in) throws IOException {
        return new Ldc2_wInstruction(in.readUnsignedShort());
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.constantPoolIndex & 0xFF00) >> 8, this.constantPoolIndex & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check Dynamic Constant
        final ConstantDesc cd = ref.getConstantDesc(this.constantPoolIndex);
        return this.constantPoolIndex > 0 && (cd instanceof LongConstant || cd instanceof DoubleConstant);
    }
}
