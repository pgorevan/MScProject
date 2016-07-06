
public class OrNode extends ExpressionTreeNode {
	private ExpressionTreeNode leftChild;
	private ExpressionTreeNode rightChild;
	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return leftChild.evaluate() || rightChild.evaluate();
	}
	public OrNode(ExpressionTreeNode l, ExpressionTreeNode r)
	{
		leftChild = l;
		rightChild =r;
	}
	
	public void setLeftNode(ExpressionTreeNode nodeIn){
		
		leftChild = nodeIn;
	}
	
	public void setRightNode(ExpressionTreeNode nodeIn){
		rightChild = nodeIn;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "OR";
	}

}