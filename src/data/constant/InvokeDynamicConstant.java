package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class InvokeDynamicConstant implements ConstantDesc {

    private final short bootstrapMethodAttributeIndex;
    private final short nameAndTypeIndex;

    public InvokeDynamicConstant(short bootstrapMethodAttributeIndex, short nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
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

}
