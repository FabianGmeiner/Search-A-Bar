package passwd;
import krypt.*;
import java.io.*;
import generificationUtil.serializer.*;
import generificationUtil.list.*;
import generificationUtil.*;

/**written by Daniel Knuettel*/

public class PasswdStore
{
	private ListStore passwdList;
	private Serializer serializer;
	private Deserializer deserializer;

	public PasswdStore()
	{
		passwdList=new ListStore("passwd");
		serializer=new Serializer(PathFinder.getPrettyName("passwds.ser"));
		deserializer=new Deserializer(PathFinder.getPrettyName("passwds.ser"));

	}
	public void addPasswd(Passwd _pswd)
	{
		passwdList.insert(_pswd);
	}
	public void save()
	{
		serializer.addObject(passwdList);
		serializer.serialize();
	}
	public void load()
	{
		deserializer.readObjs();
		ListStore swp=(ListStore)deserializer.getObject();
		while(swp.getName().compareTo("passwd")!=0)
		{
			swp=(ListStore)deserializer.getObject();
		}
		passwdList=swp;
	}
	public Passwd getPasswd(String name)
	{
		/*cannot use a search function, because a ObjectStore does not support it :( */
		int i=0;
		Passwd ps=(Passwd)passwdList.getObjectAt(i);
		while(ps!=null)
		{
			if(ps.getName().compareTo(name)==0)
			{
				return ps;
			}
			i++;
			ps=(Passwd)passwdList.getObjectAt(i);
		}
		return null;
	}

}
