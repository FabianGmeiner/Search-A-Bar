package projekt.downloadScheduler;

import projekt.downloadScheduler.Worker;

import org.apache.commons.net.ftp.*;
import java.io.*;
import projekt.ftpUtil.*;

import projekt.stats.Stats;

/*
   Worker to upload files to a ftp server
   will fork from the main process and uploadload the file.
   Every Worker takes only one file.
   Written by Daniel Knuettel 2015_
 */


/**
 <p>
 class UploadWorker
 <p>
 	Upload files to a ftp server,
	the std Timeout is 3 seconds,
	std retrys=1,
	both may be a bit small, try to use the 
	constructor UploadWorker(FtpData,int,int).
 </p>
 </p>
 */

public class UploadWorker
extends Thread
implements Worker
{
	private FtpData login=null;
	private int timeout=3;	// 3 seconds
	private int retry=1;	// 1 retry
	private int retrys=0;   // retrys done

	/* simple constructor */
	/**
	  <p>
	  Use std timeout and retrys. 
	  <br/>
	  </p>
	 */
	public UploadWorker(FtpData login)
	{
		this.login=login;
	}
	/* more complex constructor */
	public UploadWorker(FtpData login, int timeout, int retry)
	{
		this.login=login;
		this.timeout=timeout;
		this.retry=retry;
		
	}
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
			if(retry==retrys)
			{
				die("Retried too often.");
			}
			retrys++;
			running=push();
		}
		return 0;
	}

	public void run()
	{
		work();
	}
	protected int push()
	{
		FTPClient ftp=new FTPClient();

		try
		{
			ftp.setConnectTimeout(timeout);
			ftp.connect(login.getServerName());
			int serverReply=ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(serverReply))
			{
				ftp.disconnect();	
				return -1;
			}
			if(login.needLogin())
			{
				ftp.login(login.getUserName(),login.getUserPasswd());
			}
			FileInputStream file;
			try
			{
				file=new FileInputStream(login.getFileName());	
				/* do not send filetype, because we do not know it */
				boolean ret=ftp.storeFile(login.getFileName(),file);
				if(!ret)
				{
					if(Stats.__DEBUG)
					{
						System.err.printf("(%s) failed to store file on remote\n",login.getFileName());
					}
					ftp.sendNoOp();
					ftp.logout();
					ftp.disconnect();
					return -1;
				}
			}
			catch  (IOException e)
			{
				die("cannot open file");
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


}

