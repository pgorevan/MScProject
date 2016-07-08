import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneFileParser {
	private Gene[] genes;
	private List<Gene> listOfGenes;
	
	GeneFileParser(String fileName) throws IOException{
		
		
		listOfGenes = new ArrayList<Gene>();
		
		FileReader fr = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fr);
		Scanner sc  = new Scanner(fr);
		String geneName;
		while(sc.hasNextLine())
		{
			geneName = sc.nextLine();
			Gene temp = new Gene(geneName, false);
			listOfGenes.add(temp);
		}
		
		
		
	}
	
	public List<Gene> getGeneList(){
		return listOfGenes;
		
	}

}
