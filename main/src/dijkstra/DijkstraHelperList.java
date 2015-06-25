package dijkstra;

import model.Bar;
import model.Edge;
import model.Node;

/**
 * Created by knuettel.daniel on 16.06.2015.
 */
public class DijkstraHelperList
{
    protected DijkstraHelperAbstractNode first;

    public DijkstraHelperList()
    {
        first=new DijkstraHelperTailNode();
    }
    DijkstraHelperNode getNodeByName(String name)
    {
        return first.getNodeByName(name);
    }
    DijkstraHelperNode getNodeAt(int where)
    {
        return first.getNodeAt(where,0);
    }
    DijkstraHelperAbstractNode getNodes()
    {
        return first;
    }

    void insert(Bar br, DijkstraHelperEdge[] ed)
    {
        first=first.insert(br,ed);
    }
    void dijkstraInit()
    {
        first.dijkstraInit();
    }


}
