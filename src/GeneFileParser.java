import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneFileParser {

	
	public static Gene[] readGeneInputFile(){
		Gene[] arrayOfGenes= readGeneFile();
		ArrayList<ExpressionNode> arrayVersion;
		FileReader fr = null;
		Scanner sc = null;
		try {
			fr = new FileReader("src/updatefunctions.txt");
			sc = new Scanner(fr);
			int indexOfGeneArray = 0;
			while(sc.hasNextLine())
			{	
				arrayVersion = new ArrayList<ExpressionNode>();
				String line = sc.nextLine();
				String[] sections = line.split(",");
				String geneName = sections[0];
				Gene g = getGene(geneName,arrayOfGenes);

				String function = sections[1];
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
						Gene geneVar = getGene(s,arrayOfGenes);
						temp = new VarNode(geneVar);
						arrayVersion.add(temp);
					}
				
					
					
				}
				ExpressionNode[] a = arrayVersion.toArray(new ExpressionNode[arrayVersion.size()]);
				ExpressionTree t = new ExpressionTree();
				t.createTreeFirst(a);
	
				g.setUpdateFunction(t);

				arrayOfGenes[indexOfGeneArray++]= g;

			}	
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find function file");
		}
		finally{
			sc.close();
			try {
				fr.close();
			} catch (IOException e) {
				System.err.println("Failed to Close File!");
			}
			
		}
		return arrayOfGenes;
		
	}
	
	private static Gene[] readGeneFile(){
		FileReader fr = null;
		Scanner sc = null;
		Gene[] geneArray = null;
		try {
			fr = new FileReader("src/genes.txt");
			sc = new Scanner(fr);
			int noGenes=0;
			while(sc.hasNext())
			{
				noGenes++;
				sc.next();
			}
			geneArray = new Gene[noGenes];
			
			sc.close();
			fr.close();
			fr = new FileReader("src/genes.txt");
			sc = new Scanner(fr);
			int indexOfArray = 0;
			String[] sections;
			while(sc.hasNextLine())
			{	

				String line = sc.nextLine();
				sections = null;
				sections = line.split(",");
				String geneName = sections[0];
				String strGeneExpressed = sections[1];
				Boolean geneExpressed = Boolean.valueOf(strGeneExpressed);
				Gene g = new Gene(geneName, geneExpressed);
				geneArray[indexOfArray++] = g;	
			}
			fr.close();
			sc.close();
			return geneArray;
				
			
			
			
			
			
		}
		catch (IOException e){
			System.err.println("Could not read genes.txt");
			
		}
		return geneArray;
		
	}
	
	private static Gene  getGene(String name,Gene[] arrayOfGenes){
		for(Gene g : arrayOfGenes)
		{
			if(name.equals(g.getName()))
				return g;
		}
		Gene g = new Gene(name, false);
		return g;
		
	}
	
}
