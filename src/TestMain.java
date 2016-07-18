import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class TestMain {

	public static void main(String[] args) {
		
		


		Gene[] genes = GeneFileParser.readGeneInputFile();
		Gene GATA = genes[9];
		ExpressionTree function = GATA.getUpdateFunction();

		

		
		
		BooleanNetwork network = new BooleanNetwork(genes);
		network.generateStates();
		int  noSteadystates = network.checkForSteadyStates();


	
		
		System.out.println("Number of steady states is"+noSteadystates);
		try {
			network.printToFile("States.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


		 
	}
	
	

	

}
