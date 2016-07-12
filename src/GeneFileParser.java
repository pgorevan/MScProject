import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneFileParser {

	
	public static Gene[] readGeneInputFile(){
		
		Gene[] arrayOfGenes= new Gene[11];
		List<Gene> listOfGenes = new ArrayList<Gene>();
		ArrayList<ExpressionTreeNode> arrayVersion;
		FileReader fr = null;
		Scanner sc = null;
		try {
			fr = new FileReader("src/updatefunctions.txt");
			sc = new Scanner(fr);
			int indexOfGeneArray = 0;
			while(sc.hasNextLine())
			{	
				arrayVersion = new ArrayList<ExpressionTreeNode>();
				String line = sc.nextLine();
				String[] sections = line.split(",");
				String geneName = sections[0];
				Gene g = getOrCreateGene(geneName,listOfGenes);
				if(!listOfGenes.contains(g))
					listOfGenes.add(g);
				String function = sections[1];
				String[] parts = function.split(" ");
				
				for(String s: parts)
				{
					ExpressionTreeNode temp;
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
						Gene geneVar = getOrCreateGene(s,listOfGenes);
						temp = new VarNode(geneVar);
						arrayVersion.add(temp);
					}
				
					
					
				}
				ExpressionTreeNode[] a = arrayVersion.toArray(new ExpressionTreeNode[arrayVersion.size()]);
				ExpressionTree t = new ExpressionTree();
				t.createTreeFirst(a);
				Iterator iter = t.iterator();
	
				g.setUpdateFunction(t);
				System.out.println("");
				while(iter.hasNext())
				{
					ExpressionTreeNode temp = (ExpressionTreeNode) iter.next();
					
					System.out.print(temp.toString()+" ");
				}
				System.out.println("");
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
	
	
	private static Gene  getOrCreateGene(String name,List<Gene> listOfGenes){
		for(Gene g : listOfGenes)
		{
			if(name.equals(g.getName()))
				return g;
		}
		Gene g = new Gene(name, false);
		return g;
		
	}
	
}
