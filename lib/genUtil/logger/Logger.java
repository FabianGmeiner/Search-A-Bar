package genUtil.logger;

import java.io.*;
import stats.*;
import java.util.*;
import java.text.*;

/**
  A completly static class for logging
 */

public class Logger
{
	public static final int ERROR=1;
	public static final int WARN=2;
	public static final int MSG=3;
	public static final int DEBUG=4;


	private static String _errFile="ERROR.log";
	private static String _warnFile="WARN.log";
	private static String _msgFile="MSG.log";
	private static String _debugFile="DEBUG.log";

	public static void log ( int logType, String _msg)
	{
		FileOutputStream logfile;
		String errFile=PathFinder.getPrettyName(_errFile);
		String warnFile=PathFinder.getPrettyName(_warnFile);
		String msgFile=PathFinder.getPrettyName(_msgFile);
		String debugFile=PathFinder.getPrettyName(_debugFile);


		try
		{
			switch (logType)
			{
				case ERROR:
				{
					logfile=new FileOutputStream(errFile,true);
				}
				break;
				case WARN:
				{
					logfile=new FileOutputStream(warnFile,true);
				}
				break;
				case MSG:
				{
					logfile=new FileOutputStream(msgFile,true);
				}
				break;
				case DEBUG:
				{
					logfile=new FileOutputStream(debugFile,true);
				}
				break;
				default:
				{
					System.err.printf("Logger:Error: logType (%d) not recognized\n",logType);
					return;
				}
			}
			PrintStream log=new PrintStream(logfile);

			/*to know when it was logged */
			Date now = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			log.printf("[%s] %s",formatter.format(now),_msg);
			log.close();
			logfile.close();
		}
		catch (IOException e)
		{
			System.err.printf("Logger:Error: cannot log msgtype (%d) is the path writable?\n",logType);
			return;
		}

		return;
	}
}
