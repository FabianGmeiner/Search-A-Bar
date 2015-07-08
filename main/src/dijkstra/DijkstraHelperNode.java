package dijkstra;

import generificationUtil.logger.Logger;
import model.Bar;

/**
 * Created by knuettel.daniel on 16.06.2015.
 */
class DijkstraHelperNode
    extends DijkstraHelperAbstractNode
{
    protected Bar myEle;
    protected DijkstraHelperAbstractNode next;
    protected DijkstraHelperEdge [] edges;
    protected double weight;
    protected boolean mark=false;
    public DijkstraHelperNode(Bar con,DijkstraHelperEdge [] ed,DijkstraHelperAbstractNode nex)
    {

        myEle=con;
        next=nex;
        edges=ed;
        weight=-1;
    }
    public boolean update(double newWeight)
    {

        if(newWeight<weight) {
            weight = newWeight;
            return true;
        }
        if(!mark)
        {
            mark=true;
            return true;
        }
        return false;
    }

    DijkstraHelperNode getNodeByName(String name)
    {
        if(myEle.getmName().equals(name))
        {
            return this;
        }
        return next.getNodeByName(name);
    }
    DijkstraHelperNode getNodeAt(int where,int current)
    {
        if(where==current) return this;
        return next.getNodeAt(where, current + 1);
    }


    void dijkstraInit()
    {
        weight=-1;
        mark=false;
    }
    double getWeight(){
        return weight;
    }

    void dijkstraSearch(double updateWeight,DijkstraHelperNode fin)
    {
        Logger.log(Logger.MSG,"Node with bar '"+myEle.getmName()+"' weight = "+weight+"\n");
        if(weight==-1)
        {
            weight=updateWeight;
        }
        if(!update(updateWeight))
        {
            Logger.log(Logger.MSG,"Node with bar '"+myEle.getmName()+"' weight = "+weight+" stopping\n");
            return;
        }
        Logger.log(Logger.MSG,"Node with bar '"+myEle.getmName()+"' weight = "+weight+"\n");
        if(this.equals(fin))
        {
            return;
        }
        for(int i=0;i<edges.length;i++)
        {
            Logger.log(Logger.DEBUG,"Node("+myEle.getmName()+"):dijkstraSearch(): next is "+edges[i].getB().getBar().getmName()+"\n");
            edges[i].getB().dijkstraSearch(weight+edges[i].getWeight(),fin);
        }
    }
    public DijkstraHelperNode insert(Bar br, DijkstraHelperEdge [] ed)
    {
        next=next.insert(br,ed);
        return this;
    }
    public Bar getBar()
    {
        return myEle;
    }
    public DijkstraHelperEdge[] getEdges()
    {
        return edges;
    }

}
