package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.AttributeReader;
import data.ClassFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The RecordAttribute attribute.
 */
@AttributeName("Record")
public class RecordAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final RecordComponentInfo[] components;

    private RecordAttribute(final int attributeNameIndex, final RecordComponentInfo[] components) {
        this.attributeNameIndex = attributeNameIndex;
        this.components = components;
    }

    public static RecordAttribute read(final int ani, final DataInputStream in, final ClassFile ref) throws IOException {
        in.readInt();    // Ignore

        final RecordComponentInfo[] components = new RecordComponentInfo[in.readUnsignedShort()];
        for (int i = 0; i < components.length; i++) {
            components[i] = RecordComponentInfo.read(in, ref);
        }

        return new RecordAttribute(ani, components);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {

        int componentSum = 2;

        for (final RecordComponentInfo component : this.components) {
            componentSum += 6;
            for (final AttributeDesc attribute : component.attributes) {
                componentSum += attribute.getDataLength();
            }
        }

        return componentSum;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.components.length);

        for (final RecordComponentInfo component : this.components) {
            component.write(out);
        }
    }

    public static record RecordComponentInfo(int nameIndex, int descriptorIndex, AttributeDesc[] attributes) {

        public static RecordComponentInfo read(final DataInputStream in, final ClassFile ref) throws IOException {
            final int nameIndex = in.readUnsignedShort();
            final int descriptorIndex = in.readUnsignedShort();
            final int attributesCount = in.readUnsignedShort();

            final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
            for (int j = 0; j < attributes.length; j++) {
                attributes[j] = AttributeReader.read(in, ref);
            }

            return new RecordComponentInfo(nameIndex, descriptorIndex, attributes);
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.nameIndex);
            out.writeShort(this.descriptorIndex);
            out.writeShort(this.attributes.length);

            for (final AttributeDesc attribute : this.attributes) {
                attribute.write(out);
            }
        }
    }
}
