package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class ModuleConstant implements ConstantDesc {

    private final int moduleNameIndex;

    public ModuleConstant(int ref) {
        this.moduleNameIndex = ref;
    }

    @Override
    public int getTag() {
        return 19;
    }

    public int getModuleNameIndex() {
        return this.moduleNameIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.moduleNameIndex) instanceof UTF8Constant;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.moduleNameIndex);
    }
}
