/** AndNode models the AND operator of a boolean function
 */
public class AndNode extends ExpressionNode{

	/** Constructor
	 * @param l			An ExpressionNode object
	 * @param r			An ExpressionNode object
	 */	
	public AndNode(ExpressionNode l, ExpressionNode r)
	{
		leftChild = l;
		rightChild =r;
	}
	
	/** Calls the evaluate method of its children and performs an 'AND' operation on them
	 *
	 * @return boolean  Returns true when both child nodes evaluate to true, false otherwise	
	 */	
	@Override
	public boolean evaluate() {


		return leftChild.evaluate()&&rightChild.evaluate();
	}
	

	/** Sets the left child of this node
	 * @param nodeIn	An ExpressionNode object
	 */	
	public void setLeftNode(ExpressionNode nodeIn){
		
		leftChild = nodeIn;
	}
	/** Sets the right child of this node
	 * @param nodeIn	An ExpressionNode object
	 */	
	public void setRightNode(ExpressionNode nodeIn){
		rightChild = nodeIn;
	}
	
	/** Sets the right child of this node
	 * @return String  returns the string "AND";
	 */
	@Override
	public String toString() {
		return "AND";
	}
	
	

}
