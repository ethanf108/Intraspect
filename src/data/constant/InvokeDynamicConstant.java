package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

public class InvokeDynamicConstant implements ConstantDesc {

    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public InvokeDynamicConstant(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
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
    public boolean isValid(ClassFile ref) {
        //TODO Check bootstrap method table
        return ref.getConstandDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.bootstrapMethodAttributeIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
