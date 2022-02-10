package edu.rit.csh.intraspect.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OffsetInputStream extends DataInputStream {

    private final InputStream upstream;

    private long counter = 0;
    private long total = 0;

    public OffsetInputStream(InputStream upstream) {
        super(null);
        this.in = this;
        this.upstream = upstream;
    }

    @Override
    public int read() throws IOException {
        int ret = this.upstream.read();
        if (ret != -1) {
            this.counter++;
            this.total++;
        }
        return ret;
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        byte[] ret = this.upstream.readNBytes(len);
        this.counter += ret.length;
        this.total += ret.length;
        return ret;
    }

    @Override
    public long skip(long n) throws IOException {
        long val = this.upstream.skip(n);
        this.counter += val;
        this.total += val;
        return val;
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
}
