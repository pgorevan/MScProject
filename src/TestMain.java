import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import graph.*;

public class TestMain {

	public static void main(String[] args) {
		
		
		Console con = new Console();
		con.userSelection();

		
		
		
//		Gene[] geneData = FileParser.readGeneData("GeneData.csv");
//				
//		BooleanNetwork network = new BooleanNetwork(geneData);
//		network.generateStates();
//		int  noSteadystates = network.checkForSteadyStates();
//					
//		System.out.println("Number of steady states is "+noSteadystates);
//		try {
//			network.printToFile("states.txt");
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//
//		}
//		
//		
//		
//		ParseData pd = new ParseData();
//		DLLGraph graph = (DLLGraph) pd.readStateFile();
//		graph.createEdges();
//		Iterator<Edge> iter = graph.edges();
//		int count = 0;
//		ArrayList<Edge> edges = new ArrayList<Edge>();
//		while(iter.hasNext())
//		{
//			Edge e = iter.next();
//			if(e.getLabel()==3)
//			{
//				count++;
//				edges.add(e);
//			}	
//		}
//		

    
		 
	}
	
	

	

}
