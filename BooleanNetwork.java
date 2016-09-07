
/** Class to model a gene regulatory network.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BooleanNetwork {

	private Gene[] genes; // a cell's genes
	// a list of the different gene expression profiles
	private List<ABNState> listStates;
	private ABNState currentState;
	// Maximum number of states in this ABN
	private final int maxNoStates = 100000;

	/**
	 * Constructor
	 * 
	 * @param genes
	 *            an array of gene objects
	 */
	public BooleanNetwork(Gene[] genes) {

		this.genes = genes;
		listStates = new ArrayList<ABNState>();
		 currentState = new ABNState(genes, 0, 1);
		listStates.add(currentState);
	}
    
	public ABNState getCurrentState(){
		return currentState;
	}
	
	public Gene[] getGenes() {
		return genes;
	}
	/**
	 * Selects a gene at random at if its update function is enabled
	 * the network transitions to a new state
	 */
	public boolean randomTransition()
	{
		boolean transistion = false;
		currentState.UpdateGeneState();
		Random r = new Random();
		int selected = new Random().nextInt(genes.length);
		Gene gene = genes[selected];

		if (checkFunctionEnabled (gene)) {
		ABNState next = currentState.applyGeneUpdateFunction(gene.getName());
		currentState = next;
		transistion = true;
		}
		return transistion;
	}
	/**
	 * For a given gene checks it functions is enabled and if so 
	 * the network transitions to a new state
	 */
	public boolean TransitionByGene(Gene g){
		currentState.UpdateGeneState();
		boolean transitSuccess = false;
		if (checkFunctionEnabled (g)) {
		ABNState next = currentState.applyGeneUpdateFunction(g.getName());

		currentState = next;
		transitSuccess = true;
		}
		return transitSuccess;
	}
	/**
	 * Generates all the unique states of this boolean network.
	 */
	public void generateStates() {



		// Create a stack of states and push the initial state to it
		Stack<ABNState> stack = new Stack<ABNState>();
		stack.push(currentState);
		while (!stack.isEmpty() && listStates.size() <= maxNoStates) {
			// Get next state
			ABNState next = stack.pop();
			// Ensure that the gene expressions are correct for this state
			next.UpdateGeneState();
			Gene[] nextGenes = next.getGenes();
			// Iterate over the genes in the cell
			for (int i = 0; i < nextGenes.length; i++) {
				next.UpdateGeneState();
				Gene temp = nextGenes[i];
				// Retrieve the current gene's update function
				ExpressionTree function = temp.getUpdateFunction();
				boolean functionresult = function.root.evaluate();
				boolean geneExpressed = temp.checkExpression();
				// Check whether the function changes the gene expression i.e.
				// if the function is enabled
				if (functionresult != geneExpressed) {
					ABNState temp1 = next.applyGeneUpdateFunction(temp.getName());
					boolean stateStored = listStates.contains(temp1);
					// if the state new add it to list of state and push it to
					// stack
					if (!stateStored) {
						stack.push(temp1);
						listStates.add(temp1);
					}
				}

			}

		}

	}

	/**
	 * Checks for states where no update functions are enabled
	 * 
	 * @return ArrayList an arraylist containing the steady states of this
	 *         network
	 */
	public ArrayList<ABNState> checkForSteadyStates() {

		ArrayList<ABNState> steadyStates = new ArrayList<ABNState>();
		// Iterate over all the states of the network
		for (ABNState state : listStates) {
			state.UpdateGeneState();
			Gene[] genes = state.getGenes();

			boolean b = true;
			// For each gene check whether its expression changes when its
			// update function is applied
			for (int i = 0; i < genes.length; i++) {
				Gene temp = genes[i];
				ABNState temp1 = state.applyGeneUpdateFunction(temp.getName());
				// if the state of cell changes after application then
				// the state is not a steady state
				if (!state.equals(temp1)) {
					b = false;
				}

			}
			if (b) {
				steadyStates.add(state);
			}
		}

		return steadyStates;
	}

	/**
	 * Outputs the boolean network to file
	 * 
	 * @param filename
	 *            String the name of the output file.
	 */
	public void printToFile(String filename) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		String geneNames = "";
		for (Gene g : genes) {
			geneNames += g.getName() + " ";
		}
		writer.println(geneNames);
		// Iterate through the states add each state to line in the file
		Iterator<ABNState> iterator = listStates.iterator();
		while (iterator.hasNext()) {
			ABNState next = iterator.next();
			String line = next.toString();
			writer.println(line);
		}
		writer.close();

	}
	public ArrayList<ABNState> cellPeturbation(String gene, boolean expression)
	{
		ArrayList<ABNState>  states= new ArrayList<ABNState>();
		int len = genes.length;
		int posOfGene =-1;
		int pos =0;
		while(!genes[pos].getName().equals(gene))
		{
			posOfGene = pos;
			pos++;

		}
		
		for(int i =0;i<Math.pow(2, len);i++)
		{	
		 boolean [] state = check(i,len);
		 if(state[posOfGene]==expression)
		 {
				listStates.clear();
				ABNState temp =  new ABNState(state, this.genes, 0,1);
				listStates.add(temp);
				this.generateStates();
			    List<ABNState> currentSS =this.checkForSteadyStates();
			    for(ABNState s : currentSS)
			    {
			    	if(!states.contains(s))
			    		states.add(s);
			    }
		 }
		}
		return states;
	}
	private  boolean[]   check(int num,int size)
	{
		// Take your input integer
	    int input = num;

	    boolean[] bits = new boolean[size];
	    for (int i = size-1; i >= 0; i--) {
	        bits[i] = (input & (1 << i)) != 0;
	    }

	    return bits;

	}
	private boolean checkFunctionEnabled(Gene gene)
	{
		ExpressionTree function = gene.getUpdateFunction();
		boolean functionresult = function.root.evaluate();
		boolean geneExpressed = gene.checkExpression();
		return (functionresult != geneExpressed);

	}

}
