package edu.rit.csh.intraspect.data.instruction.wide.store;

import edu.rit.csh.intraspect.data.instruction.wide.WideInstruction;

import java.io.DataInputStream;
import java.io.IOException;

public sealed abstract class WideStoreInstruction extends WideInstruction permits WideIStoreInstruction, WideFStoreInstruction, WideAStoreInstruction, WideLStoreInstruction, WideDStoreInstruction {

    protected WideStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public static WideStoreInstruction read(final DataInputStream in, final int subOpcode) throws IOException {
        return switch (subOpcode) {
            case 0x36 -> new WideIStoreInstruction(subOpcode, in.readUnsignedShort());
            case 0x37 -> new WideLStoreInstruction(subOpcode, in.readUnsignedShort());
            case 0x38 -> new WideFStoreInstruction(subOpcode, in.readUnsignedShort());
            case 0x39 -> new WideDStoreInstruction(subOpcode, in.readUnsignedShort());
            case 0x3A -> new WideAStoreInstruction(subOpcode, in.readUnsignedShort());
            default -> throw new IllegalArgumentException("Invalid sub opcode");
        };
    }

    public abstract Class<?> getStoreType();
}
