package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class PackageConstant implements ConstantDesc {

    private final short utf8Index;

    public PackageConstant(short ref) {
        this.utf8Index = ref;
    }

    public short getPackageNameIndex() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant; //TODO check if valid package name?
    }
}
