package stats;
import stats.*;
import java.io.*;
import java.nio.file.*;
import genUtil.logger.*;

/**written by Daniel Knuettel*/

public class PathFinder
{

	public static final String stdpathUnix=".FindABar";
	public static final String stdpathDOS="_FindABar";


	/** return the home path __of the program__ */
	/* TODO: test under windows*/
	public static String getHomePath()
	{
		String userpath=System.getProperty("user.home");
		/*maybe we do not know what the userpath is.*/
		if(userpath==null)
		{
			userpath=".";
			System.err.printf("Pathfinder:WARNING: user.home is null using '.'\n");
		}
		String homepath="";
		StringBuilder builder=new StringBuilder();
		String os=OsDetector.detect(OsDetector.OSBASIC);
		builder.append(userpath);
		if(os.compareTo(OsDetector.DOS)==0)
		{
			builder.append("\\");// must the backslash be escaped?
			builder.append(stdpathDOS);
			homepath=builder.toString();
			if(Stats.__DEBUG)
			{
				System.out.printf("homepath='%s'\n",homepath);
			}
		}
		if(os.compareTo(OsDetector.UNIX)==0)
		{
			builder.append("/");
			builder.append(stdpathUnix);
			homepath=builder.toString();
			if(Stats.__DEBUG)
			{
				System.out.printf("homepath='%s'\n",homepath);
			}
		}
		/* create the path,if it is not there yet */
		Path p=Paths.get(homepath);

		return homepath;


	}
	/*TODO: test under windows*/
	public static String getPrettyName(String fileName)
	{
		StringBuilder builder=new StringBuilder();
		builder.append(getHomePath());
		if(OsDetector.isDos())
		{
			builder.append("\\");
		}
		else
		{
			builder.append("/");
		}
		builder.append(fileName);
		return builder.toString();
	}
}
