package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodHandleConstant implements ConstantDesc {

    private int kind;

    private int referenceIndex;

    public MethodHandleConstant(int kind, int ref) {
        this.kind = kind;
        this.referenceIndex = ref;
    }

    @Override
    public int getTag() {
        return 15;
    }

    public int getKind() {
        return kind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return switch (kind) {
            case 1, 2, 3, 4 ->
                ref.getConstandDesc(this.referenceIndex) instanceof FieldRefConstant;
            case 5, 8 ->
                ref.getConstandDesc(this.referenceIndex) instanceof MethodRefConstant;
            case 6, 7 ->
                ref.getConstandDesc(this.referenceIndex) instanceof MethodRefConstant || (ref.getMajorVersion().getMajorVersion() < 52 && ref.getConstandDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant);
            case 9 ->
                ref.getConstandDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant;
            default ->
                false;
        };
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeByte(this.kind);
        out.writeShort(this.referenceIndex);
    }
}
