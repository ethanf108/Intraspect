package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

public class ClassConstant implements ConstantDesc {

    private final int utf8Index;

    public ClassConstant(final int utf8) {
        this.utf8Index = utf8;
    }

    public int getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public int getTag() {
        return 7;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.utf8Index) instanceof UTF8Constant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    public Optional<Class<?>> getReferencedClass(final ClassFile ref) {
        if (!(ref.getConstantDesc(this.utf8Index) instanceof UTF8Constant u)) {
            throw new IllegalStateException("Class Constant does not point ot UTF8 Constant");
        }
        try {
            return Optional.of(Class.forName(u.getValue().replaceAll("/", ".")));
        } catch (ClassNotFoundException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(getTag());
        out.writeShort(this.utf8Index);
    }
}
