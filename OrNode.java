/**
 * AndNode models the OR operator of a boolean function
 */
public class OrNode extends ExpressionNode {

	/**
	 * Constructor
	 * 
	 * @param l
	 *            An ExpressionNode object
	 * @param r
	 *            An ExpressionNode object
	 */
	public OrNode(ExpressionNode l, ExpressionNode r) {
		leftChild = l;
		rightChild = r;
	}

	/**
	 * Sets the left child of this node
	 * 
	 * @param nodeIn
	 *            An ExpressionNode object
	 */
	public void setLeftNode(ExpressionNode nodeIn) {

		leftChild = nodeIn;
	}

	/**
	 * Sets the right child of this node
	 * 
	 * @param nodeIn
	 *            An ExpressionNode object
	 */
	public void setRightNode(ExpressionNode nodeIn) {
		rightChild = nodeIn;
	}

	/**
	 * Calls the evaluate method of its children and performs an 'OR' operation
	 * on them
	 *
	 * @return boolean Returns true when either of its child nodes evaluate to
	 *         true, false otherwise
	 */
	@Override
	public boolean evaluate() {

		return leftChild.evaluate() || rightChild.evaluate();
	}
	/** Creates a String representation of this object
	 * @return String  the string "OR";
	 */
	@Override
	public String toString() {
		return "OR";
	}

}
