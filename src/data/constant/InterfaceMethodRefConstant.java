package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class InterfaceMethodRefConstant implements ConstantDesc {

    private final short classIndex;
    private final short nameAndTypeIndex;

    public InterfaceMethodRefConstant(short classIndex, short nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public byte getTag() {
        return 11;
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

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.classIndex);
        writeShort(out, this.nameAndTypeIndex);
    }
}
