package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class NameAndTypeConstant implements ConstantDesc {

    private final short nameIndex;
    private final short descriptorIndex;

    public NameAndTypeConstant(short name, short descriptor) {
        this.nameIndex = name;
        this.descriptorIndex = descriptor;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.nameIndex) instanceof UTF8Constant && ref.getConstandDesc(this.descriptorIndex) instanceof UTF8Constant;
    }

}
