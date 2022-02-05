package data.instruction;

import data.ClassFile;
import data.instruction.control.ReturnInstruction;
import data.instruction.load.ArrayLoadInstruction;
import data.instruction.math.NegInstruction;
import data.instruction.misc.IincInstruction;
import data.instruction.misc.NopInstruction;
import data.instruction.store.ArrayStoreInstruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public sealed abstract class Instruction permits
        NopInstruction, ConversionInstruction, InvokeInstruction, ConstantInstruction,
        ReservedInstruction, LoadInstruction, ArrayLoadInstruction, StoreInstruction,
        ArrayStoreInstruction, StackInstruction, ReturnInstruction, UnknownInstruction,
        CompareInstruction, IfInstruction, IfCompareInstruction, IfObjectInstruction,
        MathInstruction, IincInstruction, LoadConstantInstruction, NegInstruction {

    private transient String toStringCache = null;
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

    public int getOpcode() {
        if (this.opcodeCache == -1) {
            this.cacheOpcodeValues();
        }
        return this.opcodeCache;
    }

    public String getMnemonic() {
        if (this.mnemonicCache == null) {
            this.cacheOpcodeValues();
        }
        return this.mnemonicCache;
    }

    public abstract int getNumOperands();

    public abstract int[] getOperands();

    public abstract boolean isValid(ClassFile ref);

    public final void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getOpcode());
        for (int i : this.getOperands()) {
            out.writeByte(i);
        }
    }

    public final int getDataLength() {
        return 1 + this.getNumOperands();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Instruction other && this.getOpcode() == other.getOpcode() && Arrays.equals(this.getOperands(), other.getOperands());
    }

    @Override
    public String toString() {
        if (this.toStringCache == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.getMnemonic());
            for (int i : this.getOperands()) {
                sb
                        .append(" ")
                        .append("0x")
                        .append(Integer.toHexString(i));
            }
            this.toStringCache = sb.toString();
        }
        return this.toStringCache;
    }
}
