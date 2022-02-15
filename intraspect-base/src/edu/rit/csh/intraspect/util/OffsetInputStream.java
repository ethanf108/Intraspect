package edu.rit.csh.intraspect.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OffsetInputStream extends DataInputStream {


    private long counter = 0;
    private long total = 0;

    public OffsetInputStream(InputStream upstream) {
        super(null);
        this.in = this.new Sub(upstream);
    }

    public long getCounter() {
        return this.counter;
    }

    public long getTotal() {
        return this.total;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    private final class Sub extends InputStream {

        private final InputStream upstream;

        public Sub(InputStream in) {
            this.upstream = in;
        }

        @Override
        public int read() throws IOException {
            int ret = this.upstream.read();
            if (ret != -1) {
                OffsetInputStream.this.counter++;
                OffsetInputStream.this.total++;
            }
            return ret;
        }
    }
}
