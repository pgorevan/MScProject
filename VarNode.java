/**
 * VarNode models a leaf node of an expression tree contains a gene variable
 * which acts as a boolean variable
 */
public class VarNode extends ExpressionNode {
	private Gene gene;

	/**
	 * Constructor
	 * 
	 * @param in A Gene Object
	 */
	VarNode(Gene in) {
		gene = in;
	}

	/**
	 * Checks the gene expression and return its
	 *
	 * @return boolean Returns true when the gene is expressed false otherwise
	 */
	@Override
	public boolean evaluate() {

		return gene.checkExpression();
	}

	/**
	 * Sets this nodes Gene attribute
	 */
	public void setGene(Gene g) {
		gene = g;
	}

	/**
	 * Creates a String representation of this object
	 * 
	 * @return String the name of the gene this node contains
	 */
	@Override
	public String toString() {
		return gene.getName();
	}

	/**
	 * Gets the Gene object stored in this node
	 * 
	 * @return String the string "OR";
	 */
	public Gene getGene() {
		return gene;

	}

}
