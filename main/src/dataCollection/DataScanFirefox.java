package dataCollection;

import generificationUtil.list.List;

public class DataScanFirefox extends DataScan
{
	//protected  WildcardFileFilter mFilter;
	protected List mFiles;

	protected int mOsType;

	protected String mStdPathWindows="C:\\Users\\$USER\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles";
	protected String mProfilePathWindows="\\*.default";
	protected String mStdPathUnix="/home/$USER/.mozilla/firefox";
	protected String mProfilePathUnix="/*.default";
	protected String [] mWantedFilesWindows=
	{
		"\\addons.json","\\webapps\\webapps.json"
	};
	protected String [] mWantedRegexFilesWindows=
	{
		"\\bookmarkbackups\\bookmarks-*.json"
	};

	public DataScanFirefox()
	{
		mFiles=new List();
		//mFilter=new WildcardFileFilter();
	}

	

	public List scan()
	{
		String path;
		return null;
	}
}
