//Created by Fabian Gmeiner on 22.05.15.

package model;

import statics.Statics;
import utils.GPSService;

import java.io.Serializable;
import java.util.Vector;

//This graph is based on Vectors for the list of nodes and edges,
//thus allowing a dynamic size

public class Graph implements Serializable {

    public Vector<Node> mNodes = new Vector<>();
    public Vector<Edge> mEdges = new Vector<>();

    // code used to determine how the list should be sorted
    public int mSortMode = Statics.SORT_CODE_ALPHABETICAL;
    // list of all the bars in the graph
    public Vector<Bar> mBars = new Vector<>();
    // the result of the depth-search
    public Vector<Bar> mSearchResult = new Vector<>();
    // indicating whether or not the destination is reached
    private boolean mDone = false;
    // result of getAdjacenyMatrix()-method
    private double[][] mAdjazencyMatrix = null;

    // method to set the sortMode according to user preferences
    public void setSortMode(int sortCode) {
        mSortMode = sortCode;
    }

    // method that creates an adjacency-matrix
    public double[][] getAdjacencyMatrix() {
        double[][] adjMatrix = new double[mNodes.size()][mNodes.size()];

        for (int i = 0; i < mNodes.size(); i++)
            for (int j = 0; j < mNodes.size(); j++)
                if (i == j)
                    adjMatrix[i][j] = 0;
                else
                    adjMatrix[i][j] = Double.POSITIVE_INFINITY;

        for (int i = 0; i < mNodes.size(); i++) {
            Node node = mNodes.elementAt(i);

            for (int j = 0; j < mEdges.size(); j++) {
                Edge edge = mEdges.elementAt(j);

                if (edge.a == node) {
                    int indexOfNeighbor = mNodes.indexOf(edge.b);
                    adjMatrix[i][indexOfNeighbor] = edge.weight;
                }
            }
        }
        mAdjazencyMatrix = adjMatrix;
        return adjMatrix;
    }

    // method that return the index of an element (if present)
    public int indexOf(Node a) {
        for (int i = 0; i < mNodes.size(); i++)
            if (mNodes.elementAt(i).getContent().equals(a.getContent()))
                return i;
        return -1;
    }

    // method that returns the node based on its index
    public Node getNodeAt(int i) {
        return mNodes.elementAt(i);
    }

    // method to add a node; automatically adds an edge to the closest bar
    public int addNode(Node a) {
        if (!mNodes.isEmpty()) {
            Bar barToAdd = (Bar) a.getContent();
            Bar nearest = (Bar) mNodes.firstElement().getContent();
            Node nearestNode = mNodes.firstElement();
            for (int i = 0; i < mNodes.size(); i++) {
                Bar next = (Bar) mNodes.elementAt(i).getContent();
                if (GPSService.getDistanceFromGPS(
                        next.getmGpsLatitude(),
                        next.getmGpsLongitude(),
                        barToAdd.getmGpsLatitude(),
                        barToAdd.getmGpsLongitude()) <=
                        GPSService.getDistanceFromGPS(
                                nearest.getmGpsLatitude(),
                                nearest.getmGpsLongitude(),
                                barToAdd.getmGpsLatitude(),
                                barToAdd.getmGpsLongitude())) {
                    nearest = next;
                    nearestNode = mNodes.elementAt(i);
                }
            }
            mEdges.add(new Edge(a, nearestNode, GPSService.getDistanceFromGPS(
                    nearest.getmGpsLatitude(),
                    nearest.getmGpsLongitude(),
                    barToAdd.getmGpsLatitude(),
                    barToAdd.getmGpsLongitude())));
        }
        mNodes.add(a);

        return mNodes.size() - 1;
    }

    // method to remove a certain bar
    public void removeNode(Bar bar) {
        Node node = new Node(bar);
        int index = indexOf(node);
        if (index >= 0) {
            for (int i = 0; i < mEdges.size(); i++) {
                if (mEdges.elementAt(i).a.getContent().equals(bar) ||
                        mEdges.elementAt(i).b.getContent().equals(bar)) {
                    mEdges.remove(i);
                }
            }
            mNodes.remove(index);
        }
    }

    // methods used to print the contents of the graph -> for testing
    public void printNodes() {
        System.out.println(mNodes);
    }
    public void printEdges() {
        System.out.println(mEdges);
    }

    // returns all bars currently contained in the graph
    public Vector<Bar> getAllBars() {
        Vector<Bar> result = new Vector<>();
        for (int i = 0; i < mNodes.size(); i++) {
            result.add((Bar) mNodes.elementAt(i).getContent());
        }
        return result;
    }

    // returns all edges
    public Vector<Edge> getAllEdges() {
        Vector<Edge> res = new Vector<>();
        for (int i = 0; i < mEdges.size(); i++) {
            res.add((Edge) mEdges.elementAt(i));
        }
        return res;
    }

    // returns all nodes
    public Vector<Node> getAllNodes() {
        Vector<Node> result = new Vector<>();
        for (int i = 0; i < mNodes.size(); i++) {
            result.add((Node) mNodes.elementAt(i));
        }
        return result;

    }

