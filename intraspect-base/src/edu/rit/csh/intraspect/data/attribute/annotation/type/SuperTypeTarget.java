package edu.rit.csh.intraspect.data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class SuperTypeTarget extends TargetInfo {

    private int superTypeIndex;

    public SuperTypeTarget(final int targetType) {
        super(targetType);
    }

    public int getSuperTypeIndex() {
        return this.superTypeIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
        out.writeShort(this.superTypeIndex);
    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.superTypeIndex = in.readUnsignedShort();

        return this;
    }
}
