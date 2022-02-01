package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x05, mnemonic = "iconst_2")
public final class IConst_2Instruction extends IConstInstruction {

    public IConst_2Instruction() {

    }

    @Override
    public Integer getValue() {
        return 2;
    }
}
