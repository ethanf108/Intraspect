package data.instruction.constant;

import data.instruction.ConstantInstruction;

import java.io.DataInputStream;
import java.io.IOException;

public final class AConstNullInstruction extends ConstantInstruction {
    public static AConstNullInstruction read(DataInputStream in) throws IOException {
        return new AConstNullInstruction();
    }

    @Override
    public Class<?> getConstantType() {
        return Object.class;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }
}
