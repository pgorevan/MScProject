/** Class to model a gene regulatory network.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BooleanNetwork {
	
	private Gene[] genes; // a cell genes
	// a list of the different states of gene expression of a cell
	private List<ABNState> listStates; 
	private final int maxNoStates = 100000;
	
	/** Constructor
	 * @param genes	an array of gene objects
	 */	
	public BooleanNetwork(Gene[] genes){
		
		this.genes = genes;
		listStates = new ArrayList<ABNState>();
	}
	
	public Gene[] getGenes(){
		return genes;
	}
	
	/** Generates all the unique states of this boolean network.
	 */	
	public void generateStates()
	{

		// Create the initial state of the network
		ABNState state = new ABNState(genes,0,1);
		listStates.add(state);
		
		// Create a stack of states created and push the initial state to it
		Stack<ABNState> stack = new Stack<ABNState>();
		stack.push(state);
		while(!stack.isEmpty()&&listStates.size()<=maxNoStates)
		{	
			// Get next state
			ABNState next = stack.pop();
			// Ensure that the gene expressions are correct for this state
			next.UpdateGeneState();
			Gene[] nextGenes = next.getGenes();
			// Iterate over the genes in the cell
			for(int i=0; i<nextGenes.length;i++){
				next.UpdateGeneState();
				Gene temp = nextGenes[i];
				// Retrieve the current gene's update function
				ExpressionTree function = temp.getUpdateFunction();
				
				boolean functionresult = function.root.evaluate();
				boolean geneExpressed = temp.checkExpression();
				// Check whether the function changes the gene expression i.e. if the function is enabled
				if(functionresult!=geneExpressed)
				{
					ABNState temp1 =next.applyGeneUpdateFunction(temp.getName());
					boolean stateStored = listStates.contains(temp1);
					// if the state new add it to list of state and push it to stack
					if(!stateStored)
					{	
						stack.push(temp1);
						listStates.add(temp1);
					}
				}

			}
		
		}
		 
	}
	/**  Checks for states where no update functions are enabled
	 * @return ArrayList an arraylist containing the steady states of this network
	 */	
	public ArrayList<ABNState> checkForSteadyStates(){
		
		ArrayList<ABNState> steadyStates = new ArrayList<ABNState>();
		// Iterate over all the states of the network
		for(ABNState state : listStates)
		{
			state.UpdateGeneState();
			Gene[] genes = state.getGenes();

			boolean b = true;
			// For each gene check whether its expression changes when its
			// update function is applied
			for(int i=0; i<genes.length;i++)
			{
				Gene temp = genes[i];
				ABNState temp1 =state.applyGeneUpdateFunction(temp.getName());
				// if the state of cell changes after application then 
				// the state is not a steady state
				if(!state.equals(temp1))
				{
					b= false;
				}
					
			}
			if(b)
			{	
				steadyStates.add(state);
			}
		}
		
		return steadyStates;
	}
	
	
	
	/**  Outputs the boolean network to file
	 * @param filename	String the name of the output file.
	 */	
	public void printToFile(String filename) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(filename,"UTF-8");
		String geneNames ="";
		for(Gene g : genes)
		{
			geneNames += g.getName()+" ";
		}
		writer.println(geneNames);
		// Iterate through the states add each state to line in the file
		Iterator<ABNState> iterator = listStates.iterator();
		while (iterator.hasNext())
		{
			ABNState next = iterator.next();
			String line = next.toString();
			writer.println(line);
		}
		writer.close();
		
	}

}
