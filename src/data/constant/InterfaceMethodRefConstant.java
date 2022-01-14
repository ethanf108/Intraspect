package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class InterfaceMethodRefConstant implements ConstantDesc {

    private final short classIndex;
    private final short nameAndTypeIndex;

    public InterfaceMethodRefConstant(short classIndex, short nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public short getClassIndex() {
        return classIndex;
    }

    public short getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO validate interface??
        return ref.getConstandDesc(this.classIndex) instanceof ClassConstant && ref.getConstandDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }
}
