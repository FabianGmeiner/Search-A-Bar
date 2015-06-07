package projekt.genUtil.list;
import java.io.*;

import projekt.genUtil.list.*;

/* written by Daniel Knuettel 2015 */


class Tail 
extends AbstractNode
{
	public AbstractNode insert(Object _obj)
	{
		return  new Node(this,_obj);
	}

	public AbstractNode remove()
	{
		return this;
	}
	public Object getContent()
	{
		return null;
	}
}
