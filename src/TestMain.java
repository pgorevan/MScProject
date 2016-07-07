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
		Gene temp2 = new Gene("Gene1",r.nextBoolean());
		Gene temp3 = new Gene("Gene2",r.nextBoolean());
		VarNode v1 = new VarNode(temp2);
		VarNode v2 = new VarNode(temp3);
		OrNode or = new OrNode(v1,v2);
	
		
		ExpressionTree function = UpdateFunctionGenerator.createUpdateFunction(3,3,geneSet);
		
	
		

		Boolean test123 = function.root.evaluate();
		System.out.println("Tree Created and Evaluated to "+test123);
		function.print();

	}
	

	

}
