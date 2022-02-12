package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Describes an attribute of a class.
 */
public sealed interface AttributeDesc permits
        AnnotationDefaultAttribute, BootstrapMethodsAttribute, CodeAttribute, ConstantValueAttribute,
        CustomAttribute, DeprecatedAttribute, EnclosingMethodAttribute, ExceptionsAttribute,
        InnerClassesAttribute, LineNumberTableAttribute, LocalVariableTableAttribute, LocalVariableTypeTableAttribute,
        MethodParametersAttribute, ModuleAttribute, ModuleMainClassAttribute, ModulePackagesAttribute,
        NestHostAttribute, NestMembersAttribute, PermittedSubclassesAttribute, RecordAttribute,
        RuntimeAnnotations, RuntimeParameterAnnotations, RuntimeTypeAnnotations, SignatureAttribute,
        SourceDebugExtensionAttribute, SourceFileAttribute, StackMapTableAttribute, SyntheticAttribute,
        UnknownAttribute {

    int getAttributeNameIndex();

    int getDataLength();

    void write(final DataOutputStream out) throws IOException;

    default Optional<String> getAttributeName(final ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.getAttributeNameIndex()) instanceof UTF8Constant u ? u.getValue() : null);
    }
}
