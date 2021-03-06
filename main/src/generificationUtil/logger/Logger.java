package generificationUtil.logger;

import statics.Statics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static generificationUtil.PathFinder.getPrettyName;


/**
 * A completly static class for logging
 * written by Daniel Knuettel
 */

public class Logger {
    public static final int ERROR = 1;
    public static final int WARN = 2;
    public static final int MSG = 3;
    public static final int DEBUG = 4;


    private static String _errFile = "ERROR.log";
    private static String _warnFile = "WARN.log";
    private static String _msgFile = "MSG.log";
    private static String _debugFile = "DEBUG.log";

    public static void log(int logType, String _msg) {
        FileOutputStream logfile;
        String errFile = getPrettyName(_errFile);
        String warnFile = getPrettyName(_warnFile);
        String msgFile = getPrettyName(_msgFile);
        String debugFile = getPrettyName(_debugFile);


        try {
            switch (logType) {
                case ERROR: {
                    logfile = new FileOutputStream(errFile, true);
                }
                break;
                case WARN: {
                    logfile = new FileOutputStream(warnFile, true);
                }
                break;
                case MSG: {
                    logfile = new FileOutputStream(msgFile, true);
                }
                break;
                case DEBUG: {
                    logfile = new FileOutputStream(debugFile, true);
                }
                break;
                default: {
                    System.err.printf("Logger:Error: logType (%d) not recognized\n", logType);
                    return;
                }
            }
            PrintStream log = new PrintStream(logfile);

			/*to know when it was logged */
            Date now = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            log.printf("[%s] %s", formatter.format(now), _msg);
            log.close();
            logfile.close();
        } catch (IOException e) {
            System.err.printf("Logger:Error: cannot log msgtype (%d) is the path writable?\n", logType);
        }
    }

    public static void clearFiles() {
        try {
            String errFile = getPrettyName(_errFile);
            String warnFile = getPrettyName(_warnFile);
            String msgFile = getPrettyName(_msgFile);
            String debugFile = getPrettyName(_debugFile);
            if (Statics.truncateFiles) {
                new FileOutputStream(errFile).getChannel().truncate(0).close();
                new FileOutputStream(warnFile).getChannel().truncate(0).close();
                new FileOutputStream(msgFile).getChannel().truncate(0).close();
                new FileOutputStream(debugFile).getChannel().truncate(0).close();
            } else {
                new PrintStream(new FileOutputStream(errFile, true)).print("\n\n#################\n\n\n");
                new PrintStream(new FileOutputStream(warnFile, true)).print("\n\n#################\n\n\n");
                new PrintStream(new FileOutputStream(msgFile, true)).print("\n\n#################\n\n\n");
                new PrintStream(new FileOutputStream(debugFile, true)).print("\n\n#################\n\n\n");
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }

    }
}
