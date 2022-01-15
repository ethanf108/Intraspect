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

@AttributeName("NestHost")
public class NestHostAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short hostClassIndex;

    public NestHostAttribute(short attributeNameIndex, short hostClassIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.hostClassIndex = hostClassIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public short getHostClassIndex() {
        return hostClassIndex;
    }

    public static NestHostAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        if (length != 2) {
            throw new IllegalArgumentException("Nest Host Attribute Length must be 2");
        }
        final short hostClass = readShort(in);
        return new NestHostAttribute(ani, hostClass);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, 2);
        writeShort(out, this.hostClassIndex);
    }
}
