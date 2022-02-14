package edu.rit.csh.intraspect.edit;

public interface ConstantPoolIndexedRecord<T extends Record> {

    T shift(int index, int delta);
}
