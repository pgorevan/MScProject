import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import graph.*;

public class TestMain {

	public static void main(String[] args) {
		
		
		Gene[] geneData = FileParser.readGeneData("GeneData.csv");
		
		for(Gene g : geneData)
		{
			
			ExpressionTree tree = g.getUpdateFunction();
			tree.toString();
			System.out.println(g.toString()+"   Update Function: "+tree.print());
		}

//		
		BooleanNetwork network = new BooleanNetwork(geneData);
		network.generateStates();
		int  noSteadystates = network.checkForSteadyStates();
		
		Gene[] genes = GeneFileParser.readGeneInputFile();
		BooleanNetwork network2 = new BooleanNetwork(genes);
		network2.generateStates();
		int  noSteadystates2 = network.checkForSteadyStates();


	
		
		System.out.println("Number of steady states is"+noSteadystates +" "+noSteadystates2);
		try {
			network.printToFile("States.txt");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

		}
		
		
		
		ParseData pd = new ParseData();
		Graph graph = pd.readStateFile();
		System.out.println("Graph Created");
		Iterator<Vertex> iterator = graph.vertices();
		while(iterator.hasNext())
		{
			Vertex v = iterator.next();
			Iterator<Vertex> iterator2 = graph.vertices();
			while(iterator.hasNext())
			{
				Vertex v2 = iterator.next();
				if(v.diffenceIsOne(v2))
				{
					graph.addEdge(v, v2);
				}
					
			}
		}
		System.out.println("Graph Created");
		 
	}
	
	

	

}
