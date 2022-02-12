package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for MethodHandle constants.
 */
public final class MethodHandleConstant implements ConstantDesc {

    private int kind;

    @ConstantPoolIndex({FieldRefConstant.class, MethodRefConstant.class, InterfaceMethodRefConstant.class})
    private int referenceIndex;

    public MethodHandleConstant(final int kind, final int ref) {
        this.kind = kind;
        this.referenceIndex = ref;
    }

    @Override
    public int getTag() {
        return 15;
    }

    public int getKind() {
        return this.kind;
    }

    public int getReferenceIndex() {
        return this.referenceIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        String methodName = null;
        return switch (this.kind) {
            case 1, 2, 3, 4 -> ref.getConstantDesc(this.referenceIndex) instanceof FieldRefConstant;
            case 5, 8 -> ref.getConstantDesc(this.referenceIndex) instanceof MethodRefConstant;
            case 6, 7 -> ref.getConstantDesc(this.referenceIndex) instanceof MethodRefConstant || (ref.getMajorVersion().getMajorVersion() < 52 && ref.getConstantDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant);
            case 9 -> ref.getConstantDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant;
            default -> false;
        } && switch (this.kind) {
            case 5, 6, 7, 9:
                if (ref.getConstantDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant imrc) {
                    if (ref.getConstantDesc(imrc.getNameAndTypeIndex()) instanceof NameAndTypeConstant natc
                            && ref.getConstantDesc(natc.getNameIndex()) instanceof UTF8Constant u) {
                        methodName = u.getValue();
                    } else {
                        yield false;
                    }
                }
            case 8:
                if (ref.getConstantDesc(this.referenceIndex) instanceof MethodRefConstant mrc) {
                    if (ref.getConstantDesc(mrc.getNameAndTypeIndex()) instanceof NameAndTypeConstant natc
                            && ref.getConstantDesc(natc.getNameIndex()) instanceof UTF8Constant u) {
                        methodName = u.getValue();
                    } else {
                        yield false;
                    }
                }
                if (methodName == null) {
                    yield false;
                }
                if (this.kind == 8) {
                    yield methodName.equals("<init>");
                } else {
                    yield !methodName.equals("<init>") && !methodName.equals("<clinit>");
                }
            case 1, 2, 3, 4:
                yield true;
            default:
                yield false;
        };
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeByte(this.kind);
        out.writeShort(this.referenceIndex);
    }
}
