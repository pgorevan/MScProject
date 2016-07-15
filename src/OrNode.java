
public class OrNode extends ExpressionNode {

	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return leftChild.evaluate() || rightChild.evaluate();
	}
	public OrNode(ExpressionNode l, ExpressionNode r)
	{
		leftChild = l;
		rightChild =r;
	}
	
	public void setLeftNode(ExpressionNode nodeIn){
		
		leftChild = nodeIn;
	}
	
	public void setRightNode(ExpressionNode nodeIn){
		rightChild = nodeIn;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "OR";
	}

}
