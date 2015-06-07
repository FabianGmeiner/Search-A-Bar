package projekt.downloadScheduler;

import projekt.downloadScheduler.Worker;


import org.apache.commons.net.ftp.*;
import java.io.*;
import projekt.ftpUtil.*;

import projekt.genUtil.list.*;

import projekt.stats.Stats;

/* Small util class to download some files using Threads.
 */



public class LoadScheduler
{
	private List mToRun;
	private List mRunning;
	/**
	  <p>
	  <b>mTimeout</b>
	  <br/>
	  Set mTimeout to set the timeout used by org.apache.commons.ftp.FTPClient
	  </p>
	 */
	private int	 mTimeout=3;	// 3 seconds
	private int	 mRetry=1;	// 1 retry
	private boolean _needConstructur2=false;
	private boolean _upload=false;
	private List mRan=new List();

	/**
	 <p>
	 <b> Simple Constructor. You may need to invoke setNewQuery() later. </b>

	 </p>
	 */

	public LoadScheduler()
	{}
	/**
	  <p>
	  Constructor may used with setNewQuery().
	  </p>
	 */
	public LoadScheduler(int timeout, int retry)
	{
		mRunning=new List();
		this.mTimeout=timeout;
		this.mRetry=retry;
		this._needConstructur2=true;
	}
	

	public LoadScheduler(FtpQuery _query)
	{
		mToRun=_query.toDataSections();
		mRunning=new List();
		_upload=_query.isUpload();
	}
	public LoadScheduler(FtpQuery _query,int timeout, int retry)
	{
		mToRun=_query.toDataSections();
		mRunning=new List();
		this.mTimeout=timeout;
		this.mRetry=retry;
		this._needConstructur2=true;
		_upload=_query.isUpload();
	}

	public void setNewQuery(FtpQuery _query)
	{
		mToRun=_query.toDataSections();
		_upload=_query.isUpload();

	}

	public void buildWorkers()
	{
		FtpData swp=(FtpData) mToRun.remove();
		while(swp!=null)
		{
			if(_needConstructur2)
			{
				if(_upload)
				{
					mRunning.insert (new UploadWorker(swp,mTimeout,mRetry));
				}
				else
				{
					mRunning.insert (new DownloadWorker(swp,mTimeout,mRetry));
				}

			}
			else
			{
				if(_upload)
				{
					mRunning.insert (new UploadWorker(swp));
				}
				else
				{
					mRunning.insert (new DownloadWorker(swp));
				}

			}
			swp=(FtpData)mToRun.remove();
		}
	}

	public void work()
	{
		Worker swp=(Worker)mRunning.remove();
		while(swp!=null)
		{
			swp.start();
			mRan.insert(swp);
			swp=(Worker)mRunning.remove();
		}
	
	}
}
