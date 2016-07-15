
public class VarNode extends ExpressionNode {
	private Gene gene;
	
	
	VarNode(Gene in){
		gene = in;
	}
	@Override
	public boolean evaluate() {

		return gene.checkExpression();
	}
	public void setGene(Gene g){
		gene = g;
	}
	@Override
	public String toString() {
		return gene.getName();
	}
	public Gene getGene(){
		return gene;
		
	}
	

}
