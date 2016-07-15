
public abstract class ExpressionNode {
	public ExpressionNode leftChild;
	public ExpressionNode rightChild;
	public abstract boolean evaluate();
	public abstract String toString();
}
