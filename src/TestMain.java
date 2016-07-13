

public class TestMain {

	public static void main(String[] args) {
		



		Gene[] genes = GeneFileParser.readGeneInputFile();	
		
		
		BooleanNetwork network = new BooleanNetwork(genes);
		network.generateStates();

//		for(Gene g : genes){
//			String str = g.getName();
//			ExpressionTree tree = g.getUpdateFunction();
//			String updateFunction = tree.print();
//			System.out.println(g.getName()+" "+updateFunction );
//		}

		 
	}
	
	

	

}
