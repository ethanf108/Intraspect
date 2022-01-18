package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

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
        out.write(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
