package projekt.genUtil.list;
import java.io.*;

public class ListFullException
extends Exception
{
	private String stdmsg="List is already full.";

	public ListFullException()
	{
		super("List is already full.");
	}

}
