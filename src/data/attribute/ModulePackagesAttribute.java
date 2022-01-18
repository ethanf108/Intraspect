package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

@AttributeName("ModulePackages")
public class ModulePackagesAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] packageIndex;

    public ModulePackagesAttribute(int attributeNameIndex, int[] packageIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.packageIndex = packageIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int[] getPackageIndex() {
        return packageIndex;
    }

    public static ModulePackagesAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();    // Ignore
        final int packageCount = in.readUnsignedShort();
        final int[] packageIndex = new int[packageCount];
        for (int i = 0; i < packageCount; i++) {
            packageIndex[i] = in.readUnsignedShort();
        }
        return new ModulePackagesAttribute(ani, packageIndex);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, (short) this.packageIndex.length);
        for (short s : this.packageIndex) {
            writeShort(out, s);
        }
    }

    @Override
    public int getDataLength() {
        return this.packageIndex.length * 2 + 2;
    }
}
