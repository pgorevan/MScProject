import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import graph.*;

public class TestMain {

	public static void main(String[] args) {
		
		
		Gene[] geneData = FileParser.readGeneData("GeneData.csv");
				
		BooleanNetwork network = new BooleanNetwork(geneData);
		network.generateStates();
		int  noSteadystates = network.checkForSteadyStates();
					
		System.out.println("Number of steady states is "+noSteadystates);
		try {
			network.printToFile("States.txt");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

		}
		
		
		
		ParseData pd = new ParseData();
		DLLGraph graph = (DLLGraph) pd.readStateFile();
		graph.createEdges();
		Iterator<Edge> iter = graph.edges();
		int count = 0;
		while(iter.hasNext())
		{
			Edge e = iter.next();
			if(e.getLabel()==3)
				count++;
				
		}
		System.out.println("Graph Created "+count);
		 
	}
	
	

	

}
