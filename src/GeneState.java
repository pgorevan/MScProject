import java.util.Random;

public class GeneState {
	private boolean[] geneArray;
	
	public GeneState(int noGenes){
		
		geneArray = new boolean[noGenes];
		this.generateRandomValues();
		
	}

	private void generateRandomValues()
	{
		Random r = new Random();
		for(int i=0;i<geneArray.length;i++)
		{
			geneArray[i] = r.nextBoolean();
		}
	}
	
	public boolean getValue(int i)
	{
		System.out.println(geneArray[i]);
		return geneArray[i];
	}
}
