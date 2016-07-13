import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
		System.out.println("Inital State looks like this: "+state.toString());
		
		Queue<ABNState>  processStates = new LinkedList<ABNState>();
		processStates.add(state);
		
		int depth = 0;
		while(!processStates.isEmpty()&&depth<10000)
		{	
			ABNState next = processStates.remove();
			Gene[] nextGenes = next.getGenes();
			for(int i=0; i<nextGenes.length;i++){
				Gene temp = genes[i];
				ABNState temp1 =state.applyGeneUpdateFunction(temp.getName());

				if(!state.equals(temp1)||!(listStates.contains(temp1)))
				{
					processStates.add(temp1);
					System.out.println(temp1.toString());
					
					listStates.add(temp1);
				}
				else{
					System.out.println("Update function not enabled");
				}
				
				depth++;
			}
		
		}
		 

		 System.out.println("End"+listStates.size());
	}

}
