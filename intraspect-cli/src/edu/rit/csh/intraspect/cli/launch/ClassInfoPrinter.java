package edu.rit.csh.intraspect.cli.launch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.ClassFiles;
import edu.rit.csh.intraspect.data.FieldDesc;
import edu.rit.csh.intraspect.data.MethodDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import edu.rit.csh.intraspect.data.attribute.CodeAttribute;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.io.PrintStream;

public class ClassInfoPrinter {

    private final boolean decompile;
    private final boolean showConstants;
    private final boolean showAttributes;
    private final boolean verify;

    public ClassInfoPrinter(boolean decompile, boolean showConstants, boolean showAttributes, boolean verify) {
        this.decompile = decompile;
        this.showConstants = showConstants;
        this.showAttributes = showAttributes;
        this.verify = verify;
    }

    public void printClass(final ClassFile cf, final PrintStream out) {
        out.println(ClassFiles.classSimpleString(cf));
        out.println();
        if (this.showConstants) {
            int index = 0;
            out.println("Constant Pool:");
            for (ConstantDesc cd : cf.getConstants()) {
                index++;
                if (cd instanceof EmptyWideConstant) {
                    continue;
                }
                final int numCols = (int) Math.log10(cf.getConstants().length) + 1;
                String className = cd.getClass().getSimpleName();
                className = className.substring(0, className.indexOf("Constant"));
                if (this.verify) {
                    out.printf("%3s%-" + numCols + "s %-20s %s\n", cd.isValid(cf) ? "" : "(!)", index, className, cd.getInfo());
                } else {
                    out.printf("%-" + numCols + "s %-20s %s\n", index, className, cd.getInfo());
                }
            }
            out.println();
        }

        out.println("Fields:");
        for (FieldDesc fd : cf.getFields()) {
            out.println(ClassFiles.fieldSimpleString(fd, cf));
            if (this.showAttributes) {
                for (AttributeDesc ad : fd.getAttributes()) {
                    out.println("\t" + ad.getAttributeName(cf).orElse(ad.getClass().getSimpleName()));
                }
            }
            out.println();
        }

        out.println();
        out.println("Methods:");
        for (MethodDesc md : cf.getMethods()) {
            out.println(ClassFiles.methodSimpleString(md, cf));
            for (AttributeDesc ad : md.getAttributes()) {
                if (ad instanceof CodeAttribute cd) {
                    if (this.decompile || this.showAttributes) {
                        out.println("\tCode");
                    }
                    if (this.decompile) {
                        for (Instruction in : cd.getCode()) {
                            if (this.verify && !in.isValid(cf)) {
                                out.println("\t\t(!)" + in.getMnemonic());
                            } else {
                                out.println("\t\t" + in.getMnemonic());
                            }
                        }
                        out.println();
                    }
                    if (this.showAttributes) {
                        for (AttributeDesc cad : cd.getAttributes()) {
                            out.println("\t" + cad.getClass().getSimpleName());
                        }
                    }
                } else {
                    if (this.showAttributes) {
                        out.println("\t" + ad.getAttributeName(cf).orElse(ad.getClass().getSimpleName()));
                    }
                }
            }
            out.println();
        }

        if (this.showAttributes) {
            out.println();
            out.println("Class Attributes:");
            for (AttributeDesc ad : cf.getAttributes()) {
                out.println("\t" + ad.getAttributeName(cf).orElse(ad.getClass().getSimpleName()));
            }
        }
        out.println();
    }
}
