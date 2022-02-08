package edu.rit.csh.intraspect.data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class FormalParameterTarget extends TargetInfo {

    private int formalParameterIndex;

    public FormalParameterTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
        out.writeByte(this.formalParameterIndex);
    }

    public int getFormalParameterIndex() {
        return this.formalParameterIndex;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.formalParameterIndex = in.readUnsignedByte();

        return this;
    }
}
