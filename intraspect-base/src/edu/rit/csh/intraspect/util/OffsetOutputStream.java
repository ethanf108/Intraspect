package edu.rit.csh.intraspect.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OffsetOutputStream extends DataOutputStream {


    private long total;
    private long counter;

    public OffsetOutputStream(OutputStream out) {
        super(null);
        this.out = this.new Sub(out);
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

    private final class Sub extends OutputStream {

        private final OutputStream downstream;

        public Sub(OutputStream downstream) {
            this.downstream = downstream;
        }

        @Override
        public void write(int b) throws IOException {
            this.downstream.write(b);
            OffsetOutputStream.this.total++;
            OffsetOutputStream.this.counter++;
        }
    }
}
