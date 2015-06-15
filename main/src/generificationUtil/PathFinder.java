package generificationUtil;

import generificationUtil.logger.BaseLogger;
import statics.Statics;

import java.io.File;

/**
 * written by Daniel Knuettel
 */

public class PathFinder {

    public static final String stdpathUnix = ".FindABar";
    public static final String stdpathDOS = "_FindABar";
    public static final String stdpathMAC = "_FindABar";


    /**
     * return the home path __of the program__
     */
    /* TODO: test under windows*/
    public static String getHomePath() {
        String userpath = System.getProperty("user.home");
        /*maybe we do not know what the userpath is.*/
        if (userpath == null) {
            userpath = ".";
            System.err.printf("Pathfinder:WARNING: user.home is null using '.'\n");
            BaseLogger.log("PathFinder.log", "Pathfinder:getHomePath():WARNING: user.home is null using '.'\n");
        }
        String homepath = "";
        StringBuilder builder = new StringBuilder();
        String os = OsDetector.detect(OsDetector.OSBASIC);
        if (Statics.useExternHome) {
            builder.append(userpath);
        } else {
            builder.append(".");
        }
        if (os.compareTo(OsDetector.DOS) == 0) {
            builder.append("\\");// must the backslash be escaped?
            builder.append(stdpathDOS);
            homepath = builder.toString();
            if (Statics.__DEBUG) {
                //System.out.printf("homepath='%s'\n",homepath);
            }
        }
        if (os.compareTo(OsDetector.UNIX) == 0) {
            builder.append("/");
            if (OsDetector.detect(OsDetector.OSSTD).compareTo(OsDetector.Mac) == 0) {
                builder.append(stdpathMAC);
            } else {
                builder.append(stdpathUnix);
            }
            homepath = builder.toString();
            if (Statics.__DEBUG) {
                //System.out.printf("homepath='%s'\n",homepath);
            }
        }
        /* create the path,if it is not there yet */
        File dir = new File(homepath);
        if (!dir.exists()) {
            BaseLogger.log("PathFinder.log", "Pathfinder:getHomePath():homeDirectory does not exist: creating it\n");
            try {
                dir.mkdir();
            } catch (SecurityException e) {
                BaseLogger.log("PathFinder.log", e.toString());
            }
        }

        return homepath;


    }

    /*TODO: test under windows*/
    public static String getPrettyName(String fileName) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHomePath());
        if (OsDetector.isDos()) {
            builder.append("\\");
        } else {
            builder.append("/");
        }
        builder.append(fileName);
        return builder.toString();
    }
}
