package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A placeholder Constant meant to act as a placeholder immediately after a
 * "Wide" Constant, such as a LongConstant or a DoubleConstant
 *
 * @author ethan
 */
public class EmptyWideConstant implements ConstantDesc {

    public EmptyWideConstant() {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        int index = -1;
        for (int i = 1; i < ref.getConstants().length + 1; i++) {
            if (ref.getConstants()[i] == this) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalStateException("Constant not in ClassFile");
        }
        return ref.getConstantDesc(index).isWide();
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
    }

}
