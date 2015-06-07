package projekt.ftpUtil;

public class FtpData
{

	private boolean _needLogin=false;
	private String filename;
	private String servername;
	private String username;
	private String passwd;
	public FtpData(String filename,String servername)
	{
		this.filename=filename;
		this.servername=servername;

	}
	public FtpData(String filename,String servername, String username, String passwd)
	{
		this.filename=filename;
		this.servername=servername;
		this.username=username;
		this.passwd=passwd;
		_needLogin=true;
	}

	public String getFileName()
	{
		return filename;
	}
	public boolean needLogin()
	{
		return _needLogin;
	}
	public String getServerName()
	{
		return servername;
	}
	public String getUserName()
	{
		return username;
	}
	public String getUserPasswd()
	{
		return passwd;
	}


}
