
public class AndNode extends ExpressionTreeNode{
	private  ExpressionTreeNode leftChild = null;
	private ExpressionTreeNode rightChild = null;
	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return leftChild.evaluate()&&rightChild.evaluate();
	}
	
	public AndNode(ExpressionTreeNode l, ExpressionTreeNode r)
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
		return "AND";
	}
	
	

}
