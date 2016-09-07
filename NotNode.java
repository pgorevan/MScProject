/**
 * Class which models the 'NOT' boolean operator
 *
 */
public class NotNode extends ExpressionNode {
	/**
	 * Calls the evaluate method of its only child node and performs an 'NOT'
	 * operation on return
	 *
	 * @return boolean Returns true when when its child node is false, false
	 *         when child node is ture
	 */

	@Override
	public boolean evaluate() {

		return !rightChild.evaluate();
	}

	/**
	 * Constructor
	 * 
	 * @param in
	 *            An ExpressionNode object
	 * 
	 */
	public NotNode(ExpressionNode in) {
		rightChild = in;
	}

	/**
	 * Sets the right child of this node
	 * 
	 * @param nodeIn
	 *            An ExpressionNode object
	 */
	public void setNode(ExpressionNode nodeIn) {
		rightChild = nodeIn;
	}

	/**
	 * Creates a String representation of this object
	 * 
	 * @return String the string "NOT";
	 */
	@Override
	public String toString() {
		return "NOT";
	}

}
