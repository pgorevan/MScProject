
public class ABNState {
	private int stateID;
	private Gene[] stateOfGenes;
	private int time;
	
	public ABNState(Gene[] initialState,int time, int stateID)
	{
		stateOfGenes = initialState;
		this.time = time ;
		this.stateID = stateID;

	}
	


	public Gene[] getGenes(){
		return stateOfGenes;
	}
	
	public ABNState applyGeneUpdateFunction(String geneName)
	{
		int index = 0;
		Gene[] successorArray = stateOfGenes.clone();
		for(Gene g : stateOfGenes)
		{
			if(g.getName().equals(geneName))
			{
				
				Gene temp = g.clone();
				successorArray[index] = temp;
				ExpressionTree updateFunction = g.getUpdateFunction();
				boolean result = updateFunction.root.evaluate();
				temp.setExpressed(result);
			}
			index++;
		}
		ABNState successor = new ABNState(successorArray, stateID, stateID);
		return successor;
	}
	
	public String toString(){
		String result =""+stateID+time+"\n";
		for(Gene gene : stateOfGenes)
		{
			result += gene.getName()+" "+gene.checkExpression()+", "; 
		}
		return result;
	}
@Override	
	public boolean equals(Object input){
	
		if(input==null || !(input instanceof ABNState))
			return false;
		ABNState that =(ABNState) input;
		int i = 0;
		for(Gene g : stateOfGenes)
		{
			boolean b1 = g.checkExpression();
			Gene other = that.stateOfGenes[i];
			boolean b2 = other.checkExpression();
			if(!b1==b2)
				return false;
			i++;
		}
		return true;
		
	}
	


}
