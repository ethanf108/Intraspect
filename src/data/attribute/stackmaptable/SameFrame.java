package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class SameFrame extends StackMapFrame {

    public SameFrame(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(InputStream in) throws IOException {
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
