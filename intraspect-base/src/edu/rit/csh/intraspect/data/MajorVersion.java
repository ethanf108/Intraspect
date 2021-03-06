package edu.rit.csh.intraspect.data;

import java.util.Optional;

/**
 * This class represents the major version of a class file.
 */
public class MajorVersion {

    /**
     * The major version.
     */
    private final int majorVersion;

    /**
     * Constructs a new MajorVersion object.
     *
     * @param mv The major version number.
     */
    public MajorVersion(final int mv) {
        this.majorVersion = mv;
    }

    /**
     * Returns the major version number.
     *
     * @return The major version number.
     */
    public int getMajorVersion() {
        return this.majorVersion;
    }

    /**
     * Returns the major version number as a string.
     *
     * @return The major version number as a string.
     */
    public Optional<String> getJavaVersionString() {
        return Optional.ofNullable(switch (this.majorVersion) {
            case 45 -> "Java 1.0.2 / 1.1";
            case 46 -> "Java 1.2";
            case 47 -> "Java 1.3";
            case 48 -> "Java 1.4";
            case 49 -> "Java 5.0";
            case 50 -> "Java 6";
            case 51 -> "Java 7";
            case 52 -> "Java 8";
            case 53 -> "Java 9";
            case 54 -> "Java 10";
            case 55 -> "Java 11";
            case 56 -> "Java 12";
            case 57 -> "Java 13";
            case 58 -> "Java 14";
            case 59 -> "Java 15";
            case 60 -> "Java 16";
            case 61 -> "Java 17";
            default -> null;
        });
    }
}
