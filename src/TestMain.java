import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import graph.*;

public class TestMain {

	public static void main(String[] args) {
		
		


		Gene[] genes = GeneFileParser.readGeneInputFile();
		Gene GATA = genes[9];
		ExpressionTree function = GATA.getUpdateFunction();

		

		
		
		BooleanNetwork network = new BooleanNetwork(genes);
		network.generateStates();
		int  noSteadystates = network.checkForSteadyStates();


	
		
		System.out.println("Number of steady states is"+noSteadystates);
		try {
			network.printToFile("States.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
