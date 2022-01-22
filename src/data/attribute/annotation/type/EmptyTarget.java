package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class EmptyTarget extends TargetInfo {
    public EmptyTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
    }

    @Override
    public int getDataLength() {
        return 1;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {
        return this;
    }
}
