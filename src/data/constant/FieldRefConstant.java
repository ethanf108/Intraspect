package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;

public class FieldRefConstant implements ConstantDesc {

    private final int classIndex;
    private final int nameAndTypeIndex;

    public FieldRefConstant(final int classIndex, final int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 9;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO validate field??
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant && ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
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
