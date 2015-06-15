package generificationUtil.list;

import generificationUtil.logger.Logger;


/**
 * Another List, but now it is to store Objects, eg for serialization
 * written by Daniel Knuettel
 */

public class ListStore
        extends List {
    private String name;
    private int count = 0;
    private int max = -1; // -1 = oo

    public ListStore(String name) {
        super();
        this.name = name;
    }

    public ListStore(String name, int max) {
        super();
        this.name = name;
        this.max = max;
    }

    public ListStore(String name, Object[] objs) {
        super();
        this.name = name;
        append(objs);

    }

    public ListStore(String name, int max, Object[] objs) {
        super();
        this.name = name;
        this.max = max;
        append(objs);

    }

    public void append(Object[] objs) {
        int len;
        try {
            len = objs.length;
        } catch (Exception e) {
            Logger.log(Logger.ERROR, "ListStore (" + name + " ): append(Object[]): array must have fixed length\n");
            System.err.println("ListStore: append(Object[]):ERROR");
            return;
        }
        for (int i = 0; i < len; i++) {
            insert(objs[i]);
        }
    }

    public void insert(Object _obj) {
        if (max != -1) {
            if (count >= max) {
                Logger.log(Logger.ERROR, "ListStore (" + name + "): insert: already filled up with " + count + " elements\n");
                return;
            }
        }
        first = first.insert(_obj);
    }

    public Object remove() {
        Object swp = first.getContent();
        first = first.remove();
        if (count > 0) {
            count--;
        }
        return swp;

    }

    public String getName() {
        return name;
    }

    public ListStore duplicate() {
        ListStore ret = new ListStore(name);
        ret.first = this.first;
        return ret;
    }

    public Object getObjectAt(int place) {
        /*starting with 0*/
        return first.getEleAt(place, 0);
    }


}
