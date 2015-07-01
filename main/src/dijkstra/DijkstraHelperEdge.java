package dijkstra;

/**
 * Created by knuettel.daniel on 17.06.2015.
 */
public class DijkstraHelperEdge {
    protected DijkstraHelperNode a, b;
    protected double weight;

    public DijkstraHelperEdge(DijkstraHelperNode a, DijkstraHelperNode b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public DijkstraHelperNode getA() {
        return a;
    }

    public DijkstraHelperNode getB() {
        return b;
    }

    public double getWeight() {
        return weight;
    }

}
