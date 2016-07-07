
public class NotNode extends ExpressionTreeNode {
	
	public ExpressionTreeNode child;

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return !child.evaluate();
	}
	
	public NotNode(ExpressionTreeNode in)
	{
		child = in;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NOT";
	}
	

}
