package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x06, mnemonic = "iconst_3")
public final class IConst_3Instruction extends IConstInstruction {

    public IConst_3Instruction() {

    }

    @Override
    public Integer getValue() {
        return 3;
    }
}
