package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class ChopFrame extends StackMapFrame {

    private short offsetDelta;

    public ChopFrame(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(InputStream in) throws IOException {
        offsetDelta = readShort(in);
        return this;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
