package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class DLLGraph implements Graph {
	
	private LinkedList<Vertex> vertices;
	private LinkedList<Edge> edges;
	private int size;
	
	public DLLGraph(){
		vertices = new LinkedList<Vertex>();
		edges = new LinkedList<Edge>();
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public int degree(Vertex v) {
		// TODO Auto-generated method stub
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
			return e;
	}

	@Override
	public Edge addEdge(Vertex v1, Vertex v2, Object attr) {
		UpdateFunction f = (UpdateFunction) attr;
		Edge e = new Edge(v1, v2, f);
		return null;
	}

	@Override
	public void removeVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(Edge e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator connectingEdges(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}

}
