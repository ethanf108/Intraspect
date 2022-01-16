package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.IOException;
import java.io.OutputStream;

import static util.Util.writeShort;

public class DynamicConstant implements ConstantDesc {

    private final short bootstrapMethodAttributeIndex;
    private final short nameAndTypeIndex;

    public DynamicConstant(short bootstrapMethodAttributeIndex, short nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public byte getTag() {
        return 17;
    }

    public short getBootstrapMethodAttributeIndex() {
        return bootstrapMethodAttributeIndex;
    }

    public short getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check bootstrap method table
        return ref.getConstandDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.bootstrapMethodAttributeIndex);
        writeShort(out, this.nameAndTypeIndex);
    }

}
