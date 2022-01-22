package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class TypeParameterTarget extends TargetInfo {

    private int typeParameterIndex;

    public TypeParameterTarget(final int targetType) {
        super(targetType);
    }

    public int getTypeParameterIndex() {
        return this.typeParameterIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.targetType);
        out.writeByte(this.typeParameterIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {
        this.typeParameterIndex = in.readUnsignedByte();
        return this;
    }
}
