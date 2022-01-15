package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class NameAndTypeConstant implements ConstantDesc {

    private final short nameIndex;
    private final short descriptorIndex;

    public NameAndTypeConstant(short name, short descriptor) {
        this.nameIndex = name;
        this.descriptorIndex = descriptor;
    }

    @Override
    public byte getTag() {
        return 12;
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

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.nameIndex);
        writeShort(out, this.descriptorIndex);
    }

}
