package edu.rit.csh.intraspect.gui.entries;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;

public final class ConstantEntry implements Entry {

    public ConstantDesc constantDesc;
    public ClassFile classFile;
    public int index;

    public ConstantEntry(final ConstantDesc constantDesc, final int index, final ClassFile classFile) {
        this.constantDesc = constantDesc;
        this.classFile = classFile;
        this.index = index;
    }

    @Override
    public void remove() {
        classFile.removeConstant(index);
    }

    @Override
    public String toString() {
        return "#" + index + " = " + constantDesc.getName() + "\t" + constantDesc.getInfo() + "\t// ";
    }

    /*
    #1 = Methodref          #2.#3         // java/lang/Object."<init>":()V
    #2 = Class              #4            // java/lang/Object
    #3 = NameAndType        #5:#6         // "<init>":()V
     */
}
