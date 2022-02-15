package edu.rit.csh.intraspect.data.attribute.annotation;

import edu.rit.csh.intraspect.data.constant.*;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationConstantValue extends ElementValue {

    @ConstantPoolIndex({
            IntegerConstant.class,
            FloatConstant.class,
            DoubleConstant.class,
            LongConstant.class,
            UTF8Constant.class
    })
    private int constValueIndex;

    public AnnotationConstantValue(final int tag) {
        super(tag);
    }

    public void setTag(final int tag) {
        this.tag = tag;
    }

    public int getConstValueIndex() {
        return this.constValueIndex;
    }

    public void setConstValueIndex(final int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    @Override
    ElementValue readInternal(final DataInputStream in) throws IOException {
        this.constValueIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.constValueIndex);
    }

}
