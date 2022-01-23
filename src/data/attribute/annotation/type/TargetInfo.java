package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public sealed abstract class TargetInfo permits TypeParameterTarget, SuperTypeTarget,
        TypeParameterBoundTarget, EmptyTarget, FormalParameterTarget, ThrowsTarget, LocalVarTarget,
        CatchTarget, OffsetTarget, TypeArgumentTarget {

    protected final int targetType;

    protected TargetInfo(final int targetType) {
        this.targetType = targetType;
    }

    public static TargetInfo read(final DataInputStream in) throws IOException {

        final int targetType = in.readUnsignedByte();

        return switch (targetType) {

            case 0x0, 0x1 -> new TypeParameterTarget(targetType).readInternal(in);

            case 0x10 -> new SuperTypeTarget(targetType).readInternal(in);

            case 0x11, 0x12 -> new TypeParameterBoundTarget(targetType).readInternal(in);

            case 0x13, 0x14, 0x15 -> new EmptyTarget(targetType).readInternal(in);

            case 0x16 -> new FormalParameterTarget(targetType).readInternal(in);

            case 0x17 -> new ThrowsTarget(targetType).readInternal(in);

            case 0x40, 0x41 -> new LocalVarTarget(targetType).readInternal(in);

            case 0x42 -> new CatchTarget(targetType).readInternal(in);

            case 0x43, 0x44, 0x45, 0x46 -> new OffsetTarget(targetType).readInternal(in);

            case 0x47, 0x48, 0x49, 0x4A, 0x4B -> new TypeArgumentTarget(targetType).readInternal(in);

            default -> throw new IllegalArgumentException("Invalid target type: " + targetType);
        };
    }

    public abstract void write(final DataOutputStream out) throws IOException;

    public abstract int getDataLength();

    protected abstract TargetInfo readInternal(final DataInputStream in) throws IOException;

    public int getTargetType() {
        return this.targetType;
    }
}
