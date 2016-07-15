import java.util.Iterator;

public class ABNState {
	private int stateID;
	private Gene[] stateOfGenes;
	private int time;
	private boolean[] boolArray;
	
	public ABNState(Gene[] initialState,int time, int stateID)
	{
		stateOfGenes = initialState;
		this.time = time ;
		this.stateID = stateID;
		boolArray = new boolean[stateOfGenes.length];
		for(int i= 0;i<stateOfGenes.length;i++)
		{
			boolArray[i] = stateOfGenes[i].checkExpression();
		}

	}
	
	public ABNState (boolean[] boolState, Gene[] genes, int time, int stateID)
	{
		boolArray = boolState;
		stateOfGenes = genes;
		this.time = time;
		this.stateID= stateID;
		int i =0;
		for(Gene g : genes)
		{
			boolean b = boolState[i];
			g.setExpressed(b);
			i++;
			
		}
			
	}
	


	public Gene[] getGenes(){
		return stateOfGenes;
	}
	
	public ABNState applyGeneUpdateFunction(String geneName)
	{
		UpdateGeneState();
		boolean[] nextState = boolArray.clone();
		for(Gene g : stateOfGenes)
		{
			if(g.getName().equals(geneName))
			{

				ExpressionTree updateFunction = g.getUpdateFunction();
				boolean result = updateFunction.root.evaluate();
				int indexOfGene = findGene(geneName);
				nextState[indexOfGene] = result;
			}
		}

		ABNState successor = new ABNState(nextState,stateOfGenes, time+1, Counter.counter++);
		return successor;
	}
	
	public String toString(){
		String result =""+stateID+time+"\n";
		int i =0;
		for(Gene gene : stateOfGenes)
		{
			result += gene.getName()+","+boolArray[i]+"\n"; 
			i++;
		}
		return result;
	}
@Override	
	public boolean equals(Object input){
	
		if(input==null || !(input instanceof ABNState))
			return false;
		ABNState that =(ABNState) input;
		int i = 0;
		for(boolean b: boolArray)
		{
			if(b!=that.boolArray[i])
				return false;
			i++;
		}
	
	return true;
		
	}


	private int findGene(String s)
	{
		int i =0;
		for(Gene g : stateOfGenes)
		{
			if(g.getName().equals(s))
				return i;
			else
				i++;
		}
		return i;
	}
	public void UpdateGeneState()
	{
		int i = 0;
		for(Gene g : stateOfGenes)
		{
			boolean b = boolArray[i];
			g.setExpressed(b);
			i++;
			
		}
	}


}
