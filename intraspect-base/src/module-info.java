module intraspect.base {
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

    exports edu.rit.csh.intraspect.edit;

    exports edu.rit.csh.intraspect.util;


    opens edu.rit.csh.intraspect.data to intraspect.cli, intraspect.gui;
    opens edu.rit.csh.intraspect.data.attribute to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.attribute.annotation to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.attribute.annotation.type to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.attribute.stackmaptable to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.attribute.stackmaptable.verificationtypeinfo to intraspect.base, intraspect.gui;

    opens edu.rit.csh.intraspect.data.constant to intraspect.base, intraspect.gui;

    opens edu.rit.csh.intraspect.data.instruction to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.branch to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.compare to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.constant to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.control to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.conversion to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.field to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.invoke to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.load to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.add to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.and to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.div to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.mul to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.neg to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.or to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.rem to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.shl to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.shr to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.sub to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.ushr to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.math.xor to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.misc to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.monitor to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.object to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.reserved to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.stack to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.store to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.wide to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.wide.load to intraspect.base, intraspect.gui;
    opens edu.rit.csh.intraspect.data.instruction.wide.store to intraspect.base, intraspect.gui;

    opens edu.rit.csh.intraspect.edit to intraspect.base, intraspect.gui;
}
