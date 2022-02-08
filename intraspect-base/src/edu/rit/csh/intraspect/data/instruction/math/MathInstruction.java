package edu.rit.csh.intraspect.data.instruction.math;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.math.add.AddInstruction;
import edu.rit.csh.intraspect.data.instruction.math.and.AndInstruction;
import edu.rit.csh.intraspect.data.instruction.math.div.DivInstruction;
import edu.rit.csh.intraspect.data.instruction.math.mul.MulInstruction;
import edu.rit.csh.intraspect.data.instruction.math.or.OrInstruction;
import edu.rit.csh.intraspect.data.instruction.math.rem.RemInstruction;
import edu.rit.csh.intraspect.data.instruction.math.shl.ShlInstruction;
import edu.rit.csh.intraspect.data.instruction.math.shr.ShrInstruction;
import edu.rit.csh.intraspect.data.instruction.math.sub.SubInstruction;
import edu.rit.csh.intraspect.data.instruction.math.ushr.UshrInstruction;
import edu.rit.csh.intraspect.data.instruction.math.xor.XorInstruction;

import java.util.function.BinaryOperator;

public sealed abstract class MathInstruction<T extends Number> extends Instruction implements BinaryOperator<T> permits AddInstruction, AndInstruction, DivInstruction, MulInstruction, RemInstruction, ShlInstruction, ShrInstruction, SubInstruction, UshrInstruction, XorInstruction, OrInstruction {

    @Override
    public abstract T apply(final T a, final T b);

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }
}
