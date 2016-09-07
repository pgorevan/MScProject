/**  A simple user interface to demonstrate the application.
 *   The user can select various options and the application performs them.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Console {
	
	public Console()
	{
		
	}
	/** Outputs the options available to the user
	 *  Then calls the operations the user requests
	 */		
	public void userSelection()
	{
        // Print the user options
        System.out.println("Options:");
        System.out.println("1. Create Boolean Network from 'GeneData.csv'");
        System.out.println("2. Create Random Boolean Network");
        // Collect the user choice
        int selection = readInput();
        // Perform the selected operation
        switch(selection){
        case 1:
        	createNetwork();
        	break;
        case 2:
        // Create a random network	
        	BooleanNetwork network = createRandomNetwork();
        // Check if network has steady states	
    		network.generateStates();
    		ArrayList<ABNState> states = network.checkForSteadyStates();
    		Gene[] genes = network.getGenes();
    		String geneNames = genesToString(genes);
    		System.out.println("Network Created");
    	// Print names of genes used in the network	
    		System.out.println(geneNames);
    	// Print which states are steady	
    		printStates(states);
        	break;
        default:
        // If invalid input prompt user to reenter selectoin	
        	System.out.println("Not Valid Selection");
        	this.userSelection();
        		
        }

	}
	/** Creates a boolean network from the file 'GeneData.csv'
	 *  Then asks user what they would like to do with the network
	 */	
	private void createNetwork(){
		// Parse data file to create an array of Gene Objects
		Gene[] geneData = FileParser.readGeneData("GeneData.csv");
		// Create a boolean network using the gene data
		BooleanNetwork network = new BooleanNetwork(geneData);
		network.generateStates();
		System.out.println("Network Created");
		// Give user more options
		System.out.println("Options:");
		System.out.println("1. Output states to file 'states.txt'");
		System.out.println("2. Check for steady states");
		int selection = readInput(); 
        switch(selection){
        case 1:
        	try {
        // Output the states of the network to file		
				network.printToFile("states.txt");
				System.out.println("State data written to file");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Writing to file was unsuccessful");
			}
        	break;
        case 2:
        // Check the network for steady states and output them	
        	ArrayList<ABNState> steadyStates = network.checkForSteadyStates();
        	System.out.println("Number of steady states in Network: "+steadyStates.size());
        	printStates(steadyStates);
        	break;
        default:
        	System.out.println("Not Valid Selection");
        	this.userSelection();
        	

        		
        }
		
		
	}
	/** Creates a random boolean network seeded from a list of gene names
	 *  
	 */		
	private BooleanNetwork createRandomNetwork(){
		// Generate a set of gene names that could be used 
		Set<Gene> AllGenes = FileParser.readGeneNameFile();
        // Set the number of genes
		int NoGenesUsed = 10;
		Random r = new Random();
		Gene[] geneArray = new Gene[NoGenesUsed];
		// Randomly select names of genes to be used 
		for(int i=0; i<NoGenesUsed; i++)
		{
			int randomNum = r.nextInt(AllGenes.size());
			int count = 0;
			Iterator<Gene> iter = AllGenes.iterator();
			while(iter.hasNext())
			{
				if(count==randomNum)
				{
					Gene g = iter.next();
					geneArray[i] = g;
					AllGenes.remove(g);
					break;
				}
				else
				{
					count++;
					iter.next();
				}
			}
			
		}
		// Create a set of Gene object to be used when generating update functions
		Set<Gene> GeneSet = new HashSet<Gene>();
		for(Gene g : geneArray)
		{
			GeneSet.add(g);
		}
		// For each Gene create a update function
		for(Gene g : geneArray)
		{
			// Copy the set of Gene's to be used
			Set<Gene> copyOfGenes = new HashSet<Gene>();
			copyOfGenes.addAll(GeneSet);
			// Create update function
			ExpressionTree function = UpdateFunctionGenerator.createUpdateFunction(3, 3, copyOfGenes);
			// Set update function
			g.setUpdateFunction(function);
		}
		// Create and return boolean network
		BooleanNetwork network = new BooleanNetwork(geneArray);
		return network;		
		
	}
	/** Prompts the user to enter an selection and returns it
	 *  @return int  Representing the users selection
	 */	
	private int readInput(){
		 int selection  =0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
        System.out.println("Enter Selection:");
        
          selection = Integer.parseInt(br.readLine());


        }catch(NumberFormatException | IOException nfe){
            System.out.println("Invalid Selection");
            this.userSelection();
        }
		return selection;
		
	}
	
	/** Prints each of the states of a given list
	 *  @param ArrayList<ABNState>  List of states to print
	 */	
	private void printStates(ArrayList<ABNState> states)
	{
		if(!states.isEmpty())
		{	
			Gene[] genes = states.get(0).getGenes();
			String geneNames="";
			for(Gene g : genes)
			{
				g.getName();
				 geneNames += String.format("%-9s ", g.getName());
				
			}
			System.out.println(geneNames);
			for(ABNState s : states)
			{
				System.out.println(s.toString2());
			}
		}
	}
	/** Gets the name of each Gene and prints all of them
	 *  out for a given array of Genes
	 *  @param Gene[]  Array of Gene objects
	 */	
	private String genesToString(Gene[] genes)
	{
		String str = "";
		for(Gene g : genes)
			str += g.getName()+" ";
		return str;
	}
}
