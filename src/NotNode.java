
public class NotNode extends ExpressionNode {
	

	@Override
	public boolean evaluate() {

		return !rightChild.evaluate();
	}
	
	public NotNode(ExpressionNode in)
	{
		rightChild =in;
	}

	@Override
	public String toString() {
		return "NOT";
	}
	
	public void setNode(ExpressionNode nodeIn){
		rightChild = nodeIn;
	}
	

}
