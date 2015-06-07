package ftpUtil;

import genUtil.list.*;
import ftpUtil.*;

public class FtpQuery
{
	private boolean _needLogin=false;
	private String servername;
	private String username;
	private String passwd;
	private boolean _upload=false;

	private List files;

	public FtpQuery(String [] _files,String servername,boolean upload)
	{
		this.servername=servername;
		files=new List();
		_upload=upload;
		for (int i=0;i<_files.length;i++)
		{
			files.insert(_files[i]);
		}
	}

	public FtpQuery(List _files, String servername,boolean upload)
	{
		this.servername=servername;
		this.files=_files;
		_upload=upload;
	}

	public FtpQuery(String [] _files,String servername, String username, String passwd,boolean upload)
	{

		this.servername=servername;
		this.username=username;
		this.passwd=passwd;
		_needLogin=true;
		_upload=upload;

		files=new List();
		for (int i=0;i<_files.length;i++)
		{
			files.insert(_files[i]);
		}
	}
	public FtpQuery(List _files,String servername, String username, String passwd,boolean upload)
	{
		
		this.servername=servername;
		_upload=upload;
		this.username=username;
		this.passwd=passwd;
		_needLogin=true;
		this.files=_files;
	}
	public List toDataSections()
	{
		String filename=(String)files.remove();
		List ret=new List();

		while(filename!=null)
		{
			if(_needLogin)
			{
				ret.insert(new FtpData(filename,servername,username,passwd));
			}
			else
			{
				ret.insert(new FtpData(filename,servername));
			}
			filename=(String)files.remove();
		}
		return ret;
	}
	public boolean isUpload()
	{
		return  _upload;
	}
}
