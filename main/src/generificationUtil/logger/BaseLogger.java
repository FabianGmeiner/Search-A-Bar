package generificationUtil.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * writen by Daniel Knuettel
 */

public class BaseLogger {
    public static void log(String filename, String _msg) {
        FileOutputStream logfile;
        try {
            logfile = new FileOutputStream(filename, true);
            PrintStream log = new PrintStream(logfile);

			/*to know when it was logged */
            Date now = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            log.printf("[%s] %s", formatter.format(now), _msg);
            log.close();
            logfile.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }

    }
}
