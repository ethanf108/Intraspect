package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;

public class InvokeDynamicConstant implements ConstantDesc {

    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public InvokeDynamicConstant(final int bootstrapMethodAttributeIndex, final int nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 18;
    }

    public int getBootstrapMethodAttributeIndex() {
        return bootstrapMethodAttributeIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check bootstrap method table
        return ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.bootstrapMethodAttributeIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
