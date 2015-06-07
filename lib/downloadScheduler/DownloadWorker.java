package downloadScheduler;

import downloadScheduler.Worker;


import org.apache.commons.net.ftp.*;
import java.io.*;
import ftpUtil.*;

import stats.Stats;

/*
   Worker to fetch files from a ftp server
   will fork from the main process and download the file.
   Every Worker takes only one file.
   Written by Daniel Knuettel 2015_
 */


public class DownloadWorker 
extends Thread
implements Worker
{

	private FtpData login=null;
	private int		timeout=3;	// 3 seconds
	private int		retry=1;	// 1 retry
	private int		retrys=0;   // retrys done

	public DownloadWorker(FtpData login)
	{
		this.login=login;
	}
	public DownloadWorker(FtpData login,int timeout, int retry)
	{
		this.login=login;
		this.timeout=timeout;
		this.retry=retry;
	}
	/* for reusage protected */
	protected void die(String msg)
	{
		System.err.printf("ERR: Worker(%s): Error occurred : %s: dead\n",login.getFileName(),msg);
		System.exit(-1);
	}
	public int work()
	{
		int running=1;
		while(running!=0)
		{
			if(Stats.__DEBUG)
			{
				System.out.printf("(%s) trying to fetch %d of %d times\n",login.getFileName(),retrys,retry);
			}
			running=fetch();
			if(retry==retrys)
			{
				die("retried too often");
			}
			retrys++;
		}
		return 0;
	}
	protected int fetch()
	{
		FTPClient ftp=new FTPClient();
		/* do no config and hope everything is alright.. */
		try
		{
			ftp.setConnectTimeout(timeout);
			ftp.connect(login.getServerName());
			int serverReply=ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(serverReply))
			{
				ftp.disconnect();	
				if(Stats.__DEBUG)
				{
					System.err.printf("(%s) serverReply is negative: %s\n",login.getServerName(),serverReply);
				}
				return -1;
			}
			if(login.needLogin())
			{
				if(Stats.__DEBUG)
				{
					System.out.printf("(%s) need to login as %s\n",login.getServerName(),login.getUserName());
				}
				ftp.login(login.getUserName(),login.getUserPasswd());
			}
			FTPFile[] remotefile=ftp.listFiles(login.getFileName());
			if(remotefile.length==0)
			{
				die("File not found on FTP");
			}
			FileOutputStream file;
			try
			{
				file=new FileOutputStream(login.getFileName());
				ftp.retrieveFile(login.getFileName(),file);
			}
			catch (IOException e)
			{
				die("cannot create File");
			}

			/* do not hurry */
			ftp.sendNoOp();
			ftp.logout();
			ftp.disconnect();
			return 0;

		}
		catch (Exception e)
		{
			die(e.getStackTrace().toString());
		}
		return -1;
	}
	public void run()
	{
		work();
	}
}
