package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Module attribute.
 */
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

    public static ModuleAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final int moduleNameIndex = in.readUnsignedShort();

        final int moduleFlags = in.readUnsignedShort();

        final int moduleVersionIndex = in.readUnsignedShort();

        // Requires Entry
        final RequiresEntry[] requires = new RequiresEntry[in.readUnsignedShort()];
        for (int i = 0; i < requires.length; requires[i++] = RequiresEntry.read(in)) ;

        // Exports Entry
        final ExportsEntry[] exports = new ExportsEntry[in.readUnsignedShort()];
        for (int i = 0; i < exports.length; exports[i++] = ExportsEntry.read(in)) ;

        // Opens Entry
        final OpensEntry[] opens = new OpensEntry[in.readUnsignedShort()];
        for (int i = 0; i < opens.length; opens[i++] = OpensEntry.read(in)) ;

        final int[] usesIndex = new int[in.readUnsignedShort()];
        for (int i = 0; i < usesIndex.length; usesIndex[i++] = in.readUnsignedShort()) ;

        // Provides Entry
        final ProvidesEntry[] provides = new ProvidesEntry[in.readUnsignedShort()];
        for (int i = 0; i < provides.length; provides[i++] = ProvidesEntry.read(in)) ;

        return new ModuleAttribute(ani, moduleNameIndex, moduleFlags, moduleVersionIndex, requires, exports, opens, usesIndex, provides);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 14 + this.requires.length * 6
                + (this.exports.length << 3)
                + (this.opens.length << 3)
                + this.provides.length * 6
                + (this.usesIndex.length << 1);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);

        out.writeInt(this.getDataLength());

        out.writeShort(this.moduleNameIndex);

        out.writeShort(this.moduleFlags);

        out.writeShort(this.moduleVersionIndex);

        out.writeShort(this.requires.length);

        // Requires write
        for (final RequiresEntry entry : this.requires) {
            entry.write(out);
        }

        out.writeShort(this.exports.length);

        // Exports write
        for (final ExportsEntry entry : this.exports) {
            entry.write(out);
        }

        // Opens write
        out.writeShort(this.opens.length);
        for (final OpensEntry entry : this.opens) {
            entry.write(out);
        }

        out.writeShort(this.usesIndex.length);
        for (final int index : this.usesIndex) {
            out.writeShort(index);
        }

        // Provides write
        out.writeShort(this.provides.length);
        for (final ProvidesEntry entry : this.provides) {
            entry.write(out);
        }
    }

    public record RequiresEntry(int requiresIndex, int requiresFlags, int requiresVersionIndex) {

        public static RequiresEntry read(final DataInputStream in) throws IOException {
            return new RequiresEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.requiresIndex);
            out.writeShort(this.requiresFlags);
            out.writeShort(this.requiresVersionIndex);
        }
    }

    public record ExportsEntry(int exportsIndex, int exportsFlags, int[] exportsToIndex) {

        public static ExportsEntry read(final DataInputStream in) throws IOException {
            final int exportsIndex = in.readUnsignedShort();
            final int exportsFlags = in.readUnsignedShort();

            final int[] exportsToIndex = new int[in.readUnsignedShort()];
            for (int j = 0; j < exportsToIndex.length; j++) {
                exportsToIndex[j] = in.readUnsignedShort();
            }
            return new ExportsEntry(exportsIndex, exportsFlags, exportsToIndex);
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.exportsIndex);
            out.writeShort(this.exportsFlags);
            out.writeShort(this.exportsToIndex.length);

            for (final int export : this.exportsToIndex) {
                out.writeShort(export);
            }
        }

    }

    public record OpensEntry(int opensIndex, int opensFlags, int[] opensToIndex) {

        public static OpensEntry read(final DataInputStream in) throws IOException {
            final int opensIndex = in.readUnsignedShort();
            final int opensFlags = in.readUnsignedShort();
            final int opensToCount = in.readUnsignedShort();

            final int[] opensToIndex = new int[opensToCount];
            for (int j = 0; j < opensToIndex.length; j++) {
                opensToIndex[j] = in.readUnsignedShort();
            }
            return new OpensEntry(opensIndex, opensFlags, opensToIndex);
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.opensIndex);
            out.writeShort(this.opensFlags);
            out.writeShort(this.opensToIndex.length);

            for (final int export : this.opensToIndex) {
                out.writeShort(export);
            }
        }
    }

    public record ProvidesEntry(int providesIndex, int[] providesWithIndex) {

        public static ProvidesEntry read(final DataInputStream in) throws IOException {
            final int providesIndex = in.readUnsignedShort();

            final int[] providesWithIndex = new int[in.readUnsignedShort()];
            for (int j = 0; j < providesWithIndex.length; j++) {
                providesWithIndex[j] = in.readUnsignedShort();
            }

            return new ProvidesEntry(providesIndex, providesWithIndex);
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.providesIndex);
            out.writeShort(this.providesWithIndex.length);

            for (final int provides : this.providesWithIndex) {
                out.writeShort(provides);
            }
        }
    }
}
