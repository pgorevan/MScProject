/** ABNState encapsulates the information pertaining to a state in a boolean network
 *
 * @author Patrick Gorevan.
 */

public class ABNState {
	private int stateID;
	private Gene[] geneArray;
	private int time;
	private boolean[] stateOfGenes;
	
	
	/** Constructor
	 * @param genes			An array of genes
	 * @param time			An int representing time stamp of when state was created
	 * @param stateID		An int for identifying this state;	
	 */		
	public ABNState(Gene[] initialState,int time, int stateID)
	{
		geneArray = initialState;
		this.time = time ;
		this.stateID = stateID;
		stateOfGenes = new boolean[geneArray.length];
		for(int i= 0;i<geneArray.length;i++)
		{
			stateOfGenes[i] = geneArray[i].checkExpression();
		}

	}
	/** Constructor
	 * @param boolState		An array of boolean variables
	 * @param genes			An array of genes
	 * @param time			An int representing time stamp of when was state created
	 * @param stateID		An int for identifying this state;	
	 */	
	public ABNState (boolean[] boolState, Gene[] genes, int time, int stateID)
	{
		stateOfGenes = boolState;
		geneArray = genes;
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
	
	/** Getter Method
	 * @return geneArray array of Gene objects		
	 */	
	public Gene[] getGenes(){
		return geneArray;
	}
	
	/** For a given gene apply its update function to this ABNState
	 * 
	 * @param geneName		String of gene name whose update function should be applied
	 * @return successor	A ABNState created by the application of the update function
	 */
	public ABNState applyGeneUpdateFunction(String geneName)
	{
		UpdateGeneState();
		boolean[] nextState = stateOfGenes.clone();
		for(Gene g : geneArray)
		{
			if(g.getName().equals(geneName))
			{

				ExpressionTree updateFunction = g.getUpdateFunction();
				boolean result = updateFunction.root.evaluate();
				int indexOfGene = findGene(geneName);
				nextState[indexOfGene] = result;
			}
		}

		ABNState successor = new ABNState(nextState,geneArray, time+1, Counter.counter++);
		return successor;
	}
	
	/** Converts the ABNState data to string
	 * 
	 * @return result String containing the state id and time created and whether each gene is expressed
	 */
	public String toString(){
		String result =stateID+","+time;

		
		int i =0;
		for(Gene gene : geneArray)
		{
			result += ","+stateOfGenes[i]; 
			i++;
		}
		return result;
	}
	public String toString2(){
		String result ="";

		
		int i =0;
		for(Gene gene : geneArray)
		{
			result += String.format("%-9s ", stateOfGenes[i]); 
			i++;
		}
		return result;
	}
	
	/** Checks if this ABNState is equal to another
	 * 
	 * @param input		ABNState object		
	 * @return boolean	True if equal false otherwise
	 */	
@Override	
	public boolean equals(Object input){
	
		if(input==null || !(input instanceof ABNState))
			return false;
		ABNState that =(ABNState) input;
		int i = 0;
		for(boolean b: stateOfGenes)
		{
			if(b!=that.stateOfGenes[i])
				return false;
			i++;
		}
	
	return true;
		
	}

/** Finds the index value of a gene matching a string
 * 
 * @param s		String of the gene name to be found
 * @return i 	int index value of the gene in geneArray
 */
	private int findGene(String s)
	{
		int i =0;
		for(Gene g : geneArray)
		{
			if(g.getName().equals(s))
				return i;
			else
				i++;
		}
		return i;
	}
	
	/** Update Gene expressions to match the values in stateOfGenes

	 */
	public void UpdateGeneState()
	{
		int i = 0;
		for(Gene g : geneArray)
		{
			boolean b = stateOfGenes[i];
			g.setExpressed(b);
			i++;
			
		}
	}


}
