package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class CatchTarget extends TargetInfo {

    private int exceptionTableIndex;

    public int getExceptionTableIndex() {
        return this.exceptionTableIndex;
    }

    public CatchTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);

        out.writeShort(this.exceptionTableIndex);

    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.exceptionTableIndex = in.readUnsignedShort();

        return this;
    }
}
