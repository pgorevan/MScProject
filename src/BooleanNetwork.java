import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BooleanNetwork {
	private Gene[] genes;
	private List<ABNState> listStates;
	
	public BooleanNetwork(Gene[] genes){
		
		this.genes = genes;
		listStates = new ArrayList<ABNState>();
	}
	
	public void generateStates()
	{
		ABNState state = new ABNState(genes,0,1);
		listStates.add(state);
		Stack<ABNState> stack = new Stack<ABNState>();
		stack.push(state);
		
		while(!stack.isEmpty())
		{	
			ABNState next = stack.pop();
			next.UpdateGeneState();
			Gene[] nextGenes = next.getGenes();
			
			for(int i=0; i<nextGenes.length;i++){
				next.UpdateGeneState();
				Gene temp = nextGenes[i];
				ExpressionTree function = temp.getUpdateFunction();
				boolean functionresult = function.root.evaluate();
				boolean geneExpressed = temp.checkExpression();
				
				if(functionresult!=geneExpressed)
				{
					ABNState temp1 =next.applyGeneUpdateFunction(temp.getName());
					boolean stateStored = listStates.contains(temp1);
					if(!stateStored)
					{	
						stack.push(temp1);
					listStates.add(temp1);
					}
				}

			}
		
		}
		 
	}
	
	public int checkForSteadyStates(){

		int number = 0;
		for(ABNState state : listStates)
		{
			state.UpdateGeneState();
			Gene[] genes = state.getGenes();

			boolean b = true;
			for(int i=0; i<genes.length;i++)
			{
				Gene temp = genes[i];
				ABNState temp1 =state.applyGeneUpdateFunction(temp.getName());

				if(!state.equals(temp1))
				{
					b= false;
				}
					
			}
			if(b)
			{	
				System.out.println(state.toString2());
				number++;
			}
		}
		System.out.print("Number of state "+listStates.size());
		return number;
	}
	
	public void printToFile(String filename) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(filename,"UTF-8");
		int i = 1;
		String geneNames ="ID    ";
		for(Gene g : genes)
		{
			String temp = String.format("%-8s", g.getName()) ;
			geneNames += temp;
		}
		writer.println(geneNames);
		Iterator<ABNState> iterator = listStates.iterator();
		while (iterator.hasNext())
		{
			String stateid =String.format("%-6d",i);
			ABNState next = iterator.next();
			String line = stateid+next.toString2();
			writer.println(line);
			i++;
		}
		writer.close();
		
	}

}
