package data.instruction.wide;

public final class WideRetInstruction extends WideInstruction {

    public WideRetInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @Override
    public String getSubMnemonic() {
        return "ret_w";
    }
}
