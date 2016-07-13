
public class VarNode extends ExpressionTreeNode {
	private Gene gene;
	
	
	VarNode(Gene in){
		gene = in;
	}
	@Override
	public boolean evaluate() {

		return gene.checkExpression();
	}
	@Override
	public String toString() {
		return gene.getName();
	}

}
