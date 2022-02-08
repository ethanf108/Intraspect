package launch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.ClassFiles;
import edu.rit.csh.intraspect.data.FieldDesc;
import edu.rit.csh.intraspect.data.MethodDesc;
import edu.rit.csh.intraspect.data.attribute.*;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Main {

    private static void print(ClassFile cf) {
        int index = 0;
        System.out.println("Constant Pool:");
        for (ConstantDesc cd : cf.getConstants()) {
            index++;
            if (cd instanceof EmptyWideConstant) {
                continue;
            }
            System.out.printf("%3s%-3s %-30s %s\n", cd.isValid(cf) ? "" : "(!)", index, cd.getClass().getSimpleName(), cd instanceof UTF8Constant u ? u.getValue() : "");
        }

        System.out.println("\nFields:");
        for (FieldDesc md : cf.getFields()) {
            System.out.println(ClassFiles.fieldSimpleString(md, cf));
            for (AttributeDesc ad : md.getAttributes()) {
                System.out.println("\t" + ad.getClass().getSimpleName());
            }
            System.out.println();
        }

        System.out.println("\nMethods:");
        for (MethodDesc md : cf.getMethods()) {
            System.out.println(ClassFiles.methodSimpleString(md, cf));
            for (AttributeDesc ad : md.getAttributes()) {
                System.out.println("\t" + ad.getClass().getSimpleName());
                if (ad instanceof CodeAttribute cd) {
                    for (Instruction in : cd.getCode()) {
                        System.out.println("\t\t0x" + Integer.toHexString(in.getOpcode()) + " " + in.getMnemonic());
                    }
                    System.out.println();
                    for (AttributeDesc cad : cd.getAttributes()) {
                        System.out.println("\t\t" + cad.getClass().getSimpleName());
                    }
                } else if (ad instanceof SignatureAttribute sa) {
                    System.out.println("\t\t" + (cf.getConstantDesc(sa.getSignatureIndex()) instanceof UTF8Constant u ? u.getValue() : null));
                }
            }
            System.out.println();
        }

        System.out.println("\nClass Attributes:");
        for (AttributeDesc ad : cf.getAttributes()) {
            System.out.println("\t" + ad.getClass().getSimpleName());
            if (ad instanceof SourceFileAttribute sfa) {
                System.out.println("\t\t" + (cf.getConstantDesc(sfa.getSourceFileIndex()) instanceof UTF8Constant u ? u.getValue() : null));
            } else if (ad instanceof SignatureAttribute sa) {
                System.out.println("\t\t" + (cf.getConstantDesc(sa.getSignatureIndex()) instanceof UTF8Constant u ? u.getValue() : null));
            } else if (ad instanceof PermittedSubclassesAttribute psa) {
                for (int cpi : psa.getClasses()) {
                    System.out.println("\t\t" + cpi);
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        File f = new File("C:/Users/ethan/desktop/module/java.base/java/lang/String.class");
//        File f = new File("C:\\Users\\ethan\\Desktop\\test.class");
//        File f = new File("C:\\Users\\ethan\\Documents\\GitHub\\ClassFileViewer\\out\\production\\ClassFileViewer\\data\\ConstantDesc.class");
        ClassFile cf;
        try (FileInputStream in = new FileInputStream(f)) {
            cf = ClassFile.readClassFile(new DataInputStream(in));
        }
        print(cf);
        if (true || false) {
            return;
        }
        try (final FileOutputStream out = new FileOutputStream(f)) {
            cf.write(new DataOutputStream(out));
        }
    }

    public static void main_(String[] args) throws IOException {
        Files
                .walk(new File("C:\\Users\\ethan\\Desktop\\module\\").toPath())
                .filter(n -> n.toFile().getName().endsWith(".class") && !n.toFile().getName().endsWith("module-info.class"))
                .map(n -> {
                    try {
                        return ClassFile.readClassFile(new DataInputStream(new FileInputStream(n.toFile())));
                    } catch (IOException ex) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(n -> {
                    for (MethodDesc md : n.getMethods()) {
                        for (AttributeDesc ad : md.getAttributes()) {
                            if (ad instanceof MethodParametersAttribute mpa) {
                                final String className = n.getConstantDesc(((ClassConstant) n.getConstantDesc(n.getThisClassIndex())).getUTF8Index()) instanceof UTF8Constant u ? u.getValue() : "NO CLASS";
                                final String methodName = n.getConstantDesc(md.getNameIndex()) instanceof UTF8Constant u ? u.getValue() : "NO METHOD";
                                //System.out.print(className + "." + methodName);
                                for (MethodParametersAttribute.Parameter p : mpa.getParameters()) {
                                    if ((p.accessFlags() & 0x8000) > 0) {
                                        final String varName = p.nameIndex() == 0 ? "NO PARAM NAME" : (n.getConstantDesc(p.nameIndex()) instanceof UTF8Constant u ? u.getValue() : null);
                                        System.out.println(className + "." + methodName + ": " + varName);
                                    }
                                }
//                                System.out.println();
                            }
                        }
                    }
                });
    }
}
