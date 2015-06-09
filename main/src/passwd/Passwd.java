package passwd;
import krypt.*;
import java.io.*;
/**
 written by Daniel Knuettel 
TODO:test
 status: UNTESTED
 */
public class Passwd
implements Serializable
{
	private String uname;
	private byte [] passwd;
	XKey key;
	
	public Passwd(String _uname,String _passwd)
	{
		uname=_uname;
		byte [] unkr=_passwd.getBytes();
		byte [] name_in_bytes=uname.getBytes();
		
		byte [] speeds=new byte[8];
		byte [] masks= new byte[8];
		for(int i=0;i<8;i++)
		{
			speeds[i]=(byte)((Math.random()*4)+1);
		}
		if(name_in_bytes.length>=8)
		{
			for(int i=0;i<8;i++)
			{
				masks[i]=(byte)(name_in_bytes[i]^(1<<(byte)((Math.random()*4)+1)));
			}
		}
		else
		{
			for(int i=0;i<8;i++)
			{
				masks[i]=(byte)((Math.random()*4)+1);
			}
		}
		key=XKeys.getKey(masks,speeds);
		XKrypter k=new XKrypter(key);
		passwd=k.kryptBytes(unkr);
	}
	public String getName()
	{
		return uname;
	}
	public String getPasswd()
	{
		XKrypter k=new XKrypter(key);
		byte [] pswd=k.deKryptBytes(passwd);
		return new String(pswd);
	}
}
