package data;

import data.constant.UTF8Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassFiles {

    /**
     * Private constructor to prevent instantiation.
     */
    private ClassFiles() {
    }

    /**
     * Determine if the given name is a valid unqualified name.
     *
     * @param name The name to check.
     * @return True if the name is valid, false otherwise.
     */
    public static boolean isValidUnqualifiedName(final String name) {
        return !(Objects.requireNonNull(name).isBlank() || name.contains(".") || name.contains(";") || name.contains("[") || name.contains("/"));
    }

    public static boolean isValidQualifiedName(final String name) {
        if (Objects.requireNonNull(name).isBlank()) {
            return false;
        }
        for (final String unqualifiedName : name.split("/")) {
            if (!isValidUnqualifiedName(unqualifiedName)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidClassNameInternalForm(String name) {
        return name.startsWith("[") ? isValidClassDescriptor(name) : isValidQualifiedName(name);
    }

    /**
     * Determine if the given name is a valid class descriptor name.
     *
     * @param descriptor The name to check.
     * @return True if the name is valid, false otherwise.
     */
    public static boolean isValidClassDescriptor(String descriptor) {
        if (descriptor.startsWith("[")) {
            if (!"[".repeat(descriptor.lastIndexOf('[') + 1).equals(descriptor.substring(0, descriptor.lastIndexOf('[') + 1))) {
                return false;
            }
            descriptor = descriptor.substring(descriptor.lastIndexOf('[') + 1);
        }
        return switch (descriptor.charAt(0)) {
            case 'I', 'B', 'C', 'Z', 'S', 'J', 'F', 'D', 'V' -> descriptor.length() == 1;
            case 'L' -> descriptor.endsWith(";") && isValidQualifiedName(descriptor.substring(1, descriptor.length() - 1));
            default -> false;
        };
    }

    public static boolean isValidMethodDescriptor(String descriptor) {
        if (!Objects.requireNonNull(descriptor).startsWith("(")) {
            return false;
        }
        int numArrays = 0;
        boolean retMode = false;
        for (int i = 1; i < descriptor.length(); i++) {
            if (descriptor.charAt(i) == '[') {
                if (numArrays++ >= 255) {
                    return false;
                }
                continue;
            } else if (descriptor.charAt(i) == 'L') {
                if (!descriptor.contains(";") || !isValidClassDescriptor(descriptor.substring(i, descriptor.indexOf(";", i) + 1))) {
                    return false;
                }
                i = descriptor.indexOf(";", i);
                if (retMode) {
                    return true;
                }
            } else if (descriptor.charAt(i) == ')') {
                retMode = true;
            } else if ("IBCZSJFD".contains(String.valueOf(descriptor.charAt(i))) || (retMode && descriptor.charAt(i) == 'V' && numArrays == 0)) {
                if (retMode) {
                    return true;
                }
            } else {
                return false;
            }
            numArrays = 0;
        }
        return false;
    }

    public static String getFromDescriptor(final String desc) {
        Objects.requireNonNull(desc);
        final String baseType = desc.substring(desc.lastIndexOf("[") + 1);
        String ret = switch (baseType) {
            case "I" -> int.class.getCanonicalName();
            case "B" -> byte.class.getCanonicalName();
            case "C" -> char.class.getCanonicalName();
            case "Z" -> boolean.class.getCanonicalName();
            case "S" -> short.class.getCanonicalName();
            case "J" -> long.class.getCanonicalName();
            case "F" -> float.class.getCanonicalName();
            case "D" -> double.class.getCanonicalName();
            case "V" -> void.class.getCanonicalName();
            default -> {
                if (!baseType.startsWith("L") || !baseType.endsWith(";")) {
                    throw new IllegalArgumentException("Invalid Descriptor");
                }

                final String className = baseType.substring(1, baseType.length() - 1).replaceAll("/", ".");
                try {
                    yield Class.forName(className).getCanonicalName();
                } catch (ClassNotFoundException ex) {
                    yield className;
                }
            }
        };

        int numArrays = desc.lastIndexOf("[") + 1;

        if (numArrays > 255) {
            throw new IllegalArgumentException("Array dimensions may not be greater than 255");
        }

        return ret + "[]".repeat(numArrays);
    }

    public static String[] getFromMethodDescriptor(final String desc) {
        if (!desc.startsWith("(")) {
            throw new IllegalArgumentException("Invalid Method Descriptor format");
        }
        final List<String> args = new ArrayList<>();
        int index = 1;
        int numArrays = 0;
        boolean argMode = true;
        OUTER:
        while (index < desc.length()) {
            switch (desc.charAt(index)) {
                case ')' -> {
                    argMode = false;
                    index++;
                }
                case '[' -> {

                    if (numArrays++ == 255) {
                        throw new IllegalArgumentException("Array dimensions may not be greater than 255");
                    }

                    index++;
                }
                default -> {
                    String className;
                    if (desc.charAt(index) == 'L') {
                        className = getFromDescriptor(desc.substring(index, desc.indexOf(";", index) + 1));
                        index = desc.indexOf(";", index) + 1;
                    } else {
                        if (desc.charAt(index) == 'V' && argMode) {
                            throw new IllegalArgumentException("Argument cannot be of type void");
                        }
                        if (desc.charAt(index) == 'V' && numArrays > 0) {
                            throw new IllegalArgumentException("Cannot have a void array");
                        }
                        className = getFromDescriptor(String.valueOf(desc.charAt(index)));
                        index++;
                    }
                    className += "[]".repeat(numArrays);
                    numArrays = 0;
                    if (argMode) {
                        args.add(className);
                    } else {
                        args.add(0, className);
                        break OUTER;
                    }
                }
            }
        }
        if (index != desc.length()) {
            throw new IllegalArgumentException("Method Descriptor has invalid length");
        }

        return args.toArray(new String[0]);
    }

    public static String fieldSimpleString(final FieldDesc fd, final ClassFile cf) {
        final String name = cf.getConstantDesc(fd.getNameIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        if (name == null) {
            throw new IllegalStateException("Field Name Index must point to a UTF 8 Constant");
        }
        final String descriptor = cf.getConstantDesc(fd.getDescriptorIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        if (descriptor == null) {
            throw new IllegalStateException("Field Descriptor Index must point to a UTF 8 Constant");
        }
        final StringBuilder ret = new StringBuilder();
        if ((fd.getAccessFlags() & 0x1) > 0) {
            ret.append("public ");
        } else if ((fd.getAccessFlags() & 0x2) > 0) {
            ret.append("private ");
        } else if ((fd.getAccessFlags() & 0x4) > 0) {
            ret.append("protected ");
        }
        if ((fd.getAccessFlags() & 0x8) > 0) {
            ret.append("static ");
        }
        if ((fd.getAccessFlags() & 0x10) > 0) {
            ret.append("final ");
        }
        if ((fd.getAccessFlags() & 0x40) > 0) {
            ret.append("volatile ");
        }
        if ((fd.getAccessFlags() & 0x80) > 0) {
            ret.append("transient ");
        }
        if ((fd.getAccessFlags() & 0x1000) > 0) {
            ret.append("synthetic ");
        }
        if ((fd.getAccessFlags() & 0x4000) > 0) {
            ret.append("enum ");
        }
        ret
                .append(getFromDescriptor(descriptor))
                .append(" ")
                .append(name)
                .append(";");
        return ret.toString();
    }

    public static String methodSimpleString(final MethodDesc md, final ClassFile cf) {
        final String name = cf.getConstantDesc(md.getNameIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        if (name == null) {
            throw new IllegalStateException("Field Name Index must point to a UTF 8 Constant");
        }
        final String descriptor = cf.getConstantDesc(md.getDescriptorIndex()) instanceof UTF8Constant u ? u.getValue() : null;
        if (descriptor == null) {
            throw new IllegalStateException("Field Descriptor Index must point to a UTF 8 Constant");
        }
        final StringBuilder ret = new StringBuilder();
        if ((md.getAccessFlags() & 0x1) > 0) {
            ret.append("public ");
        } else if ((md.getAccessFlags() & 0x2) > 0) {
            ret.append("private ");
        } else if ((md.getAccessFlags() & 0x4) > 0) {
            ret.append("protected ");
        }
        if ((md.getAccessFlags() & 0x8) > 0) {
            ret.append("static ");
        }
        if ((md.getAccessFlags() & 0x10) > 0) {
            ret.append("final ");
        }
        if ((md.getAccessFlags() & 0x20) > 0) {
            ret.append("synchronized ");
        }
        if ((md.getAccessFlags() & 0x40) > 0) {
            ret.append("bridge ");
        }
        if ((md.getAccessFlags() & 0x100) > 0) {
            ret.append("native ");
        }
        if ((md.getAccessFlags() & 0x400) > 0) {
            ret.append("abstract ");
        }
        if ((md.getAccessFlags() & 0x800) > 0) {
            ret.append("strictfp ");
        }
        if ((md.getAccessFlags() & 0x1000) > 0) {
            ret.append("synthetic ");
        }
        final String[] types = getFromMethodDescriptor(descriptor);
        ret.append(types[0]).append(" ").append(name).append("(");
        boolean has = false;
        for (int i = 1; i < types.length; i++) {
            if (has) {
                ret.append(", ");
            }
            has = true;
            ret.append(types[i]);
            if (i == types.length - 1 && (md.getAccessFlags() & 0x80) > 0) {
                ret.append("...");
            }
        }
        ret.append(");");
        return ret.toString();
    }
}
