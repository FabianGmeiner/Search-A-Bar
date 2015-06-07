package generificationUtil.stack;

import generificationUtil.list.List;

public class Stack
        extends List {
    public Stack() {
        super();

    }

    //OVERWRITE

    /**
     * Just an overwrite from the inherited insert to
     * the also inherited insert_stack
     */
    public void insert(Object _obj) {
        insert_stack(_obj);
    }

    /**
     * Just the same as insert().
     */
    public void push(Object _obj) {
        insert_stack(_obj);
    }

    /**
     * An Overwrite from List.remove().
     */
    public Object pop() {
        return remove();
    }
}
