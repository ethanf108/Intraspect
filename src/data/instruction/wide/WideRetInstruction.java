package data.instruction.wide;

public final class WideRetInstruction extends WideInstruction {

    public WideRetInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @Override
    public String getSubMnemonic() {
        return "ret_w";
    }
}
