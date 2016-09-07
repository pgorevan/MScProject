package graph;

public class Edge {
	
	private Vertex v1;
	private Vertex v2;

	private int geneIndex;
	
	public Edge(Vertex one, Vertex two){
		v1 = one;
		v2 = two;

	}
	
	public Edge(Vertex one, Vertex two, int i)
	{
		v1 = one;
		v2 = two;
		geneIndex = i;
	}
	
	public Vertex getVertexOne(){
		return v1;
	}
	public Vertex getVertexTwo(){
		return v2;
	}
	public int getLabel()
	{
		return geneIndex;
	}
	
	
	public boolean equals(Edge e)
	{
		if(this.v1.equals(e.v1)&&this.v2.equals(e.v2))
			return true;
		return false;
		
	}

}
