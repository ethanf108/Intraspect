package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

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
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.descriptorIndex);
    }
}
