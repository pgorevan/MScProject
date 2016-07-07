/** Generates a boolean in expression in the form of a binary  from set of Genes
 *
 * @author Patrick Gorevan.
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class UpdateFunctionGenerator {

	/** Generates a random boolean expression in binary tree form from a set of genes
	 * 
	 * @param possibleNoActivators		The number of possible activator genes the function can use
	 * @param possiblenoSuppressors		The number of possible suppressor genes the function can use
	 * @return updateFunction			Binary tree representing a boolean function of the form suppressors AND NOT (activators)
	 */
	public static ExpressionTree createUpdateFunction(int possibleNoActivators, int possiblenoSuppressors,Set<Gene> GeneVariables){
		
		
		ExpressionTree updateFunction = new ExpressionTree();
		ExpressionTree treeOne = new ExpressionTree();
		ExpressionTree treeTwo = new ExpressionTree();
		if(!GeneVariables.isEmpty())
		{
		int [] ActivatorFunction = UFG(possibleNoActivators, GeneVariables);	
		ExpressionTreeNode[] ActivatorHalf = UpdateFunctionGenerator.convertToExpressionTree(ActivatorFunction, GeneVariables);
		treeOne.createTreeFirst(ActivatorHalf);
		}
		if(!GeneVariables.isEmpty())
		{
		int [] RepressorFunction = UFG(possiblenoSuppressors, GeneVariables);
		ExpressionTreeNode[] RepressorHalf = UpdateFunctionGenerator.convertToExpressionTree(RepressorFunction, GeneVariables);
		treeTwo.createTreeFirst(RepressorHalf);
		}
		//If there activators and no suppressors  update function is the activator tree
		if(!treeOne.isEmpty()&&treeTwo.isEmpty())
		{
			updateFunction.root = treeOne.root;
		}
		
		//If there are suppressors present but no activators update function consists of the repressor tree
		if(treeOne.isEmpty()&&!treeTwo.isEmpty())
		{
			NotNode not = new NotNode(treeTwo.root);
			treeTwo.root = not;
			updateFunction.root=treeTwo.root;
		}
		// If there are both activators and suppressors join the two parts together to form the update function
		if(!treeOne.isEmpty()&&!treeTwo.isEmpty())
		{
			NotNode not = new NotNode(treeTwo.root);
			AndNode and = new AndNode(treeOne.root,not);
			updateFunction.root = and;
		}

		// Return the update function as a boolean expression tree
		return updateFunction;

	}
	
	//Creates an array representing the preorder traversal of a binary tree 
	//where 1 is a internal node and 0 is a leaf node
	private static  int []  UFG( int NoBoolVariables,Set<Gene> GeneVariables)
	{
		if(NoBoolVariables>GeneVariables.size())
			NoBoolVariables=GeneVariables.size();
		
		int min = 1;
		int range = NoBoolVariables;
		Random r = new Random();
		int noActivators = r.nextInt(range) + min;
		System.out.println();
		System.out.println(noActivators);
		
		int noOfOperators = noActivators-1;
		int[] arrayRepOfTree= new int[noActivators+noOfOperators];
		arrayRepOfTree = updateFunction(noActivators);
		
		for(int i : arrayRepOfTree)
		{
			System.out.print(i);
		}
		return arrayRepOfTree;
		

		
	}
	
	
	
	
	
	
	private static int[] updateFunction(int noVariables)
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
	    {	
	        if (nodes[i]==0) leaves++;
	        else nonleaves++;
	    }  
	    return nonleaves + 1 == leaves && i == nodes.length;
	}
	
	public static ExpressionTreeNode[] convertToExpressionTree(int[] a, Set<Gene> GeneVariables)
	{
		
		Random r = new Random();
		ExpressionTreeNode[] udfArray = new ExpressionTreeNode[a.length];
		for(int i =0; i<a.length;i++)
		{
			if(a[i]==1)
			{
				ExpressionTreeNode temp = null;
				if(r.nextBoolean())
				{
					 temp = new OrNode(null,null);
				}
				else
				{
					temp = new AndNode(null, null);
				}
				udfArray[i] = temp;	
			}
			else
			{
				Gene g = SelectGene(GeneVariables);
				ExpressionTreeNode varNode = new VarNode(g);
				udfArray[i] = varNode;
			}
			
				
		}
		return udfArray;
		
	}
	
	private static Gene SelectGene(Set<Gene> GeneVariables)
	{
		int numberOfGenesAvailable = GeneVariables.size();
		int selected = new Random().nextInt(numberOfGenesAvailable);
		int i = 0;
		Iterator<Gene> iterator = GeneVariables.iterator();
		while (iterator.hasNext())
		{	
			if (i==selected)
			{
			Gene g = iterator.next();
			iterator.remove();
				return g;
			
			}
			Gene g = iterator.next();
			i++;
		}
		return null;
		
	}
	
	
	
}
