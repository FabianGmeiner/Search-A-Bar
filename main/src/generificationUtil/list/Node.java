package generificationUtil.list;

/* A container for Composite Pattern
 written by Daniel Knuettel 2015*/

class Node
extends AbstractNode
{
	private AbstractNode next;
	private Object content;

	public Node(AbstractNode next,Object content)
	{
		this.next=next;
		this.content=content;
	}
	public AbstractNode insert(Object _obj)
	{
		next=next.insert(_obj);
		return this;
	}
	public AbstractNode remove()
	{
		return next;
	}
	public Object getContent()
	{
		return content;
	}
}
