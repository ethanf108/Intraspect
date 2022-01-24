package data.instruction;

import data.ClassFile;
import java.io.DataOutputStream;
import java.io.IOException;

public sealed abstract class Instruction permits NopInstruction, ConversionInstruction {

    private transient int opcodeCache = -1;
    private transient String mnemonicCache = null;

    protected Instruction() {

    }

    private void cacheOpcodeValues() {
        final Opcode opcode = this.getClass().getAnnotation(Opcode.class);
        if (opcode.mnemonic() == null || opcode.mnemonic().isBlank()) {
            throw new IllegalStateException("Opcode Annotation has invalid Mnemonic");
        }
        if (opcode.opcode() < 0 || opcode.opcode() > 255) {
            throw new IllegalStateException("Opcode Annotation has invalid Opcode");
        }
        this.opcodeCache = opcode.opcode();
        this.mnemonicCache = opcode.mnemonic();
    }

    public final int getOpcode() {
        if (this.opcodeCache == -1) {
            this.cacheOpcodeValues();
        }
        return this.opcodeCache;
    }

    public final String getMnemonic() {
        if (this.mnemonicCache == null) {
            this.cacheOpcodeValues();
        }
        return this.mnemonicCache;
    }

    public abstract int getNumOperands();

    public abstract int[] getOperands();

    public abstract boolean isValid(ClassFile ref);

    public abstract void write(DataOutputStream out) throws IOException;

    public int getDataLength() {
        return 1 + this.getNumOperands();
    }
}
