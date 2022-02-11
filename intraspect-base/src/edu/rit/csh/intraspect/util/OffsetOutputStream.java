package edu.rit.csh.intraspect.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OffsetOutputStream extends DataOutputStream {

    private final OutputStream downstream;

    private long total;
    private long counter;

    public OffsetOutputStream(OutputStream out) {
        super(null);
        this.downstream = out;
        this.out = this;
    }

    @Override
    public synchronized void write(int b) throws IOException {
        this.downstream.write(b);
        this.total++;
        this.counter++;
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.downstream.write(b);
        this.total += b.length;
        this.counter += b.length;
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        this.downstream.write(b, off, len);
        this.total += len;
        this.counter += len;
    }

    @Override
    public void flush() throws IOException {
        this.downstream.flush();
    }

    public long getTotal() {
        return this.total;
    }

    public long getCounter() {
        return this.counter;
    }

    public void resetCount() {
        this.counter = 0;
    }
}
