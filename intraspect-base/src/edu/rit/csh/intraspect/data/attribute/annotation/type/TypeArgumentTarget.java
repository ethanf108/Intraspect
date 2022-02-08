package edu.rit.csh.intraspect.data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class TypeArgumentTarget extends TargetInfo {

    private int offset;
    private int typeArgumentIndex;

    public TypeArgumentTarget(final int targetType) {
        super(targetType);
    }

    public int getOffset() {
        return this.offset;
    }

    public int getTypeArgumentIndex() {
        return this.typeArgumentIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);

        out.writeShort(this.offset);

        out.writeByte(this.typeArgumentIndex);
    }

    @Override
    public int getDataLength() {
        return 4;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.offset = in.readUnsignedShort();

        this.typeArgumentIndex = in.readUnsignedByte();

        return this;
    }
}
