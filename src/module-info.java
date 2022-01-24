module ClassFileViewer {
    requires java.base;

    exports data;
    exports data.attribute;
    exports data.attribute.annotation;
    exports data.attribute.annotation.type;
    exports data.attribute.stackmaptable;
    exports data.attribute.stackmaptable.verificationtypeinfo;
    exports data.constant;
    exports util;
}
