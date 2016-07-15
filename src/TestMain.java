

public class TestMain {

	public static void main(String[] args) {
		
		


		Gene[] genes = GeneFileParser.readGeneInputFile();
		Gene GATA = genes[9];
		ExpressionTree function = GATA.getUpdateFunction();

		

		
		
		BooleanNetwork network = new BooleanNetwork(genes);
		network.generateStates();
		int  noSteadystates = network.checkForSteadyStates();


	
		
		System.out.println("Number of steady states is"+noSteadystates);
		
		


		 
	}
	
	

	

}
