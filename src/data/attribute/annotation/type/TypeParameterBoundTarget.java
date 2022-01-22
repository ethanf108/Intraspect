package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class TypeParameterBoundTarget extends TargetInfo {

    private int typeParameterIndex;
    private int boundIndex;

    public TypeParameterBoundTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
        out.writeByte(this.typeParameterIndex);
        out.writeByte(this.boundIndex);
    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        this.typeParameterIndex = in.readUnsignedByte();
        this.boundIndex = in.readUnsignedByte();

        return this;
    }
}
