import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TestMain {

	public static void main(String[] args) {
		
		Boolean t,ra,s;
		ra = true;
		s = false;
		t = ra && !ra;
		System.out.println(t);
		Set<Gene> geneSet =  new HashSet<Gene>();
		GeneState one = new GeneState(5);
		Random r = new Random();
		Gene[] allGenes = new Gene[5];
		for(int i=0; i<5; i++)
		{
			Gene temp = new Gene(""+i,r.nextBoolean());
			allGenes[i] = temp;
			geneSet.add(temp);
		}
		
		Set  variableNodes = new HashSet();
		for( Gene i : allGenes)
			variableNodes.add(i);
		VarNode first = new VarNode(allGenes[0]);
		VarNode second = new VarNode(allGenes[1]);
		
		ExpressionTreeNode test = new OrNode(first, second);
		boolean result = test.evaluate();
		ExpressionTreeNode not = new NotNode(test);
		System.out.println("result of the boolean function is "+result);
		System.out.println("result of the negation boolean function is "+not.evaluate());
		
		int [] resultFunction = UpdateFunctionGenerator.UFG(3, 3, geneSet);
		
		ExpressionTreeNode[] booleanUpdateFunction = UpdateFunctionGenerator.convertToExpressionTree(resultFunction, geneSet);
		
		for(ExpressionTreeNode n :booleanUpdateFunction)
		{
			System.out.print(" "+n.toString()+" ");
		}
		
	
	}
	
	public static int[] updateFunction(int noVariables)
	{
		if(noVariables==1)
		{
			int[] arrayRepOfExp = new int[1];
			arrayRepOfExp[0] =0;
			return arrayRepOfExp;
		}
			
		Random r = new Random();
		int[] arrayRepOfExp = new int[2*noVariables-1];
		for(int i=0;i<arrayRepOfExp.length;i++)
		{
			if(r.nextBoolean())
				arrayRepOfExp[i] = 1;
			else
				arrayRepOfExp[i] = 0;
		}
		while(!isValidExpression(arrayRepOfExp))
		{
			arrayRepOfExp=updateFunction(noVariables);
		}
		return arrayRepOfExp;
		}
	public static boolean isValidExpression(int[] nodes) {
	    int nonleaves = 0, leaves = 0, i = 0;
	    for (i=0; i<nodes.length && nonleaves + 1 != leaves; i++)
	        if (nodes[i]==0) leaves++;
	        else nonleaves++;
	    return nonleaves + 1 == leaves && i == nodes.length;
	}
	
	

}
