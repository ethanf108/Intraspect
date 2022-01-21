package data.constant;

import data.ClassFile;
import data.ClassFiles;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

public class MethodRefConstant implements ConstantDesc {

    private final int classIndex;
    private final int nameAndTypeIndex;

    public MethodRefConstant(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 10;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        if (!(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc)) {
            return false;
        }
        Optional<Class<?>> refClass = cc.getReferencedClass(ref);
        if (refClass.isPresent() && refClass.orElseThrow().isInterface()) {
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
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.classIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
