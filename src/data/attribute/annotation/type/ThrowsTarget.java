package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ThrowsTarget extends TargetInfo {

    private int throwsTypeIndex;

    public ThrowsTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
        out.writeShort(this.throwsTypeIndex);
    }

    public int getThrowsTypeIndex() {
        return this.throwsTypeIndex;
    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.throwsTypeIndex = in.readUnsignedShort();

        return this;
    }
}
