//Created by Fabian on 22.05.15.
package model;

import statics.Statics;

import java.util.*;

public class Graph {

    public Vector<Node> mNodes = new Vector<>();
    public Vector<Edge> mEdges = new Vector<>();
    public int mSortMode = Statics.SORT_CODE_ALPHABETICAL;
    public void setSortMode(int sortCode){mSortMode = sortCode;}

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
                if(mEdges.elementAt(i).a.getContent().equals(bar)||
                    mEdges.elementAt(i).b.getContent().equals(bar)){
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
    public Vector<Bar> getAllBars() {
        Vector<Bar> result = new Vector<>();
        for(int i=0;i<mNodes.size();i++){
            result.add((Bar)mNodes.elementAt(i).getContent());
        }
        return result;
    }
    public Vector<Bar> getBarsFilteredByStringSearch(String search){
        Vector<Bar> result = new Vector<>();
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
        Vector<Bar> result = new Vector<>();
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

    public Vector<Bar> getBarsFiltered(int[] filters){
        Vector<Bar> bars = sortListBy(getAllBars());
        Vector<Bar> result = new Vector<>();
        for(int i=0;i<bars.size();i++){
            if(bars.elementAt(i).getmCategory() == filters[0] || filters[0] == 0){

                double price = bars.elementAt(i).getmPrice();
                int priceRange = filters[3];

                if  ((priceRange == 0) ||
                    (priceRange == 1 && price == 0.0) ||
                    (priceRange == 2 && price > 0 && price <= 5) ||
                    (priceRange == 3 && price > 5 && price <= 10) ||
                    (priceRange == 4 && price > 10)) {

                    int ageRestriction = bars.elementAt(i).getmAgeRestriction();

                    if((filters[1] == 0) ||
                         (filters[1] == 1 && ageRestriction == 16) ||
                         (filters[1] == 2 && ageRestriction == 18) ||
                         (filters[1] == 3 && ageRestriction == 0)) {

                        int avgAge = bars.elementAt(i).getmAverageAge();

                        if((filters[2] == 0) ||
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
    public Vector<Bar> sortListBy(Vector<Bar> list){
        Bar buffer = null;
        Vector<Bar> result = new Vector<>();
        if(mSortMode == Statics.SORT_CODE_ALPHABETICAL) {
            while(!(list.isEmpty())){
                buffer = list.firstElement();
                for(int i=0;i< list.size();i++){
                    if((list.elementAt(i).getmName().compareTo(buffer.getmName())<0)){
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        }
        else if(mSortMode == Statics.SORT_CODE_PRICE){
            while(!(list.isEmpty())){
                buffer = list.firstElement();
                for(int i=0;i< list.size();i++){
                    if(list.elementAt(i).getmPrice()<buffer.getmPrice()){
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        }
        else if(mSortMode == Statics.SORT_CODE_POPULAR){
            while(!(list.isEmpty())){
                buffer = list.firstElement();
                for(int i=0;i< list.size();i++){
                    if(list.elementAt(i).getPopularity()>buffer.getPopularity()){
                        buffer = list.elementAt(i);
                    }
                }
                list.remove(buffer);
                result.add(buffer);
            }
        }
        return result;
    }
}

