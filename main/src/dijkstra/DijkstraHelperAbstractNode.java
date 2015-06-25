package dijkstra;

/**
 * Created by knuettel.daniel on 16.06.2015.
 */

import model.Node;
import model.*;

abstract class DijkstraHelperAbstractNode
{
    abstract DijkstraHelperNode getNodeByName(String name);
    abstract DijkstraHelperNode getNodeAt(int where,int current);
    abstract DijkstraHelperAbstractNode insert(Bar br, DijkstraHelperEdge[] ed);
    abstract  void dijkstraInit();
    abstract void dijkstraSearch(double updateWeight,DijkstraHelperNode fin);

}
