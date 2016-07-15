import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		Gene[] genes = GeneFileParser.readGeneInputFile();
		Gene[] genes2 = GeneFileParser.readGeneInputFile();
		ABNState state1 = new ABNState(genes,1,1);
		ABNState state2 = new ABNState(genes2,1,1);
		
		
		
	}

}
