package projekt.genUtil.list;
import java.io.*;

import projekt.genUtil.list.*;
import projekt.genUtil.logger.*;


/**The OneList is a special List, that contains only one Element,
 it may be used for unbuffered stuff<br /><br />
 written by Daniel Knuettel*/
public class OneList
extends List
{
	private boolean isFull=false;

	public OneList()
	{
		super();
	}
	public void insert(Object _obj)
	{
		if(!isFull)
		{
			first=first.insert(_obj);
			isFull=true;
		}
		else
		{
			Logger.log(Logger.ERROR,"Onelist:insert():ERROR: list is full");
		}
	}
	public void insert_stack(Object _obj)
	{
		if(!isFull)
		{
			first=new Node(first,_obj);
			isFull=true;
		}
		else
		{
			Logger.log(Logger.ERROR,"Onelist:insert_stack():ERROR: list is full");
		}
	}
	public Object remove()
	{
		Object swp=first;
		first=first.remove();
		isFull=false;
		return first.getContent();
	}


}
