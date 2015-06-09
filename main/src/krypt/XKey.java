package krypt;

import generificationUtil.list.*;
import generificationUtil.logger.*;
import java.io.*;
public class XKey
implements Serializable
{
	private ListStore keys;

	public XKey(byte [] masks, byte [] speeds)
	{
		keys=new ListStore("Keys");
		try
		{
			if(masks.length!=8)
			{
				Logger.log(Logger.ERROR,"new XKey(): masks.length is not 8\n");
				return;
			}
			if(speeds.length!=8)
			{
				Logger.log(Logger.ERROR,"new XKey(): speeds.length is not 8\n");
				return;
			}
		}
		catch (Exception e)
		{
			Logger.log(Logger.ERROR,e.toString()+"\n");
		}
		byte [][] key=new byte[2][8];
		key[0]=masks;
		key[1]=speeds;
		keys.insert(key);
	}
	public static byte increment(byte toIncrement,byte speed)
	{
		if((toIncrement+speed)>7)
		{
			return (byte)((toIncrement+speed)-7);
		}
		return (byte)(toIncrement+speed);

	}
	public ListStore getKeys()
	{
		return keys.duplicate();
	}
	void addKey(byte[] masks, byte [] speeds)
	{
		byte [][] key=new byte[2][8];
		key[0]=masks;
		key[1]=speeds;
		keys.insert(key);

	}

}
