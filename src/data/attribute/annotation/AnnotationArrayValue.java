package data.attribute.annotation;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public final class AnnotationArrayValue extends ElementValue {

    private ElementValue[] values;

    public AnnotationArrayValue(byte tag) {
        super(tag);
    }

    public ElementValue[] getValues() {
        return values;
    }

    public void setValues(ElementValue[] values) {
        this.values = values;
    }

    @Override
    ElementValue readInternal(DataInputStream in) throws IOException {
        final short numValues = readShort(in);
        this.values = new ElementValue[numValues];
        for (int i = 0; i < numValues; i++) {
            this.values[i] = ElementValue.read(in);
        }
        return this;
    }

    @Override
    public int getDataLength() {
        int length = 2;
        for (ElementValue elementValue : this.values) {
            length += elementValue.getDataLength();
        }
        return length;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, (short) this.values.length);
        for (ElementValue elementValue : this.values) {
            elementValue.write(out);
        }
    }

}
