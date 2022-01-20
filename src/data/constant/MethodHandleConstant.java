package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodHandleConstant implements ConstantDesc {

    private final int kind;

    private final int referenceIndex;

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
        String methodName = null;
        final boolean first = switch (kind) {
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
        return first && switch (kind) {
            case 5,6,7,9:
                if (ref.getConstandDesc(this.referenceIndex) instanceof InterfaceMethodRefConstant imrc) {
                    if (ref.getConstandDesc(imrc.getNameAndTypeIndex()) instanceof NameAndTypeConstant natc
                            && ref.getConstandDesc(natc.getNameIndex()) instanceof UTF8Constant u) {
                        methodName = u.getValue();
                    } else {
                        yield false;
                    }
                }
            case 8:
                if (ref.getConstandDesc(this.referenceIndex) instanceof MethodRefConstant mrc) {
                    if (ref.getConstandDesc(mrc.getNameAndTypeIndex()) instanceof NameAndTypeConstant natc
                            && ref.getConstandDesc(natc.getNameIndex()) instanceof UTF8Constant u) {
                        methodName = u.getValue();
                    } else {
                        yield false;
                    }
                }
                if (methodName == null) {
                    yield false;
                }
                if (kind == 8) {
                    yield methodName.equals("<init>");
                } else {
                    yield !methodName.equals("<init>") && !methodName.equals("<clinit>");
                }
            case 1,2,3,4:
                yield true;
            default:
                yield false;
        };
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeByte(this.kind);
        out.writeShort(this.referenceIndex);
    }
}
