package dijkstra;

import generificationUtil.logger.Logger;
import model.Edge;
import model.Graph;
import model.Node;
import model.*;

import java.util.Vector;

/**
 * Created by knuettel.daniel on 16.06.2015.
 */
public class DijkstraHelperGraph
{
    protected DijkstraHelperList nodeList;
    protected Vector<Bar> alreadyAdded=new Vector<>();
    public  DijkstraHelperGraph()
    {
        nodeList=new DijkstraHelperList();
    }
    public boolean build(Graph g)
    {
        /*
        Vector<Node> nd= g.getAllNodes();
        Vector<Edge> ed=g.getAllEdges();

        for(int i=0;i<nd.size();i++)
        {
            Node swp=(Node)nd.elementAt(i);
            DijkstraHelperNode dswp=new DijkstraHelperNode((Bar)swp.getContent(),new DijkstraHelperEdge[0],new DijkstraHelperTailNode());
            Vector <DijkstraHelperEdge> edswp=new Vector<>();
            for(int j=0;j<ed.size();j++)
            {
                Edge e=(Edge)ed.elementAt(j);
                if(e.getA().equals(swp))
                {
                    edswp.add(new DijkstraHelperEdge(dswp,new DijkstraHelperNode((Bar)e.getB().getContent(),new DijkstraHelperEdge[0],new DijkstraHelperTailNode()),e.getWeight()));
                }
                if(e.getB().equals(swp))
                {

                    edswp.add(new DijkstraHelperEdge(dswp,new DijkstraHelperNode((Bar)e.getA().getContent(),new DijkstraHelperEdge[0],new DijkstraHelperTailNode()),e.getWeight()));
                }
            }
            DijkstraHelperEdge []edgearr=new DijkstraHelperEdge[edswp.size()];
            for (int k=0;k<edgearr.length;k++)
            {
                edgearr[k]=edswp.elementAt(k);
            }
            nodeList.insert((Bar)swp.getContent(),edgearr);
        }
        */
        buildFromMatrix(g);
        return true;

    }
    public  void buildFromMatrix(Graph g)
    {
        Vector<Node> nd= g.getAllNodes();
        double[][] matrix=g.getAdjacencyMatrix();
        for(int i=0;i<matrix.length;i++)
        {
            DijkstraHelperNode node=recursiveBuildNodeFromMatrix(nd.elementAt(i),i,matrix,nd);
            nodeList.insert(node.getBar(),node.getEdges());
        }
    }
    protected DijkstraHelperEdge[] recursiveBuildFromMatrix(Node n,int pos,double[][]matrix,Vector<Node> nds)
    {
        if(alreadyAdded.contains(n.getContent()))
        {
            Logger.log(Logger.DEBUG,"recursiveBuildFromMatrix:  "+((Bar)n.getContent()).getmName()+" already done\n");
            return new DijkstraHelperEdge[0];
        }
        Logger.log(Logger.DEBUG,"recursiveBuildFromMatrix:  "+((Bar)n.getContent()).getmName()+"\n");

        alreadyAdded.add((Bar)n.getContent());
        Vector<DijkstraHelperEdge>edges=new Vector<>();
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[pos][i]!=Double.POSITIVE_INFINITY)
            {

                if(n.equals(nds.elementAt(i)))
                {
                    continue;
                }
                Logger.log(Logger.DEBUG,"recursiveBuildFromMatrix:  "
                        +((Bar)n.getContent()).getmName()+": adding edge to "+
                        ((Bar)nds.elementAt(i).getContent()).getmName()+"\n");

                nodeList.insert((Bar)n.getContent(),recursiveBuildFromMatrix(nds.elementAt(i), i, matrix, nds)
                                );
                DijkstraHelperNode swp=nodeList.getNodeByName(((Bar)n.getContent()).getmName());
                edges.add(new DijkstraHelperEdge(
                        swp,

                        recursiveBuildNodeFromMatrix(nds.elementAt(i),i,matrix,nds),matrix[pos][i]));
            }
        }
        DijkstraHelperEdge[] res=new DijkstraHelperEdge[edges.size()];
        for(int i=0;i<edges.size();i++)
        {
            res[i]=edges.elementAt(i);
        }
        Logger.log(Logger.DEBUG,"recursiveBuildFromMatrix: Bar: "+((Bar)n.getContent()).getmName()+" finished\n");

        return  res;
    }
    private DijkstraHelperNode recursiveBuildNodeFromMatrix(Node n,int pos,double[][]matrix,Vector<Node> nds)
    {
        DijkstraHelperEdge[] edges=recursiveBuildFromMatrix(n,pos,matrix,nds);
        DijkstraHelperNode nd=new DijkstraHelperNode((Bar)n.getContent(),edges,new DijkstraHelperTailNode());
        return nd;
    }
    public void dijkstraInit()
    {
        nodeList.dijkstraInit();
    }
    public double dijkstraSearch(String from,String to)
    {
        dijkstraInit();
        DijkstraHelperNode startnd=nodeList.getNodeByName(from);
        if(startnd==null)
        {
            Logger.log(Logger.ERROR,"Dijkstra: dijkstraSearch(): no Node "+from+" found\n");
            return -2;
        }
        DijkstraHelperNode finalnd=nodeList.getNodeByName(to);
        if(finalnd==null)
        {
            Logger.log(Logger.ERROR,"Dijkstra: dijkstraSearch(): no Node "+to+" found\n");
            return -2;
        }
        startnd.dijkstraSearch(0, finalnd);
        Logger.log(Logger.MSG,"DijkstraHelperGraph: dijkstraSearch(): weight= "+finalnd.getWeight()+"\n");
        return finalnd.getWeight();
    }
}
