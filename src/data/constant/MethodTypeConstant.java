package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodTypeConstant implements ConstantDesc {

    private final int descriptorIndex;

    public MethodTypeConstant(int ref) {
        this.descriptorIndex = ref;
    }

    @Override
    public int getTag() {
        return 16;
    }

    public int getMethodDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.descriptorIndex) instanceof UTF8Constant; //TODO check if valid method descriptor??
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.descriptorIndex);
    }
}
