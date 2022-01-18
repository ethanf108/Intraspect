package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.AttributeReader;
import data.ClassFile;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.*;

@AttributeName("Record")
public class RecordAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final RecordComponentInfo[] components;

    private RecordAttribute(final int attributeNameIndex, final RecordComponentInfo[] components) {
        this.attributeNameIndex = attributeNameIndex;
        this.components = components;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {

        int componentSum = 2;

        for (final RecordComponentInfo component : components) {
            componentSum += 6;
            for (final AttributeDesc attribute : component.attributes) {
                componentSum += attribute.getDataLength();
            }
        }

        return componentSum;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, (short) this.components.length);

        for (final RecordComponentInfo component : this.components) {
            writeShort(out, component.nameIndex);
            writeShort(out, component.descriptorIndex);
            writeShort(out, (short) component.attributes.length);

            for (final AttributeDesc attribute : component.attributes) {
                attribute.write(out);
            }
        }
    }

    public static RecordAttribute read(final int ani, final DataInputStream in, final ClassFile ref) throws IOException {
        in.readInt();    // Ignore

        final int componentsCount = in.readUnsignedShort();

        final RecordComponentInfo[] components = new RecordComponentInfo[componentsCount];
        for (int i = 0; i < components.length; i++) {
            final int nameIndex = in.readUnsignedShort();
            final int descriptorIndex = in.readUnsignedShort();
            final int attributesCount = in.readUnsignedShort();

            final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
            for (int j = 0; j < attributes.length; j++) {
                attributes[j] = AttributeReader.read(in, ref);
            }

            components[i] = new RecordComponentInfo(nameIndex, descriptorIndex, attributes);
        }

        return new RecordAttribute(ani, components);
    }

    private static record RecordComponentInfo(int nameIndex, int descriptorIndex, AttributeDesc[] attributes) {

    }
}
