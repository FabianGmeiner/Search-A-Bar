package generificationUtil.list;

import java.io.Serializable;

/* The abstract container for Composite Pattern
 written by Daniel Knuettel 2015*/

abstract class AbstractNode
        implements Serializable {
    /* For FIFO */
    public abstract AbstractNode insert(Object _obj);

    public abstract AbstractNode remove();

    public abstract Object getContent();

    public abstract Object getEleAt(int place, int current);

}

