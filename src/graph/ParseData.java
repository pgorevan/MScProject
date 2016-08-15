package graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ParseData {
	
	public static Graph readStateFile()
	{
		DLLGraph graph = new DLLGraph();
		
		try {
			FileReader fr = new FileReader("States.txt");
			Scanner sc = new Scanner(fr);
			String[] geneNameArray =null;
			if(sc.hasNext())
			{
				String genes = sc.nextLine();
				geneNameArray = genes.split(" ");
				graph.setGeneNames(geneNameArray);
				
			}
			int count = 0;
			while(sc.hasNext())
			{
		
				String state = sc.nextLine();
				String[] stateArray = state.split(",");
				int stateid = Integer.parseInt(stateArray[0]);
				int timeStamp = Integer.parseInt(stateArray[1]);
				boolean[] expressions = new boolean[stateArray.length-2];
				for(int i=2,j =0;i<stateArray.length;i++,j++)
				{
					boolean b = Boolean.parseBoolean(stateArray[i]);
					expressions[j] = b;
				}
				Vertex v = new Vertex(stateid,timeStamp,expressions);
				graph.addVertex(v);
				count++;
			}
			System.out.println("Number of States entered "+count);
			
		} catch (FileNotFoundException e) {
			System.err.println("Cannot read input file");;
		}
		return graph;
	}

}
