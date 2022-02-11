package edu.rit.csh.intraspect.data.instruction;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.branch.*;
import edu.rit.csh.intraspect.data.instruction.compare.CompareInstruction;
import edu.rit.csh.intraspect.data.instruction.constant.ConstantInstruction;
import edu.rit.csh.intraspect.data.instruction.control.ReturnInstruction;
import edu.rit.csh.intraspect.data.instruction.conversion.ConversionInstruction;
import edu.rit.csh.intraspect.data.instruction.field.FieldInstruction;
import edu.rit.csh.intraspect.data.instruction.invoke.InvokeInstruction;
import edu.rit.csh.intraspect.data.instruction.load.ArrayLoadInstruction;
import edu.rit.csh.intraspect.data.instruction.load.LoadConstantInstruction;
import edu.rit.csh.intraspect.data.instruction.load.LoadInstruction;
import edu.rit.csh.intraspect.data.instruction.math.MathInstruction;
import edu.rit.csh.intraspect.data.instruction.math.neg.NegInstruction;
import edu.rit.csh.intraspect.data.instruction.misc.*;
import edu.rit.csh.intraspect.data.instruction.monitor.MonitorInstruction;
import edu.rit.csh.intraspect.data.instruction.object.MultiANewArrayInstruction;
import edu.rit.csh.intraspect.data.instruction.object.NewArrayInstruction;
import edu.rit.csh.intraspect.data.instruction.object.ObjectInstruction;
import edu.rit.csh.intraspect.data.instruction.reserved.ReservedInstruction;
import edu.rit.csh.intraspect.data.instruction.stack.StackInstruction;
import edu.rit.csh.intraspect.data.instruction.store.ArrayStoreInstruction;
import edu.rit.csh.intraspect.data.instruction.store.StoreInstruction;
import edu.rit.csh.intraspect.data.instruction.wide.WideInstruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public sealed abstract class Instruction permits
        NopInstruction, ConversionInstruction, InvokeInstruction, ConstantInstruction,
        ReservedInstruction, LoadInstruction, ArrayLoadInstruction, StoreInstruction,
        ArrayStoreInstruction, StackInstruction, ReturnInstruction, UnknownInstruction,
        CompareInstruction, BranchInstruction, MathInstruction, IincInstruction,
        LoadConstantInstruction, NegInstruction, AThrowInstruction, ObjectInstruction,
        FieldInstruction, ArrayLengthInstruction, RetInstruction, MonitorInstruction,
        MultiANewArrayInstruction, NewArrayInstruction, BranchWideInstruction, TableSwitchInstruction,
        WideInstruction, LookupSwitchInstruction {

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

    public boolean isPadded() {
        return false;
    }

    public abstract int getNumOperands();

    public abstract int[] getOperands();

    public abstract boolean isValid(final ClassFile ref);

    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getOpcode());
        for (int i : this.getOperands()) {
            out.writeByte(i);
        }
    }

    public final int getDataLength() {
        return 1 + this.getNumOperands();
    }

    @Override
    public boolean equals(final Object obj) {
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
