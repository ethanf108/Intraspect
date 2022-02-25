package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Optional;

@Opcode(opcode = 0xC5, mnemonic = "multianewarray")
public final class MultiANewArrayInstruction extends Instruction {

    @ConstantPoolIndex(ClassConstant.class)
    private final int classIndex;

    private final int numDimensions;

    @AssembleInject
    public MultiANewArrayInstruction(final int classIndex, final int numDimensions) {
        this.classIndex = classIndex;
        this.numDimensions = numDimensions;
    }

    public static MultiANewArrayInstruction read(final DataInputStream in) throws IOException {
        return new MultiANewArrayInstruction(in.readUnsignedShort(), in.readUnsignedByte());
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getNumDimensions() {
        return this.numDimensions;
    }

    public Optional<ClassConstant> getClassConstant(final ClassFile ref) {
        if (this.classIndex == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc ? cc : null);
    }

    @Override
    public int getNumOperands() {
        return 3;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.classIndex & 0xFF00) >> 8, this.classIndex & 0xFF, this.numDimensions & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant;
    }
}
