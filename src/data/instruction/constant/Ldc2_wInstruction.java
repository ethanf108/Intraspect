package data.instruction.constant;

import data.ClassFile;
import data.ConstantDesc;
import data.constant.DoubleConstant;
import data.constant.LongConstant;
import data.instruction.LoadConstantInstruction;

import java.io.DataInputStream;
import java.io.IOException;

public final class Ldc2_wInstruction extends LoadConstantInstruction {

    public Ldc2_wInstruction(int constantPoolIndex) {
        super(constantPoolIndex);
    }

    public static Ldc2_wInstruction read(DataInputStream in) throws IOException {
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
    public boolean isValid(ClassFile ref) {
        //TODO Check Dynamic Constant
        final ConstantDesc cd = ref.getConstantDesc(this.constantPoolIndex);
        return this.constantPoolIndex > 0 && (cd instanceof LongConstant || cd instanceof DoubleConstant);
    }
}
