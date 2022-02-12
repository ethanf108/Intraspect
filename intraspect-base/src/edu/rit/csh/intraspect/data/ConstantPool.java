package edu.rit.csh.intraspect.data;

import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ConstantPool {

    /**
     * 0-indexed list
     */
    private final List<ConstantDesc> pool;

    public ConstantPool() {
        this.pool = new ArrayList<>();
    }

    void addInternal(ConstantDesc cd) {
        this.pool.add(cd);
    }

    ConstantDesc get0Indexed(int index) {
        return this.pool.get(index);
    }

    public ConstantDesc get(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.get0Indexed(index - 1);
    }

    public int getNumConstants() {
        return this.pool.size();
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.getNumConstants() + 1);
        for (ConstantDesc cd : this.pool) {
            cd.write(out);
        }
    }
}
