import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class TestMain {

	public static void main(String[] args) {
		



		Gene[] genes = GeneFileParser.readGeneInputFile();

		for(Gene g : genes){
			String str = g.getName();
			ExpressionTree tree = g.getUpdateFunction();
			String updateFunction = tree.print();
			System.out.println(g.getName()+" "+updateFunction );
		}
		ABNState state = new ABNState(genes,0,1);
		state.applyGeneUpdateFunction("FOG1");


	}
	

	

}
