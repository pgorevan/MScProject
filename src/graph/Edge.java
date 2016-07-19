package graph;

public class Edge {
	
	private Vertex v1;
	private Vertex v2;
	private UpdateFunction function;
	
	public Edge(Vertex one, Vertex two){
		v1 = one;
		v2 = two;
		function = null;
	}
	
	public Edge(Vertex one, Vertex two, UpdateFunction f)
	{
		v1 = one;
		v2 = two;
		function = f;
	}
	
	public Vertex getVertexOne(){
		return v1;
	}
	public Vertex getVertexTwo(){
		return v2;
	}
	
	
	public boolean equals(Edge e)
	{
		if(this.v1.equals(e.v1)&&this.v2.equals(e.v2))
			return true;
		return false;
		
	}

}
