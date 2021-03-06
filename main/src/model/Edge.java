//Created by Fabian Gmeiner on 22.05.15.

package model;

import java.io.Serializable;

// instead of using an adjacency-matrix with a double[][] matrix, we use Vectors and 'Edge'-Objects
// to allow dynamic extension of the Graph

public class Edge implements Serializable {

    protected Node a, b;
    protected double weight;

    public Edge(Node a, Node b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    @SuppressWarnings("unused")
    public double getWeight() {
        return weight;
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }
}
