package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class OffsetTarget extends TargetInfo {

    private int offset;

    public int getOffset() {
        return this.offset;
    }

    public OffsetTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {

        out.writeByte(this.targetType);

        out.writeShort(this.offset);

    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.offset = in.readUnsignedShort();

        return this;
    }
}
