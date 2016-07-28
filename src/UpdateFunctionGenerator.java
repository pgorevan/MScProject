/** Generates a boolean in expression in the form of a binary  from set of Genes
 *
 * @author Patrick Gorevan.
 */

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class UpdateFunctionGenerator {

	/** Generates a random boolean expression in binary tree form from a set of genes
	 * 
	 * @param noPossActivators		The number of possible activator genes the function can use
	 * @param noPossSupressors		The number of possible suppressor genes the function can use
	 * @return updateFunction		Binary tree representing a boolean function of the form suppressors AND NOT (activators)
	 */
	public static ExpressionTree createUpdateFunction(int noPossActivators, int noPossSupressors,Set<Gene> GeneVariables){
		
		
		ExpressionTree updateFunction = new ExpressionTree(); // final update function
		ExpressionTree activatorTree = new ExpressionTree(); // activator half of the function
		ExpressionTree suppressorTree = new ExpressionTree(); // suppressor half of the function
		
		if(!GeneVariables.isEmpty())
		{
		// Create a array representing the preorder traversal of the activator subtree
		int [] ActivatorFunction = UFG(noPossActivators, GeneVariables);	
		ExpressionNode[] ActivatorHalf = convertToExpressionTree(ActivatorFunction, GeneVariables);
		// Convert the array to a booelan expression tree
		activatorTree.createTree(ActivatorHalf);
		}
		if(!GeneVariables.isEmpty())
		{
			// Create a array representing the preorder traversal of the supressor subtree	
		int [] RepressorFunction = UFG(noPossSupressors, GeneVariables);
		ExpressionNode[] RepressorHalf = convertToExpressionTree(RepressorFunction, GeneVariables);
		suppressorTree.createTree(RepressorHalf);
		}
		//If there activators and no suppressors  update function is the activator tree
		if(!activatorTree.isEmpty()&&suppressorTree.isEmpty())
		{
			updateFunction.root = activatorTree.root;
		}
		//If there are suppressors present but no activators update function consists of the repressor tree
		if(activatorTree.isEmpty()&&!suppressorTree.isEmpty())
		{
			NotNode not = new NotNode(suppressorTree.root);
			suppressorTree.root = not;
			updateFunction.root=suppressorTree.root;
		}
		// If there are both activators and suppressors join the subtrees together to form the update function
		if(!activatorTree.isEmpty()&&!suppressorTree.isEmpty())
		{
			NotNode not = new NotNode(suppressorTree.root);
			AndNode and = new AndNode(activatorTree.root,not);
			updateFunction.root = and;
		}

		// Return the update function as a boolean expression tree
		return updateFunction;

	}
	
	/** Creates an array representing the preorder traversal of a binary tree 
	 *  where 1 is a operator node and 0 is a leaf node(VarNode)
	 * 
	 * @param noPossVars		Number of variables ie leaf nodes of the tree
	 * @param GeneVariables			The set of genes 
	 * @return arrayRepOfTree		An int array containing the preorder traversal of a binary tree
	 */	
	private static  int []  UFG( int noPossVars,Set<Gene> GeneVariables)
	{
		//Check if the number of possible variables is valid
		if(noPossVars>GeneVariables.size())
			noPossVars=GeneVariables.size();
		int noVariables = selectNoVariables(noPossVars);
		int[] arrayRepOfExp;
		do
		{
			if(noVariables==1)
			{
				arrayRepOfExp = new int[1];
				arrayRepOfExp[0] =0;
				return arrayRepOfExp;
			}
				
			Random r = new Random();
			arrayRepOfExp = new int[2*noVariables-1];
			for(int i=0;i<arrayRepOfExp.length;i++)
			{
				if(r.nextBoolean())
					arrayRepOfExp[i] = 1;
				else
					arrayRepOfExp[i] = 0;
			}
		}while(!isValidExpression(arrayRepOfExp));
		return arrayRepOfExp;

	}
	
	private static  int selectNoVariables( int noPossVar)
	{

		int min = 1;
		int range = noPossVar;
		Random r = new Random();
		int noVariables = r.nextInt(range) + min;
		return noVariables;
	}
	
	/** Checks whether a given array is a valid preorder traversal
	 * 
	 * @param nodes array of 1 and 0s representing internal nodes(1s) and leafs(0s)

	 * @return Boolean array is valid or not		
	 */		
	public static boolean isValidExpression(int[] nodes) {
	    int nonleaves = 0, leaves = 0, i = 0;
	    for (i=0; i<nodes.length && nonleaves + 1 != leaves; i++)
	    {	
	        if (nodes[i]==0) leaves++;
	        else nonleaves++;
	    }  
	    return nonleaves + 1 == leaves && i == nodes.length;
	}
	
	public static ExpressionNode[] convertToExpressionTree(int[] a, Set<Gene> GeneVariables)
	{
		
		Random r = new Random();
		ExpressionNode[] udfArray = new ExpressionNode[a.length];
		for(int i =0; i<a.length;i++)
		{
			if(a[i]==1)
			{
				ExpressionNode temp = null;
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
				ExpressionNode varNode = new VarNode(g);
				udfArray[i] = varNode;
			}
			
				
		}
		return udfArray;
		
	}
	
	/** Randomly selects a gene  from a set of genes
	 * 
	 * @param GeneVariables	Set of genes the update function can use

	 * @return g  a Gene		
	 */
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
			iterator.next();
			i++;
		}
		return null;
		
	}
}
