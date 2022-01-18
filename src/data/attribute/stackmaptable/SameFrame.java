package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

public final class SameFrame extends StackMapFrame {

    public SameFrame(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        out.write(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
