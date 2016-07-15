
public class AndNode extends ExpressionNode{

	@Override
	public boolean evaluate() {


		return leftChild.evaluate()&&rightChild.evaluate();
	}
	
	public AndNode(ExpressionNode l, ExpressionNode r)
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
		return "AND";
	}
	
	

}
