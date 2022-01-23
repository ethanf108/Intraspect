package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * A ConstantDescriptor for InterfaceMethodRef constants.
 */
public class InterfaceMethodRefConstant implements ConstantDesc {

    private final int classIndex;
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
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
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
