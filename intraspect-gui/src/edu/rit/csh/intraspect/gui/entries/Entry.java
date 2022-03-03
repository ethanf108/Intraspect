package edu.rit.csh.intraspect.gui.entries;

public sealed interface Entry permits ConstantEntry, FieldEntry, MethodEntry, AttributeEntry {

    void remove();

}
