import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class FileParser {
	
	public static Gene[] readGeneData(String fileName)
	{
		List<String> geneList = convertDataFileToArrayList(fileName);
		geneList.size();
		Gene[] geneArray = new Gene[geneList.size()];
		
		for(int i= 0; i<geneList.size();i++)
		{

			String geneStr = geneList.get(i);
			String[] sections;
			sections = geneStr.split(",");
			String geneName = sections[0];
			Gene gene = getOrCreateGene(geneName,geneArray);
			String strGeneExpressed = sections[1];
			Boolean geneExpressed = Boolean.valueOf(strGeneExpressed);
			gene.setExpressed(geneExpressed);
			geneArray[i] = gene;
			
			
		}
		insertUpdateFunctions(geneList,geneArray);
		return geneArray;
	}
	
	public static void insertUpdateFunctions(List<String> geneList,Gene[] geneArray)
	{

		for(int i= 0; i<geneList.size();i++)
		{
		ArrayList<ExpressionNode> arrayVersion;
		arrayVersion = new ArrayList<ExpressionNode>();	
		Gene gene = geneArray[i];	
		String geneStr = geneList.get(i);
		String[] sections;
		sections = geneStr.split(",");	
		String function = sections[2];

		String[] parts = function.split(" ");
		
		for(String s: parts)
		{
			ExpressionNode temp;
			switch(s)
			{
			case "AND":
				temp = new AndNode(null,null);
				arrayVersion.add(temp);
				break;
			case "OR":
				temp = new OrNode(null,null);
				arrayVersion.add(temp);
				break;
			case "NOT":
				temp = new NotNode(null);
				arrayVersion.add(temp);
				break;
			default:
				Gene geneVar = getOrCreateGene(s,geneArray);
				temp = new VarNode(geneVar);
				arrayVersion.add(temp);
			}
		
			
			
		}
		ExpressionNode[] a = arrayVersion.toArray(new ExpressionNode[arrayVersion.size()]);
		ExpressionTree t = new ExpressionTree();
		t.createTree(a);

		gene.setUpdateFunction(t);

		}
	}
	
	
	private static List<String> convertDataFileToArrayList(String fileName)
	{
		List<String> GeneList = new ArrayList<String>();
		FileReader fr;
		Scanner sc;
		try{
			fr = new FileReader("src/"+fileName);
			sc = new Scanner(fr);
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				GeneList.add(line);
			}
		}catch (FileNotFoundException e)
		{
			System.err.println("File not found");
		}
		finally{

		}
		
		return GeneList;
		
	}
	
	private static int countLine(String fileName)
	{
		int count = 0;
		FileReader fr = null;
		Scanner sc = null;
		try{
			fr = new FileReader("src/"+fileName);
			sc = new Scanner(fr);
			while(sc.hasNextLine())
			{
				count++;
				String line = sc.nextLine();
			}
				
				
		}catch (FileNotFoundException e)
		{
			System.err.println("Failed to count number of lines in file");
		}
		finally{
			try{
			fr.close();
			sc.close();
			}catch (IOException e){
				System.err.println("Failed to close file reader");
			}
		}
		return count;
		
	}
	private static Gene  getOrCreateGene(String name,Gene[] arrayOfGenes){
		for(Gene g : arrayOfGenes)
		{

			if(g !=null &&name.equals(g.getName()))
				return g;
		}
		Gene g = new Gene(name, false);
		return g;
		
	}
	
	public static Set<Gene> readGeneNameFile(){
		
		Set<Gene> setOfGenes = new HashSet<Gene>();


			FileReader fr;
			try {
				fr = new FileReader("listOfGeneNames.txt");
				Scanner sc = new Scanner(fr);
				int indexOfArray = 0;
				String[] sections;
				Random r = new Random();
				while(sc.hasNextLine())
				{	

					String geneName = sc.nextLine();

					Gene g = new Gene(geneName, r.nextBoolean());
					setOfGenes.add(g);
				}
				fr.close();
				sc.close();
			} catch ( IOException e) {
				
				System.out.println("Could not read list of gene names.");
			}

			return setOfGenes;
			
		}

		

	

}
