package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class MethodTypeConstant implements ConstantDesc {

    private final short descriptorIndex;

    public MethodTypeConstant(short ref) {
        this.descriptorIndex = ref;
    }

    public short getMethodDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.descriptorIndex) instanceof UTF8Constant; //TODO check if valid method descriptor??
    }
}
