package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The SourceDebugExtension attribute.
 */
@AttributeName("SourceDebugExtension")
public final class SourceDebugExtensionAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final byte[] debugExtension;

    public SourceDebugExtensionAttribute(final int attributeNameIndex, final byte[] debugExtension) {
        this.attributeNameIndex = attributeNameIndex;
        this.debugExtension = debugExtension;
    }

    public static SourceDebugExtensionAttribute read(final int ani, final DataInputStream in) throws IOException {
        final byte[] data = new byte[in.readInt()];
        in.read(data);
        return new SourceDebugExtensionAttribute(ani, data);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.debugExtension.length;
    }

    public byte[] getDebugExtension() {
        return this.debugExtension;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.debugExtension.length);
        out.write(this.debugExtension);
    }
}