    // methods to react tu user input in the search-field
    public Vector<Bar> getBarsFilteredByStringSearch(String search) {
        Vector<Bar> result = new Vector<>();
        for (int i = 0; i < mNodes.size(); i++) {
            Bar bar = (Bar) mNodes.elementAt(i).getContent();
            if ((bar.getmName().contains(search)) ||
                    bar.getmDescription().contains(search) ||
                    bar.getmAdress().contains(search) ||
                    bar.getmUrl().contains(search)) {

                result.add((Bar) mNodes.elementAt(i).getContent());
            }
        }
        return result;
    }
    public Vector<Bar> getBarsFilteredByNumericalSearch(double search) {
        Vector<Bar> result = new Vector<>();
        for (int i = 0; i < mNodes.size(); i++) {
            Bar bar = (Bar) mNodes.elementAt(i).getContent();
            if (((bar.getmPrice() == search) ||
                    (double) bar.getmAgeRestriction() == search ||
                    (double) bar.getmAverageAge() == search)) {

                result.add((Bar) mNodes.elementAt(i).getContent());
            }
        }
        return result;
    }

    // method that returns a list of bars based on the filters chosen by the user
    public Vector<Bar> getBarsFiltered(int[] filters) {
        Vector<Bar> bars = sortListBy(getAllBars());
        Vector<Bar> result = new Vector<>();
        for (int i = 0; i < bars.size(); i++) {
            if (bars.elementAt(i).getmCategory() == filters[0] || filters[0] == 0) {

                double price = bars.elementAt(i).getmPrice();
                int priceRange = filters[3];

                if ((priceRange == 0) ||
                        (priceRange == 1 && price == 0.0) ||
                        (priceRange == 2 && price > 0 && price <= 5) ||
                        (priceRange == 3 && price > 5 && price <= 10) ||
                        (priceRange == 4 && price > 10)) {

                    int ageRestriction = bars.elementAt(i).getmAgeRestriction();

                    if ((filters[1] == 0) ||
                            (filters[1] == 1 && ageRestriction == 16) ||
                            (filters[1] == 2 && ageRestriction == 18) ||
                            (filters[1] == 3 && ageRestriction == 0)) {

                        int avgAge = bars.elementAt(i).getmAverageAge();

                        if ((filters[2] == 0) ||
                                (filters[2] == 1 && avgAge < 18) ||
                                (filters[2] == 2 && avgAge >= 18 && avgAge < 25) ||
                                (filters[2] == 3 && avgAge >= 25 && avgAge < 30) ||
                                (filters[2] == 4 && avgAge >= 30 && avgAge <= 40) ||
                                (filters[2] == 5 && avgAge > 40)) {

                            result.add(bars.elementAt(i));

                        }

                    }
                }
            }
        }
        return result;
    }

    // method that returns a list of bars sorted by the sortMode the user chose
    public Vector<Bar> sortListBy(Vector<Bar> list) {
        Bar buffer;
        Vector<Bar> result = new Vector<>();
        if (mSortMode == Statics.SORT_CODE_ALPHABETICAL) {
            while (!(list.isEmpty())) {
                buffer = list.firstElement();
                for (int i = 0; i < list.size(); i++) {
                    if ((list.elementAt(i).getmName().compareTo(buffer.getmName()) < 0)) {
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        } else if (mSortMode == Statics.SORT_CODE_PRICE) {
            while (!(list.isEmpty())) {
                buffer = list.firstElement();
                for (int i = 0; i < list.size(); i++) {
                    if (list.elementAt(i).getmPrice() < buffer.getmPrice()) {
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        } else if (mSortMode == Statics.SORT_CODE_POPULAR) {
            while (!(list.isEmpty())) {
                buffer = list.firstElement();
                for (int i = 0; i < list.size(); i++) {
                    if (list.elementAt(i).getPopularity() > buffer.getPopularity()) {
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        }
        return result;
    }

    // methods to perform a depth-search through the graph in order to calculate a route/tour
    public Vector<Bar> depthSearch(Bar start) {
        mNodes.elementAt(mBars.indexOf(start)).setCheckmark(true);
        mSearchResult.add(start);
        for (int i = 0; i < mNodes.size(); i++) {
            if ((mAdjazencyMatrix[mBars.indexOf(start)][i] != Double.POSITIVE_INFINITY
                    || mAdjazencyMatrix[i][mBars.indexOf(start)] != Double.POSITIVE_INFINITY)
                    && !mNodes.elementAt(i).getCheckmark()) {
                depthSearch(mBars.elementAt(i));
            }
        }
        mSearchResult.add(start);
        return mSearchResult;
    }
    public Vector<Bar> depthSearchDestination(Bar start, Bar end) {
        mNodes.elementAt(mBars.indexOf(start)).setCheckmark(true);
        mSearchResult.add(start);
        if (!start.equals(end)) {
            for (int i = 0; i < mNodes.size(); i++) {
                if ((mAdjazencyMatrix[mBars.indexOf(start)][i] != Double.POSITIVE_INFINITY
                        || mAdjazencyMatrix[i][mBars.indexOf(start)] != Double.POSITIVE_INFINITY)
                        && !mNodes.elementAt(i).getCheckmark()) {
                    if (!mDone) {
                        depthSearchDestination(mBars.elementAt(i), end);
                    }
                }
            }
        } else {
            mDone = true;
        }
        mSearchResult.add(start);
        return mSearchResult;
    }

    // method to initialize all the nodes
    public void initialiseGraph() {
        mDone = false;
        mSearchResult = new Vector<>();
        for (int i = 0; i < mNodes.size(); i++) {
            mNodes.elementAt(i).initialiseDepthSearch();
        }
        mAdjazencyMatrix = getAdjacencyMatrix();
        mBars = getAllBars();
    }

}

