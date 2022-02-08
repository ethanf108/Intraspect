package edu.rit.csh.intraspect.data.instruction.wide;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.data.instruction.wide.load.WideLoadInstruction;
import edu.rit.csh.intraspect.data.instruction.wide.store.WideStoreInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC4, mnemonic = "wide")
public sealed abstract class WideInstruction extends Instruction permits WideIincInstruction, WideLoadInstruction, WideStoreInstruction, WideRetInstruction, WideUnknownInstruction {

    protected final int subOpcode;
    protected final int localVariableIndex;

    protected WideInstruction(final int subOpcode, final int localVariableIndex) {
        this.subOpcode = subOpcode;
        this.localVariableIndex = localVariableIndex;
    }

    public static WideInstruction read(final DataInputStream in) throws IOException {
        final int subOpcode = in.readUnsignedByte();
        return switch (subOpcode) {
            case 0x15, 0x16, 0x17, 0x18, 0x19 -> WideLoadInstruction.read(in, subOpcode);
            case 0x36, 0x37, 0x38, 0x39, 0x3A -> WideStoreInstruction.read(in, subOpcode);
            case 0x84 -> new WideIincInstruction(subOpcode, in.readUnsignedShort(), in.readUnsignedShort());
            case 0xA9 -> new WideRetInstruction(subOpcode, in.readUnsignedShort());
            default -> new WideUnknownInstruction(subOpcode);
        };
    }

    public final int getSubOpcode() {
        return this.subOpcode;
    }

    public abstract String getSubMnemonic();

    public final int getLocalVariableIndex() {
        return this.localVariableIndex;
    }

    @Override
    public int getNumOperands() {
        return 3;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.subOpcode & 0xFF, (this.localVariableIndex & 0xFF00) >> 8, this.localVariableIndex & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO check local variable index
        return true;
    }
}
