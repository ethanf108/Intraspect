package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class NameAndTypeConstant implements ConstantDesc {

    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeConstant(int name, int descriptor) {
        this.nameIndex = name;
        this.descriptorIndex = descriptor;
    }

    @Override
    public int getTag() {
        return 12;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.nameIndex) instanceof UTF8Constant && ref.getConstandDesc(this.descriptorIndex) instanceof UTF8Constant;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.nameIndex);
        writeShort(out, this.descriptorIndex);
    }

}
