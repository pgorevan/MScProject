
public class NotNode extends ExpressionTreeNode {
	

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return !rightChild.evaluate();
	}
	
	public NotNode(ExpressionTreeNode in)
	{
		rightChild =in;
	}

	@Override
	public String toString() {
		return "NOT";
	}
	
	public void setNode(ExpressionTreeNode nodeIn){
		rightChild = nodeIn;
	}
	

}
