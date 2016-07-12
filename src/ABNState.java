
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
	
	public ABNState applyGeneUpdateFunction(String geneName)
	{
		for(Gene g : stateOfGenes)
		{
			if(g.getName().equals(geneName))
			{
				ExpressionTree updateFunction = g.getUpdateFunction();
				boolean result = updateFunction.root.evaluate();
			}	
		}
		

		return null;
	}
	
	
	

}
