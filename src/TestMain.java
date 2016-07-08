import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class TestMain {

	public static void main(String[] args) {
		
		Set<Gene> geneSet =  new HashSet<Gene>();
		GeneState one = new GeneState(5);
		Random r = new Random();

		for(int i=0; i<5; i++)
		{
			Gene temp = new Gene(""+i,r.nextBoolean());
			temp.toString();
			geneSet.add(temp);
		}
		List<Gene> listOfGenes = null;
		try {
			GeneFileParser gfp = new GeneFileParser("src/genes.txt");
			listOfGenes = gfp.getGeneList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to read file");
		}
		for(Gene g : listOfGenes){
			System.out.println(g.toString());
		}
		ExpressionTree function = UpdateFunctionGenerator.createUpdateFunction(3,3,geneSet);

		Boolean test123 = function.root.evaluate();
		System.out.println("Tree Created and Evaluated to "+test123);
		function.print();

	}
	

	

}
