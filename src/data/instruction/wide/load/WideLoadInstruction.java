package data.instruction.wide.load;

import data.instruction.wide.WideInstruction;

import java.io.DataInputStream;
import java.io.IOException;

public sealed abstract class WideLoadInstruction extends WideInstruction permits WideILoadInstruction, WideFLoadInstruction, WideALoadInstruction, WideLLoadInstruction, WideDLoadInstruction {


    protected WideLoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public static WideLoadInstruction read(DataInputStream in, int subOpcode) throws IOException {
        return switch (subOpcode) {
            case 0x15 -> new WideILoadInstruction(subOpcode, in.readUnsignedShort());
            case 0x16 -> new WideLLoadInstruction(subOpcode, in.readUnsignedShort());
            case 0x17 -> new WideFLoadInstruction(subOpcode, in.readUnsignedShort());
            case 0x18 -> new WideDLoadInstruction(subOpcode, in.readUnsignedShort());
            case 0x19 -> new WideALoadInstruction(subOpcode, in.readUnsignedShort());
            default -> throw new IllegalArgumentException("Invalid sub opcode");
        };
    }

    public abstract Class<?> getLoadType();
}