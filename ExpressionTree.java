
/** Models a boolean function as a binary tree. Leaf node are variables 
 * non-leaf nodes operators
 *
 */
import java.util.Iterator;
import java.util.Stack;

/**
 * Constructor
 */
public class ExpressionTree {
	ExpressionNode root;
	Index myindex = new Index();

	/**
	 * Converts a boolean function in the form a preordered array of
	 * ExpressionNodes, each of which is either a variable or an operator, to a
	 * usable expressionTree
	 * 
	 * @param ExpressionNode[]
	 * @return ExpressionNode The root of the ExpressionTree
	 * 
	 */
	public ExpressionNode createTree(ExpressionNode[] preorder) {
		root = preorder[0];
		// Call recursive function the creates the entire tree;
		return createTreePrivate(preorder, myindex, root);

	}

	/**
	 * Checks if the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;

	}

	/**
	 * @return String The boolean function as a String
	 */
	public String print() {
		String str = "";
		String result = inOrderIter(root, str);
		return result;
	}

	private String inOrderIter(ExpressionNode root, String output) {

		if (root == null)
			return output;

		Stack<ExpressionNode> s = new Stack<ExpressionNode>();
		ExpressionNode currentNode = root;

		while (!s.empty() || currentNode != null) {

			if (currentNode != null) {

				s.push(currentNode);
				currentNode = currentNode.leftChild;

			} else {

				ExpressionNode n = s.pop();

				output = output + n.toString() + " ";
				currentNode = n.rightChild;

			}
		}
		return output;
	}

	/**
	 * Recursive function that sets the child nodes (where applicable) of each
	 * node in the tree starting at the root
	 * 
	 * @param preorder
	 *            An array of ExpressionNodes representing a boolean function
	 * @param pos
	 *            Current position in the array
	 * @param node
	 *            ExpressionNode
	 */

	private ExpressionNode createTreePrivate(ExpressionNode[] preorder, Index pos, ExpressionNode node) {
		int i = pos.index;
		// Check if the end of the array has been reached if so finish
		if (i == preorder.length)
			return null;
		// Get the next expression node
		ExpressionNode temp = preorder[i];
		// Increment the index so it points to the next node
		pos.index++;
		// If instance of a VarNode then it will have no child nodes
		if (temp instanceof VarNode) {
			temp = (VarNode) temp;
		}
		if (preorder[i] instanceof VarNode) {

		} else if (preorder[i] instanceof OrNode) {
			// Set left and right child of this OrNode
			((OrNode) temp).setLeftNode(createTreePrivate(preorder, myindex, temp.leftChild));
			((OrNode) temp).setRightNode(createTreePrivate(preorder, myindex, temp.rightChild));
		} else if (preorder[i] instanceof AndNode) {
			// Set left and right child of this AndNode
			((AndNode) temp).setLeftNode(createTreePrivate(preorder, myindex, temp.leftChild));
			((AndNode) temp).setRightNode(createTreePrivate(preorder, myindex, temp.rightChild));
		} else {
			// Since this is NotNode it only has one child node
			((NotNode) temp).setNode(createTreePrivate(preorder, myindex, temp.rightChild));
		}

		return temp;

	}

	public Iterator<ExpressionNode> iterator() {
		return new UDFIterator();

	}

	private class UDFIterator implements Iterator<ExpressionNode> {
		Stack<ExpressionNode> stack;

		public UDFIterator() {
			ExpressionNode currentNode = root;
			stack = new Stack<ExpressionNode>();
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.leftChild;
			}
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

		public ExpressionNode next() {
			ExpressionNode node = stack.pop();
			if (node.rightChild != null) {
				ExpressionNode temp = node.rightChild;
				while (temp != null) {
					stack.push(temp);
					temp = temp.leftChild;
				}

			}
			return node;
		}

	}

	class Index {
		int index = 0;

	}
}
