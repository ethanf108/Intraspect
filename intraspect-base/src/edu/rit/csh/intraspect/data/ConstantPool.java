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

    void addInternal(final ConstantDesc cd) {
        this.pool.add(cd);
    }

    ConstantDesc get0Indexed(final int index) {
        return this.pool.get(index);
    }

    public ConstantDesc get(final int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return this.get0Indexed(index - 1);
    }

    void addResize(final int index, final ConstantDesc ncd) {
        Objects.requireNonNull(ncd);
        if (index < 1) {
            throw new IllegalArgumentException("Invalid insert index");
        }
        if (this.getNumConstants() >= 65535) {
            throw new IllegalStateException("Max number of constants reached");
        }
        for (int i = 1; i <= this.pool.size(); i++) {
            final ConstantDesc cd = this.get(i);
            for (final Field f : cd.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    if (f.isAnnotationPresent(ConstantPoolIndex.class) && f.getInt(cd) >= index) {
                        f.setInt(cd, f.getInt(cd) + 1);
                    }
                } catch (final IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        this.pool.add(index - 1, ncd);
    }

    void removeResize(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Invalid insert index");
        }
        if (this.getNumConstants() == 0) {
            throw new IllegalStateException("Min number of constants reached");
        }
        for (int i = 1; i <= this.pool.size(); i++) {
            final ConstantDesc cd = this.get(i);
            for (final Field f : cd.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    if (f.isAnnotationPresent(ConstantPoolIndex.class) && f.getInt(cd) >= index) {
                        f.setInt(cd, f.getInt(cd) - 1);
                    }
                } catch (final IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        this.pool.remove(index - 1);
    }

    public int getNumConstants() {
        return this.pool.size();
    }

    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.getNumConstants() + 1);
        for (final ConstantDesc cd : this.pool) {
            cd.write(out);
        }
    }
}
