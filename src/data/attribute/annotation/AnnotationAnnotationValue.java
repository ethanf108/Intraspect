package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationAnnotationValue extends ElementValue {

    private AnnotationDesc annotationValue;

    public AnnotationAnnotationValue(int tag) {
        super(tag);
    }

    public AnnotationDesc getAnnotationValue() {
        return annotationValue;
    }

    public void setAnnotationValue(AnnotationDesc annotationValue) {
        this.annotationValue = annotationValue;
    }

    @Override
    ElementValue readInternal(DataInputStream in) throws IOException {
        this.annotationValue = AnnotationDesc.read(in);
        return this;
    }

    @Override
    public int getDataLength() {
        return 1 + this.annotationValue.getDataLength();
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        this.annotationValue.write(out);
    }

}
