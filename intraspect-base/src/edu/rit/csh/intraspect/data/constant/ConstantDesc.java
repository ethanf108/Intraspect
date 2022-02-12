package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * An interface representing a constant descriptor.
 */
public sealed interface ConstantDesc permits
        ClassConstant, DoubleConstant, DynamicConstant, EmptyWideConstant,
        FieldRefConstant, FloatConstant, IntegerConstant, InterfaceMethodRefConstant,
        InvokeDynamicConstant, LongConstant, MethodHandleConstant, MethodRefConstant,
        MethodTypeConstant, ModuleConstant, NameAndTypeConstant, PackageConstant,
        StringConstant, UTF8Constant {

    /**
     * Reads a constant from the given data input stream.
     *
     * @param in the data input stream to read from
     * @return the constant read from the data input stream
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if the constant is invalid
     */
    static ConstantDesc readConstant(final DataInputStream in) throws IOException {
        final int tag = in.readUnsignedByte();
        return switch (tag) {
            case 1 -> UTF8Constant.read(in);
            case 3 -> IntegerConstant.read(in);
            case 4 -> FloatConstant.read(in);
            case 5 -> LongConstant.read(in);
            case 6 -> DoubleConstant.read(in);
            case 7 -> new ClassConstant(in.readUnsignedShort());
            case 8 -> new StringConstant(in.readUnsignedShort());
            case 9 -> new FieldRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 10 -> new MethodRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 11 -> new InterfaceMethodRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 12 -> new NameAndTypeConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 15 -> new MethodHandleConstant(in.readUnsignedByte(), in.readUnsignedShort());
            case 16 -> new MethodTypeConstant(in.readUnsignedShort());
            case 17 -> new DynamicConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 18 -> new InvokeDynamicConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 19 -> new ModuleConstant(in.readUnsignedShort());
            case 20 -> new PackageConstant(in.readUnsignedShort());
            default -> throw new IllegalArgumentException("Invalid Tag: " + tag);
        };
    }

    int getTag();

    boolean isValid(final ClassFile ref);

    boolean isWide();

    /**
     * Writes the constant descriptor to the given output stream.
     *
     * @param out the output stream to write to
     * @throws IOException if an I/O error occurs
     */
    void write(DataOutputStream out) throws IOException;

    String getName();

    String getInfo();
}
