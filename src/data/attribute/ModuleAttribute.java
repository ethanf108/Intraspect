package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("Module")
public class ModuleAttribute implements AttributeDesc {

    private final int attributeNameIndex;

    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private final RequiresEntry[] requires;
    private final ExportsEntry[] exports;
    private final OpensEntry[] opens;
    private final int[] usesIndex;
    private final ProvidesEntry[] provides;

    public ModuleAttribute(final int attributeNameIndex,
                           final int moduleNameIndex,
                           final int moduleFlags,
                           final int moduleVersionIndex,
                           final RequiresEntry[] requires,
                           final ExportsEntry[] exports,
                           final OpensEntry[] opens,
                           final int[] usesIndex,
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
    public int getAttributeNameIndex() {
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

    public static ModuleAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final int moduleNameIndex = in.readUnsignedShort();

        final int moduleFlags = in.readUnsignedShort();

        final int moduleVersionIndex = in.readUnsignedShort();

        // Requires Entry
        final int requiresCount = in.readUnsignedShort();
        final RequiresEntry[] requires = new RequiresEntry[requiresCount];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = new RequiresEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        // Exports Entry
        final int exportsCount = in.readUnsignedShort();
        final ExportsEntry[] exports = new ExportsEntry[exportsCount];
        for (int i = 0; i < exports.length; i++) {
            final int exportsIndex = in.readUnsignedShort();
            final int exportsFlags = in.readUnsignedShort();
            final int exportsToCount = in.readUnsignedShort();

            final int[] exportsToIndex = new int[exportsToCount];
            for (int j = 0; j < exportsToIndex.length; j++) {
                exportsToIndex[j] = in.readUnsignedShort();
            }
            exports[i] = new ExportsEntry(exportsIndex, exportsFlags, exportsToIndex);
        }

        // Opens Entry
        final int opensCount = in.readUnsignedShort();
        final OpensEntry[] opens = new OpensEntry[opensCount];
        for (int i = 0; i < opens.length; i++) {
            final int opensIndex = in.readUnsignedShort();
            final int opensFlags = in.readUnsignedShort();
            final int opensToCount = in.readUnsignedShort();

            final int[] opensToIndex = new int[opensToCount];
            for (int j = 0; j < opensToIndex.length; j++) {
                opensToIndex[j] = in.readUnsignedShort();
            }
            opens[i] = new OpensEntry(opensIndex, opensFlags, opensToIndex);
        }

        final int usesCount = in.readUnsignedShort();
        final int[] usesIndex = new int[usesCount];
        for (int i = 0; i < usesIndex.length; i++) {
            usesIndex[i] = in.readUnsignedShort();
        }

        // Provides Entry
        final int providesCount = in.readUnsignedShort();
        final ProvidesEntry[] provides = new ProvidesEntry[providesCount];
        for (int i = 0; i < provides.length; i++) {
            final int providesIndex = in.readUnsignedShort();
            final int providesWithCount = in.readUnsignedShort();

            final int[] providesWithIndex = new int[providesWithCount];
            for (int j = 0; j < providesWithIndex.length; j++) {
                providesWithIndex[j] = in.readUnsignedShort();
            }
            provides[i] = new ProvidesEntry(providesIndex, providesWithIndex);
        }

        return new ModuleAttribute(ani, moduleNameIndex, moduleFlags, moduleVersionIndex, requires, exports, opens, usesIndex, provides);
    }

    private static record RequiresEntry(int requiresIndex, int requiresFlags, int requiresVersionIndex) {
    }

    private static record ExportsEntry(int exportsIndex, int exportsFlags, int[] exportsToIndex) {
    }

    private static record OpensEntry(int opensIndex, int opensFlags, int[] opensToIndex) {
    }

    private static record ProvidesEntry(int providesIndex, int[] providesWithIndex) {
    }
}