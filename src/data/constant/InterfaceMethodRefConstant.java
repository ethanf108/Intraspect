package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

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
        //TODO validate interface??
        return ref.getConstandDesc(this.classIndex) instanceof ClassConstant && ref.getConstandDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.classIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
