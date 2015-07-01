package generificationUtil;

/**
 * written by Daniel Knuettel
 */

public class OsDetector {

    /**
     * OSBASIC may return  UNIX or DOS
     */
    public static final int OSBASIC = 1;
    /**
     * OSSTD may return Windows,Linux,Mac,Bsd(is there a javaport for bsd?),Solaris,...
     */
    public static final int OSSTD = 2;
    /**
     * OSEXTENDED returns the full osname
     */
    public static final int OSEXTENDED = 3;

    public static final String NOTRECOGNIZED = "___";

    public static final String DOS = "DOS";
    public static final String UNIX = "UNIX";//="*NIX"
    public static final String Windows = "Windows";
    public static final String Linux = "Linux";
    public static final String Mac = "Mac";
    public static final String Bsd = "Bsd";
    public static final String Solaris = "Solaris";

    /**
     * As there is no java implementation on microkernels (mach) only a few osses are recognized
     */
    public static String detect(int mode) {
        String os = System.getProperty("os.name");
        switch (mode) {
            case OSBASIC: {
                if (os.startsWith("Windows")) {
                    return DOS;
                }
                if (os.startsWith("Linux") || os.startsWith("Bsd") || os.startsWith("Mac") || os.startsWith("Solaris")) {
                    return UNIX;
                }
                return NOTRECOGNIZED;
            }// no break because return.
            case OSSTD: {
                if (os.startsWith("Windows")) {
                    return Windows;
                }
                if (os.startsWith("Linux")) {
                    return Linux;
                }
                if (os.startsWith("Bsd")) {
                    return Bsd;
                }
                if (os.startsWith("Mac")) {
                    return Mac;
                }
                if (os.startsWith("Solaris")) {
                    return Solaris;
                }
                if (os.startsWith("MS") || os.startsWith("Ms") || os.startsWith("ms")) {
                    return DOS;
                }
                return NOTRECOGNIZED;
            }
            case OSEXTENDED: {
                return System.getProperty("os.name");
            }
            default: {
                return NOTRECOGNIZED;
            }
        }
    }

    public static boolean isDos() {
        return detect(OSBASIC).compareTo(DOS) == 0;
    }
}
