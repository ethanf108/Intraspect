package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Acts as a superclass for all verification type info structures.
 */
public abstract sealed class VerificationTypeInfo permits TopVariableInfo, IntegerVariableInfo, FloatVariableInfo, LongVariableInfo, DoubleVariableInfo, NullVariableInfo, UninitializedThisVariableInfo, ObjectVariableInfo, UninitializedVariableInfo {

    protected final int tag;

    protected VerificationTypeInfo(final int tag) {
        this.tag = tag;
    }

    public static VerificationTypeInfo read(final DataInputStream in) throws IOException {
        final int tag = in.readUnsignedByte();
        return switch (tag) {
            case 0 -> new TopVariableInfo(tag).readInternal(in);
            case 1 -> new IntegerVariableInfo(tag).readInternal(in);
            case 2 -> new FloatVariableInfo(tag).readInternal(in);
            case 5 -> new NullVariableInfo(tag).readInternal(in);
            case 6 -> new UninitializedThisVariableInfo(tag).readInternal(in);
            case 7 -> new ObjectVariableInfo(tag).readInternal(in);
            case 8 -> new UninitializedVariableInfo(tag).readInternal(in);

            // Two locations
            case 4 -> new LongVariableInfo(tag).readInternal(in);
            case 3 -> new DoubleVariableInfo(tag).readInternal(in);

            // Invalid
            default -> throw new IllegalArgumentException("Invalid tag for Verification Type Info");
        };
    }

    abstract VerificationTypeInfo readInternal(final DataInputStream in) throws IOException;

    public abstract void write(final DataOutputStream out) throws IOException;

    public abstract int getDataLength();
}
