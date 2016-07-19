package graph;

import java.util.Iterator;

public interface Graph<E,A> {
	
	//Returns number of vertices
	public int size();
	public int degree(Vertex v);
	public boolean containsEdge(Vertex v1, Vertex v2);
	
	public void clear();
	public void addVertex(Vertex V);
	public Edge addEdge(Vertex v1, Vertex v2);
	public Edge addEdge(Vertex v1, Vertex v2, A attr);
	public void removeVertex(Vertex v);
	public void removeEdge(Edge e);
	public Iterator<Vertex> vertices();
	public Iterator<Edge> edges();
	public Iterator<Vertex> neighbours(Vertex v);
	public Iterator<Edge> connectingEdges(Vertex v);
	
	
	
	
	
	
	
	
	
	

}
