/**
 * This class models the undirected state space of an
 * asynchronous Boolean Network
 */

package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DLLGraph implements Graph {
	
	private LinkedList<Vertex> vertices;
	private LinkedList<Edge> edges;
	private String[] geneNames;
	private int size;
	
	public DLLGraph(){
		vertices = new LinkedList<Vertex>();
		edges = new LinkedList<Edge>();
		geneNames= null;
		size = 0;
	}
	
	public void setGeneNames(String[] names)
	{
		geneNames = names;
	}
	@Override
	public int size() {
		return size;
	}
	public String[] getGeneNames(){
		return geneNames;
	}

	@Override
	public int degree(Vertex v) {
		return 0;
	}

	@Override
	public boolean containsEdge(Vertex v1, Vertex v2) {
		Iterator<Edge> iterator = edges();
		while(iterator.hasNext())
		{
			Edge e = iterator.next();
			if(v1.equals(e.getVertexOne()) && v2.equals(e.getVertexTwo()))
					return true;
		}
		return false;
	}

	@Override
	public void clear() {
		vertices = null;
		edges = null;
		size = 0;
		
	}

	@Override
	public void addVertex(Vertex v) {
		vertices.add(v);
		size++;
	}

	@Override
	public Edge addEdge(Vertex v1, Vertex v2) {
			Edge e = null;
			if(!this.containsEdge(v1, v2))
			{	
				e = new Edge(v1, v2);
			edges.add(e);
			}
			v1.addNeighbour(e);
			v2.addNeighbour(e);
			return e;
	}

	@Override
	public Edge addEdge(Vertex v1, Vertex v2, Object attr) {
		int f = (int) attr;
		Edge e = new Edge(v1, v2, f);
		edges.add(e);
		return e;
	}

	@Override
	public void removeVertex(Vertex v) {
		ArrayList<Edge> neighbours = v.getNeighourhood();
		for(Edge e : neighbours)
		{
			 Vertex a = e.getVertexOne();
			 Vertex b = e.getVertexTwo();
			 a.removeNeighbour(e);
			 b.removeNeighbour(e);
			 edges.remove(e);
		}
		vertices.remove(v);
		
	}

	@Override
	public void removeEdge(Edge e) {
		Vertex a = e.getVertexOne();
		Vertex b = e.getVertexTwo();
		a.removeNeighbour(e);
		b.removeNeighbour(e);
		edges.remove(e);
		
	}

	@Override
	public Iterator<Vertex> vertices() {
		Iterator<Vertex> interator = vertices.iterator();
		return interator;
	}

	@Override
	public Iterator<Edge> edges() {
		Iterator<Edge> iterator = edges.iterator();
		return iterator;
	}

	@Override
	public Iterator neighbours(Vertex v) {
		ArrayList<Edge> edges = v.getNeighourhood();
		ArrayList<Vertex> neighbours = new ArrayList();
		for (Edge e : edges)
		{
			Vertex one = e.getVertexOne();
			Vertex two = e.getVertexTwo();
			if(!one.equals(v))
					neighbours.add(one);
			else
				neighbours.add(two);
		}
		
		return neighbours.iterator();
	}

	@Override
	public Iterator connectingEdges(Vertex v) {
		return v.getNeighourhood().iterator();
	}
	
	public void createEdges()
	{
		for (int i= 0; i<geneNames.length; i++)
		{
			Iterator<Vertex>  iter = this.vertices();
			while(iter.hasNext())
			{
				Vertex v = iter.next();
				boolean[] currentState = v.getGeneExpresssion();
				Iterator<Vertex>  iter2 = this.vertices();
				while(iter2.hasNext())
				{
					Vertex v2 = iter2.next();
					boolean[] nextState = v2.getGeneExpresssion();
					if(currentState[i]!=nextState[i] &&v.diffenceIsOne(v2)&&!this.containsEdge(v, v2))
					{

						this.addEdge(v, v2, i);
					}
					
					
				}
				
			}
		}
	}

}
