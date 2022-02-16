package edu.rit.csh.intraspect.gui.entries;

import javafx.scene.layout.Pane;

public sealed interface Entry permits ConstantEntry, FieldEntry, MethodEntry, AttributeEntry {
    Pane buildPane();
}
