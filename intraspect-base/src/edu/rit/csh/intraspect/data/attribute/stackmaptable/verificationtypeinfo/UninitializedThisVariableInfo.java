package edu.rit.csh.intraspect.data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The UninitializedThisVariableInfo verification type.
 */
public final class UninitializedThisVariableInfo extends VerificationTypeInfo {

    public UninitializedThisVariableInfo(final int tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }

}
