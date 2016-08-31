/** Abstract class that defines the properties of an Expression Tree Node
 */
public abstract class ExpressionNode {
	public ExpressionNode leftChild;
	public ExpressionNode rightChild;
	public abstract boolean evaluate();
	public abstract String toString();
}
