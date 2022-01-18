package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class SameFrame extends StackMapFrame {

    public SameFrame(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
