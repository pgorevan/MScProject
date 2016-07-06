import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class UpdateFunctionGenerator {
	private ExpressionTreeNode root;
	
	
	public  static int[]  UFG( int possibleNoActivators, int possiblenoSuppressors,Set<Gene> GeneVariables)
	{
		if(possibleNoActivators>GeneVariables.size())
			possibleNoActivators=possiblenoSuppressors=GeneVariables.size();
		
		int min = 1;
		int range = possibleNoActivators;
		Random r = new Random();
		int noActivators = r.nextInt(range) + min;
		System.out.println(noActivators);
		
		int noOfOperators = noActivators-1;
		int[] arrayRepOfTree= new int[noActivators+noOfOperators];
		arrayRepOfTree = updateFunction(noActivators);
		System.out.println("This"+noActivators);
		
		for(int i : arrayRepOfTree)
		{
			System.out.print(i);
		}

		return arrayRepOfTree;
		
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
		System.out.println("The number of iteratiions performed is "+selected);
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
