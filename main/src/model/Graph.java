//Created by Fabian on 22.05.15.
package model;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class Graph {

    public Vector<Node> mNodes = new Vector<Node>();
    public Vector<Edge> mEdges = new Vector<Edge>();

    public double[][] getAdjacencyMatrix() {
        double[][] adjMatrix = new double[mNodes.size()][mNodes.size()];

        for(int i = 0; i < mNodes.size(); i++)
            for(int j = 0; j < mNodes.size(); j++)
                if(i == j)
                    adjMatrix[i][j] = 0;
                else
                    adjMatrix[i][j] = Double.POSITIVE_INFINITY;

        for(int i = 0; i < mNodes.size(); i++) {
            Node node = mNodes.elementAt(i);

            for(int j = 0; j < mEdges.size(); j++) {
                Edge edge = mEdges.elementAt(j);

                if(edge.a == node) {
                    int indexOfNeighbor = mNodes.indexOf(edge.b);
                    adjMatrix[i][indexOfNeighbor] = edge.weight;
                }
            }
        }
        return adjMatrix;
    }

    public int indexOf(Node a) {
        for(int i = 0; i < mNodes.size(); i++)
            if(mNodes.elementAt(i).getContent().equals(a.getContent()))
                return i;
        return -1;
    }

    public Vector<Node> getNodes() {
        return mNodes;
    }
    public Vector<Edge> getEdges() {
        return mEdges;
    }
    public Node getNodeAt(int i) {
        return mNodes.elementAt(i);
    }

    public int addNode(Node a) {
        mNodes.add(a);

        return mNodes.size() - 1;
    }
    public void removeNode(Bar bar){
        Node node = new Node(bar);
        int index = indexOf(node);
        if(index >= 0){
            for(int i=0;i<mEdges.size();i++){
                if(((Bar)mEdges.elementAt(i).a.getContent()).equals(bar)||
                    ((Bar)mEdges.elementAt(i).b.getContent()).equals(bar)){
                    mEdges.remove(i);
                }
            }
            mNodes.remove(index);
        }
    }
    public void addEdge(Edge edge) {
        mEdges.add(edge);
    }

    public void printNodes() {
        System.out.println(mNodes);
    }
    public void printEdges() {
        System.out.println(mEdges);
    }

    public Vector<Node> getAllNodes(){
        return mNodes;
    }
    public Vector<Bar> getAllBars() {
        Vector<Bar> result = new Vector<Bar>();
        for(int i=0;i<mNodes.size();i++){
            result.add((Bar)mNodes.elementAt(i).getContent());
        }
        return result;
    }
    public Vector<Bar> getBarsFilteredByStringSearch(String search){
        Vector<Bar> result = new Vector<Bar>();
        for (int i=0; i<mNodes.size(); i++){
            Bar bar = (Bar)mNodes.elementAt(i).getContent();
            if((bar.getmName().contains(search))||
                    bar.getmDescription().contains(search)||
                    bar.getmAdress().contains(search)||
                    bar.getmUrl().contains(search)){

                result.add((Bar)mNodes.elementAt(i).getContent());
            }
        }
        return result;
    }
    public Vector<Bar> getBarsFilteredByNumericalSearch(double search){
        Vector<Bar> result = new Vector<Bar>();
        for (int i=0; i<mNodes.size(); i++){
            Bar bar = (Bar)mNodes.elementAt(i).getContent();
            if(((bar.getmPrice()==search)||
                    (double)bar.getmAgeRestriction()==search||
                    (double)bar.getmAverageAge()==search)){

                result.add((Bar)mNodes.elementAt(i).getContent());
            }
        }
        return result;
    }
}

