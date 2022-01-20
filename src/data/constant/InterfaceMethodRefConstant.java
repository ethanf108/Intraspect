package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

public class InterfaceMethodRefConstant implements ConstantDesc {

    private final int classIndex;
    private final int nameAndTypeIndex;

    public InterfaceMethodRefConstant(int classIndex, int nameAndTypeIndex) {
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
    public boolean isValid(ClassFile ref) {
        if (!(ref.getConstandDesc(this.classIndex) instanceof ClassConstant cc)) {
            return false;
        }
        Optional<Class<?>> refClass = cc.getReferencedClass(ref);
        if (refClass.isPresent() && !refClass.orElseThrow().isInterface()) {
            return false;
        }
        return ref.getConstandDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
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
