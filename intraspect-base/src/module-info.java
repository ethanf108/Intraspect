module ClassFileViewer {
    requires java.base;

    exports edu.rit.csh.intraspect.data;
    exports edu.rit.csh.intraspect.data.attribute;
    exports edu.rit.csh.intraspect.data.attribute.annotation;
    exports edu.rit.csh.intraspect.data.attribute.annotation.type;
    exports edu.rit.csh.intraspect.data.attribute.stackmaptable;
    exports edu.rit.csh.intraspect.data.attribute.stackmaptable.verificationtypeinfo;

    exports edu.rit.csh.intraspect.data.constant;

    exports edu.rit.csh.intraspect.data.instruction;
    exports edu.rit.csh.intraspect.data.instruction.branch;
    exports edu.rit.csh.intraspect.data.instruction.compare;
    exports edu.rit.csh.intraspect.data.instruction.constant;
    exports edu.rit.csh.intraspect.data.instruction.control;
    exports edu.rit.csh.intraspect.data.instruction.conversion;
    exports edu.rit.csh.intraspect.data.instruction.field;
    exports edu.rit.csh.intraspect.data.instruction.invoke;
    exports edu.rit.csh.intraspect.data.instruction.load;
    exports edu.rit.csh.intraspect.data.instruction.math;
    exports edu.rit.csh.intraspect.data.instruction.math.add;
    exports edu.rit.csh.intraspect.data.instruction.math.and;
    exports edu.rit.csh.intraspect.data.instruction.math.div;
    exports edu.rit.csh.intraspect.data.instruction.math.mul;
    exports edu.rit.csh.intraspect.data.instruction.math.neg;
    exports edu.rit.csh.intraspect.data.instruction.math.or;
    exports edu.rit.csh.intraspect.data.instruction.math.rem;
    exports edu.rit.csh.intraspect.data.instruction.math.shl;
    exports edu.rit.csh.intraspect.data.instruction.math.shr;
    exports edu.rit.csh.intraspect.data.instruction.math.sub;
    exports edu.rit.csh.intraspect.data.instruction.math.ushr;
    exports edu.rit.csh.intraspect.data.instruction.math.xor;
    exports edu.rit.csh.intraspect.data.instruction.misc;
    exports edu.rit.csh.intraspect.data.instruction.monitor;
    exports edu.rit.csh.intraspect.data.instruction.object;
    exports edu.rit.csh.intraspect.data.instruction.reserved;
    exports edu.rit.csh.intraspect.data.instruction.stack;
    exports edu.rit.csh.intraspect.data.instruction.store;
    exports edu.rit.csh.intraspect.data.instruction.wide;
    exports edu.rit.csh.intraspect.data.instruction.wide.load;
    exports edu.rit.csh.intraspect.data.instruction.wide.store;

    exports edu.rit.csh.intraspect.util;
}
