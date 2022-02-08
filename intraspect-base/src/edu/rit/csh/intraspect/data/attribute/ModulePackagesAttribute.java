package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The ModulePackages attribute.
 */
@AttributeName("ModulePackages")
public class ModulePackagesAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] packageIndex;

    public ModulePackagesAttribute(final int attributeNameIndex, final int[] packageIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.packageIndex = packageIndex;
    }

    public static ModulePackagesAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Ignore

        final int[] packageIndex = new int[in.readUnsignedShort()];

        for (int i = 0; i < packageIndex.length; i++) {
            packageIndex[i] = in.readUnsignedShort();
        }

        return new ModulePackagesAttribute(ani, packageIndex);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int[] getPackageIndex() {
        return this.packageIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.packageIndex.length);
        for (final int s : this.packageIndex) {
            out.writeShort(s);
        }
    }

    @Override
    public int getDataLength() {
        return this.packageIndex.length * 2 + 2;
    }
}
