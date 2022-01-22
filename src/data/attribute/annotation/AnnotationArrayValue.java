package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationArrayValue extends ElementValue {

    private ElementValue[] values;

    public AnnotationArrayValue(int tag) {
        super(tag);
    }

    public ElementValue[] getValues() {
        return this.values;
    }

    public void setValues(final ElementValue[] values) {
        this.values = values;
    }

    @Override
    ElementValue readInternal(final DataInputStream in) throws IOException {
        this.values = new ElementValue[in.readUnsignedShort()];
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = ElementValue.read(in);
        }
        return this;
    }

    @Override
    public int getDataLength() {
        int length = 3;
        for (ElementValue elementValue : this.values) {
            length += elementValue.getDataLength();
        }
        return length;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.values.length);
        for (final ElementValue elementValue : this.values) {
            elementValue.write(out);
        }
    }
}
