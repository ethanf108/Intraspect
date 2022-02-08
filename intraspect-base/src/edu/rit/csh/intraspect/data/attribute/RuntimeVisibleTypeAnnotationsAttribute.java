package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.type.TypeAnnotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The RuntimeVisibleTypeAnnotations attribute.
 */
@AttributeName("RuntimeVisibleTypeAnnotations")
public final class RuntimeVisibleTypeAnnotationsAttribute extends RuntimeTypeAnnotations {

    public RuntimeVisibleTypeAnnotationsAttribute(final int attributeNameIndex, final TypeAnnotation[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeVisibleTypeAnnotationsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final TypeAnnotation[] annotations = new TypeAnnotation[in.readUnsignedShort()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = TypeAnnotation.read(in);
        }

        return new RuntimeVisibleTypeAnnotationsAttribute(ani, annotations);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.annotations.length);

        for (final TypeAnnotation annotation : this.annotations) {
            annotation.write(out);
        }
    }

    @Override
    public boolean isRuntimeVisible() {
        return true;
    }
}
