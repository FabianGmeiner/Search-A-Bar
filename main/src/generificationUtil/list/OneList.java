package generificationUtil.list;

import generificationUtil.logger.Logger;


/**
 * The OneList is a special List, that contains only one Element,
 * it may be used for unbuffered stuff<br /><br />
 * written by Daniel Knuettel
 */
public class OneList
        extends List {
    private boolean isFull = false;

    public OneList() {
        super();
    }

    public void insert(Object _obj) {
        if (!isFull) {
            first = first.insert(_obj);
            isFull = true;
            Logger.log(Logger.MSG, "OneList:insert(): filled now\n");
        } else {
            Logger.log(Logger.ERROR, "OneList:insert():ERROR: list is full");
        }
    }

    public void insert_stack(Object _obj) {
        if (!isFull) {
            first = new Node(first, _obj);
            isFull = true;
            Logger.log(Logger.MSG, "OneList:insert(): filled now\n");
        } else {
            Logger.log(Logger.ERROR, "Onelist:insert_stack():ERROR: list is full");
        }
    }

    public Object remove() {
        Object swp = first;
        if (swp == null) {
            Logger.log(Logger.WARN, "Onelist:remove():returning 'null'\n");
        }
        first = first.remove();
        isFull = false;
        return first.getContent();
    }


}
