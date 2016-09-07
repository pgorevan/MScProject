package graph;

import java.util.ArrayList;

public class Vertex {
	private int stateid;
	private int time;
	private boolean[] geneExpressions;
	private ArrayList<Edge> neighbourhood; 
	
	public Vertex(int id, int timeStamp, boolean[] boolArray){
		stateid = id;
		time = timeStamp;
		geneExpressions = boolArray;
		neighbourhood = new ArrayList<Edge>();
		
	}
	
	public ArrayList<Edge> getNeighourhood(){
		return neighbourhood;
	}
	
	
	public void addNeighbour(Edge edge){
		if(neighbourhood.contains(edge))
		{
			return;
		}
		neighbourhood.add(edge);
		
	}
	public void removeNeighbour(Edge edge){
		if(neighbourhood.contains(edge))
		{
			return;
		}
		neighbourhood.remove(edge);
		
	}
	
	public int getID(){
		return stateid;
	}
	
	public boolean[] getGeneExpresssion()
	{
		return geneExpressions;
	}
	
	
	public boolean equals(Vertex other)
	{

		return this.stateid==other.stateid;
	}
	
	public boolean  diffenceIsOne(Vertex other)
	{
		int noOfMatchedExpressions = 0;
		for(int i =0;i<geneExpressions.length;i++)
		{
			if(noOfMatchedExpressions>1)
				return false;
			if(this.geneExpressions[i]!=other.geneExpressions[i])
				noOfMatchedExpressions++;
		}
		return noOfMatchedExpressions==1;
	}

	
	
}
