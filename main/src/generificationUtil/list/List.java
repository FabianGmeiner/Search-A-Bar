package generificationUtil.list;

import java.io.Serializable;

/**
 * Basic List Object to store Objects.
 * written by Daniel Knuettel 2015
 */

public class List
        implements Serializable {
    protected AbstractNode first;

    public List() {
        first = new Tail();
    }

    public void insert_stack(Object _obj) {
        first = new Node(first, _obj);
    }

    public void insert(Object _obj) {
        first = first.insert(_obj);
    }

    public Object remove() {
        Object swp = first.getContent();
		if(Statics.__DEBUG)
		{
			Logger.log(Logger.DEBUG,"List:removing one object\n");
		}
		if(swp==null)
		{
			Logger.log(Logger.WARN,"List:remove(): returning 'null'\n");
		}
        first = first.remove();
        return swp;
    }
}

