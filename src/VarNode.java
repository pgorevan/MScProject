
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
		// TODO Auto-generated method stub
		return gene.getName();
	}

}
