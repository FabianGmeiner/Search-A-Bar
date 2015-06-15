package generificationUtil.list;

/* written by Daniel Knuettel 2015 */


class Tail
        extends AbstractNode {
    public AbstractNode insert(Object _obj) {
        return new Node(this, _obj);
    }

    public AbstractNode remove() {
        return this;
    }

    public Object getContent() {
        return null;
    }

    public Object getEleAt(int place, int current) {
        return null;
    }
}
