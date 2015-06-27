package dijkstra;

import model.Node;
import model.*;

/**
 * Created by knuettel.daniel on 16.06.2015.
 */
class DijkstraHelperTailNode
extends DijkstraHelperAbstractNode
{
    @java.lang.Override
    DijkstraHelperNode getNodeAt(int where, int current) {
        return null;
    }

    @java.lang.Override
    DijkstraHelperNode getNodeByName(String name) {
        return null;
    }

    @java.lang.Override
    DijkstraHelperAbstractNode insert(Bar br,DijkstraHelperEdge [] ed) {
        return new DijkstraHelperNode(br,ed,this);
    }

    void dijkstraInit(){}

    @java.lang.Override
    void dijkstraSearch(double updateWeight, DijkstraHelperNode fin) {
        return;
    }
}
