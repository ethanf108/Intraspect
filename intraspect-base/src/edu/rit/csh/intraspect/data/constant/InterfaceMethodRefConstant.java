package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * A ConstantDescriptor for InterfaceMethodRef constants.
 */
public class InterfaceMethodRefConstant implements ConstantDesc {

    @ConstantPoolIndex(ClassConstant.class)
    private final int classIndex;

    @ConstantPoolIndex(NameAndTypeConstant.class)
    private final int nameAndTypeIndex;

    public InterfaceMethodRefConstant(final int classIndex, final int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 11;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getNameAndTypeIndex() {
        return this.nameAndTypeIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        if (!(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc)) {
            return false;
        }
        final Optional<Class<?>> refClass = cc.getReferencedClass(ref);
        if (refClass.isPresent() && !refClass.get().isInterface()) {
            return false;
        }
        return ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.classIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
