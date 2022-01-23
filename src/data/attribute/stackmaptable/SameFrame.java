package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The SameFrame stack map frame type.
 */
public final class SameFrame extends StackMapFrame {

    public SameFrame(final int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
