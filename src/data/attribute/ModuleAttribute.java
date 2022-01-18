package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("Module")
public class ModuleAttribute implements AttributeDesc {

    private final short attributeNameIndex;

    private final short moduleNameIndex;
    private final short moduleFlags;
    private final short moduleVersionIndex;
    private final RequiresEntry[] requires;
    private final ExportsEntry[] exports;
    private final OpensEntry[] opens;
    private final short[] usesIndex;
    private final ProvidesEntry[] provides;

    public ModuleAttribute(final short attributeNameIndex,
                           final short moduleNameIndex,
                           final short moduleFlags,
                           final short moduleVersionIndex,
                           final RequiresEntry[] requires,
                           final ExportsEntry[] exports,
                           final OpensEntry[] opens,
                           final short[] usesIndex,
                           final ProvidesEntry[] provides) {
        this.attributeNameIndex = attributeNameIndex;
        this.moduleNameIndex = moduleNameIndex;
        this.moduleFlags = moduleFlags;
        this.moduleVersionIndex = moduleVersionIndex;
        this.requires = requires;
        this.exports = exports;
        this.opens = opens;
        this.usesIndex = usesIndex;
        this.provides = provides;
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 14 + requires.length * 6 + exports.length * 8 + opens.length * 8 + provides.length * 6 + usesIndex.length * 2;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeInt(out, this.getDataLength());

        writeShort(out, moduleNameIndex);

        writeShort(out, moduleFlags);

        writeShort(out, moduleVersionIndex);

        writeShort(out, (short) requires.length);

        // Requires write
        for (RequiresEntry entry : requires) {
            writeShort(out, entry.requiresIndex);
            writeShort(out, entry.requiresFlags);
            writeShort(out, entry.requiresVersionIndex);
        }


        writeShort(out, (short) exports.length);

        // Exports write
        for (ExportsEntry entry : exports) {
            writeShort(out, entry.exportsIndex);
            writeShort(out, entry.exportsFlags);
            writeShort(out, (short) entry.exportsToIndex.length);

            for (final short export : entry.exportsToIndex) {
                writeShort(out, export);
            }
        }

        // Opens write
        writeShort(out, (short) opens.length);
        for (OpensEntry entry : opens) {
            writeShort(out, entry.opensIndex);
            writeShort(out, entry.opensFlags);
            writeShort(out, (short) entry.opensToIndex.length);

            for (final short export : entry.opensToIndex) {
                writeShort(out, export);
            }
        }

        writeShort(out, (short) usesIndex.length);
        for (short index : usesIndex) {
            writeShort(out, index);
        }

        // Provides write
        writeShort(out, (short) provides.length);
        for (ProvidesEntry entry : provides) {
            writeShort(out, entry.providesIndex);
            writeShort(out, (short) entry.providesWithIndex.length);

            for (short provides : entry.providesWithIndex) {
                writeShort(out, provides);
            }
        }
    }

    public static ModuleAttribute read(final short ani, final DataInputStream in) throws IOException {
        readInt(in);    // Ignore

        final short moduleNameIndex = in.readUnsignedShort();

        final short moduleFlags = in.readUnsignedShort();

        final short moduleVersionIndex = in.readUnsignedShort();

        // Requires Entry
        final short requiresCount = in.readUnsignedShort();
        final RequiresEntry[] requires = new RequiresEntry[requiresCount];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = new RequiresEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        // Exports Entry
        final short exportsCount = in.readUnsignedShort();
        final ExportsEntry[] exports = new ExportsEntry[exportsCount];
        for (int i = 0; i < exports.length; i++) {
            final short exportsIndex = in.readUnsignedShort();
            final short exportsFlags = in.readUnsignedShort();
            final short exportsToCount = in.readUnsignedShort();

            final short[] exportsToIndex = new short[exportsToCount];
            for (int j = 0; j < exportsToIndex.length; j++) {
                exportsToIndex[j] = in.readUnsignedShort();
            }
            exports[i] = new ExportsEntry(exportsIndex, exportsFlags, exportsToIndex);
        }

        // Opens Entry
        final short opensCount = in.readUnsignedShort();
        final OpensEntry[] opens = new OpensEntry[opensCount];
        for (int i = 0; i < opens.length; i++) {
            final short opensIndex = in.readUnsignedShort();
            final short opensFlags = in.readUnsignedShort();
            final short opensToCount = in.readUnsignedShort();

            final short[] opensToIndex = new short[opensToCount];
            for (int j = 0; j < opensToIndex.length; j++) {
                opensToIndex[j] = in.readUnsignedShort();
            }
            opens[i] = new OpensEntry(opensIndex, opensFlags, opensToIndex);
        }

        final short usesCount = in.readUnsignedShort();
        final short[] usesIndex = new short[usesCount];
        for (int i = 0; i < usesIndex.length; i++) {
            usesIndex[i] = in.readUnsignedShort();
        }

        // Provides Entry
        final short providesCount = in.readUnsignedShort();
        final ProvidesEntry[] provides = new ProvidesEntry[providesCount];
        for (int i = 0; i < provides.length; i++) {
            final short providesIndex = in.readUnsignedShort();
            final short providesWithCount = in.readUnsignedShort();

            final short[] providesWithIndex = new short[providesWithCount];
            for (int j = 0; j < providesWithIndex.length; j++) {
                providesWithIndex[j] = in.readUnsignedShort();
            }
            provides[i] = new ProvidesEntry(providesIndex, providesWithIndex);
        }

        return new ModuleAttribute(ani, moduleNameIndex, moduleFlags, moduleVersionIndex, requires, exports, opens, usesIndex, provides);
    }

    private static record RequiresEntry(short requiresIndex, short requiresFlags, short requiresVersionIndex) {
    }

    private static record ExportsEntry(short exportsIndex, short exportsFlags, short[] exportsToIndex) {
    }

    private static record OpensEntry(short opensIndex, short opensFlags, short[] opensToIndex) {
    }

    private static record ProvidesEntry(short providesIndex, short[] providesWithIndex) {
    }
}