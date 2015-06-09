package krypt;
/** Written by Daniel Knuettel*/

import generificationUtil.list.*;
import generificationUtil.logger.*;

public class XKeys
{
	public static XKey getKey(byte[] masks, byte[] speeds)
	{
		return new XKey(masks,speeds);
	}
	public static XKey getKey(byte[][] manymasks,byte[][] manyspeeds)
	{
		XKey res=new XKey(manymasks[0],manyspeeds[0]);
		for(int i=1;i<manymasks.length&&i<manyspeeds.length;i++)
		{
			res.addKey(manymasks[i],manyspeeds[i]);
		}
		return res;
	}
}
