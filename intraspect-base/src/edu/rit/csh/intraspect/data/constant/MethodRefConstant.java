package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.ClassFiles;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * A ConstantDescriptor for MethodRefConstant constants.
 */
public final class MethodRefConstant implements ConstantDesc {

    @ConstantPoolIndex(ClassConstant.class)
    private final int classIndex;

    @ConstantPoolIndex(NameAndTypeConstant.class)
    private final int nameAndTypeIndex;

    public MethodRefConstant(final int classIndex, final int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 10;
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
        Optional<Class<?>> refClass = cc.getReferencedClass(ref);
        if (refClass.isPresent() && refClass.get().isInterface()) {
            return false;
        }
        if (!(ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant natc)) {
            return false;
        }
        final String name = ref.getConstantDesc(natc.getNameIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        final String descriptor = ref.getConstantDesc(natc.getDescriptorIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        if (descriptor == null || name == null) {
            return false;
        }
        final String[] descArray = ClassFiles.getFromMethodDescriptor(descriptor);
        return !(name.startsWith("<") && (!name.equals("<init>") || !descArray[0].equals(void.class.getCanonicalName())));
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
