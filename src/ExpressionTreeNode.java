
public abstract class ExpressionTreeNode {
	public ExpressionTreeNode leftChild;
	public ExpressionTreeNode rightChild;
	public abstract boolean evaluate();
	public abstract String toString();
}
