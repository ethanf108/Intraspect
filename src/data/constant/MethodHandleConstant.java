package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class MethodHandleConstant implements ConstantDesc {

    private final byte kind;

    private final short referenceIndex;

    public MethodHandleConstant(byte kind, short ref) {
        this.kind = kind;
        this.referenceIndex = ref;
    }

    public byte getKind() {
        return kind;
    }

    public short getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return switch (kind) {
            case 1,2,3,4 ->
                ref.getConstandDesc(this.referenceIndex) instanceof FieldRefConstant;
            case 5, 8 ->
                ref.getConstandDesc(this.referenceIndex) instanceof MethodRefConstant;
            case 6, 7 ->
                ref.getConstandDesc(this.referenceIndex) instanceof MethodRefConstant
                || (ref.getMajorVersion().getMajorVersion() < 52 ? ref.getConstandDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant : false);
            case 9 ->
                ref.getConstandDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant;
            default ->
                false;
        };
    }
}
