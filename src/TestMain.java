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
		
		int [] ActivatorFunction = UpdateFunctionGenerator.UFG(3, 3, geneSet);
		int [] RepressorFunction = UpdateFunctionGenerator.UFG(3, 3, geneSet);
		ExpressionTreeNode[] ActivatorHalf = UpdateFunctionGenerator.convertToExpressionTree(ActivatorFunction, geneSet);
		ExpressionTreeNode[] RepressorHalf = UpdateFunctionGenerator.convertToExpressionTree(RepressorFunction, geneSet);
		ExpressionTree treeOne = new ExpressionTree();
		treeOne.createTreeFirst(ActivatorHalf);
		ExpressionTree treeTwo = new ExpressionTree();
		treeTwo.createTreeFirst(RepressorHalf);
		ExpressionTree updateFunction = new ExpressionTree();
		NotNode not = new NotNode(treeTwo.root);
		AndNode and = new AndNode(treeOne.root,not);
		updateFunction.root = and;
		

		Boolean test123 = treeOne.root.evaluate();
		System.out.println("Tree Created and Evaluvated to "+test123);
		for(ExpressionTreeNode n :ActivatorHalf)
		{
			System.out.print(" "+n.toString()+" ");
		}
		System.out.print("AND NOT(");
		for(ExpressionTreeNode n : RepressorHalf)
		{	
			System.out.print(" "+n.toString()+" ");
		}
		System.out.print(")");
	}
	

	

}
