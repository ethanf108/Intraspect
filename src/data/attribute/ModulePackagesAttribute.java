package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("ModulePackages")
public class ModulePackagesAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short[] packageIndex;

    public ModulePackagesAttribute(short attributeNameIndex, short[] packageIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.packageIndex = packageIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public short[] getPackageIndex() {
        return packageIndex;
    }

    public static ModulePackagesAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        final short packageCount = readShort(in);
        final short[] packageIndex = new short[packageCount];
        for (int i = 0; i < packageCount; i++) {
            packageIndex[i] = readShort(in);
        }
        return new ModulePackagesAttribute(ani, packageIndex);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.packageIndex.length * 2 + 2);
        writeShort(out, (short) this.packageIndex.length);
        for (short s : this.packageIndex) {
            writeShort(out, s);
        }
    }
}
