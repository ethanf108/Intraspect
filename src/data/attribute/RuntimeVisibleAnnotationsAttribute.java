package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import static util.Util.*;

// For convenience - Will likely move this into its own class file for use elsewhere
interface AttributeSubtype {
    int getDataLength();

    void write(final OutputStream out) throws IOException;
}

@AttributeName("RuntimeVisibleAnnotations")
public class RuntimeVisibleAnnotationsAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final Annotation[] annotations;
    private RuntimeVisibleAnnotationsAttribute(final short attributeNameIndex, final Annotation[] annotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.annotations = annotations;
    }

    public static RuntimeVisibleAnnotationsAttribute read(final short ani, final InputStream in) throws IOException {
        readInt(in);    // Ignore!

        final short numAnnotations = readShort(in);
        final Annotation[] annotations = new Annotation[numAnnotations];

        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = Annotation.read(in);
        }

        return new RuntimeVisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2 + Arrays.stream(annotations).mapToInt(AttributeSubtype::getDataLength).sum();
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, attributeNameIndex);
        writeInt(out, getDataLength());
        writeShort(out, (short) annotations.length);

        for (final Annotation annotation : annotations) {
            annotation.write(out);
        }
    }

    private static record Annotation(short typeIndex, ElementValuePair[] elementValuePairs) implements AttributeSubtype {

        public static Annotation read(final InputStream in) throws IOException {

            final short typeIndex = readShort(in);

            final ElementValuePair[] elementValuePairs = new ElementValuePair[readShort(in)];

            for (int i = 0; i < elementValuePairs.length; i++) {
                elementValuePairs[i] = ElementValuePair.read(in);
            }

            return new Annotation(typeIndex, elementValuePairs);
        }

        @Override
        public int getDataLength() {
            return 2 + Arrays.stream(elementValuePairs).mapToInt(AttributeSubtype::getDataLength).sum();
        }

        @Override
        public void write(final OutputStream out) throws IOException {
            writeShort(out, typeIndex);
            for (final ElementValuePair elementValuePair : elementValuePairs) {
                elementValuePair.write(out);
            }
        }

        private static record ElementValuePair(short elementNameIndex, ElementValue value) implements AttributeSubtype {


            public static ElementValuePair read(final InputStream in) throws IOException {

                final short elementNameIndex = readShort(in);
                final ElementValue elementValue = ElementValue.read(in);

                return new ElementValuePair(elementNameIndex, elementValue);
            }

            @Override
            public int getDataLength() {
                return 2 + value.getDataLength();
            }

            @Override
            public void write(final OutputStream out) throws IOException {
                writeShort(out, elementNameIndex);
                value.write(out);
            }
        }

        private static record ElementValue(byte tag, ElementValueUnion value) implements AttributeSubtype {

            public static ElementValue read(final InputStream in) throws IOException {
                final byte tag = in.readNBytes(1)[0];

                final ElementValueUnion elementValueUnion = ElementValueUnion.read(in);

                return new ElementValue(tag, elementValueUnion);
            }

            @Override
            public int getDataLength() {
                return 1 + value.getDataLength();
            }

            @Override
            public void write(final OutputStream out) throws IOException {
                out.write(new byte[]{tag});
                value.write(out);
            }

            private static record ElementValueUnion(short constantValueIndex, EnumConstantValue enumConstantValue,
                                                    short classInfoIndex, Annotation annotationValue,
                                                    ArrayValue arrayValue) implements AttributeSubtype {

                public static ElementValueUnion read(final InputStream in) throws IOException {

                    final short constantValueIndex = readShort(in);

                    final EnumConstantValue enumConstantValue = EnumConstantValue.read(in);

                    final short classInfoIndex = readShort(in);

                    final Annotation annotationValue = Annotation.read(in);

                    final ArrayValue arrayValue = ArrayValue.read(in);

                    return new ElementValueUnion(constantValueIndex, enumConstantValue, classInfoIndex, annotationValue, arrayValue);
                }

                @Override
                public int getDataLength() {
                    return 4 + enumConstantValue.getDataLength() +
                            annotationValue.getDataLength() +
                            arrayValue.getDataLength();
                }

                @Override
                public void write(final OutputStream out) throws IOException {
                    writeShort(out, constantValueIndex);
                    enumConstantValue.write(out);
                    writeShort(out, classInfoIndex);
                    annotationValue.write(out);
                    arrayValue.write(out);
                }

                private static record EnumConstantValue(short typeNameIndex,
                                                        short constantNameIndex) implements AttributeSubtype {

                    public static EnumConstantValue read(final InputStream in) throws IOException {
                        return new EnumConstantValue(readShort(in), readShort(in));
                    }

                    @Override
                    public int getDataLength() {
                        return 4;
                    }

                    @Override
                    public void write(OutputStream out) throws IOException {
                        writeShort(out, typeNameIndex);
                        writeShort(out, constantNameIndex);
                    }
                }

                private static record ArrayValue(short numValues, ElementValue[] values) implements AttributeSubtype {

                    public static ArrayValue read(final InputStream in) throws IOException {

                        final short numValues = readShort(in);

                        final ElementValue[] values = new ElementValue[numValues];

                        for (int i = 0; i < values.length; i++) {
                            values[i] = ElementValue.read(in);
                        }

                        return new ArrayValue(numValues, values);
                    }

                    @Override
                    public int getDataLength() {
                        return 2 + Arrays.stream(values).mapToInt(AttributeSubtype::getDataLength).sum();
                    }

                    @Override
                    public void write(OutputStream out) throws IOException {
                        writeShort(out, numValues);

                        for (final ElementValue value : values) {
                            value.write(out);
                        }
                    }
                }
            }
        }
    }
}