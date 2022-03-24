package edu.rit.csh.intraspect.edit;

public interface ConstantPoolIndexedRecord<T extends Record> {

    T shift(final int index, final int delta);
}
