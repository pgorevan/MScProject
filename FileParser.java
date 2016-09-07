
/** Class that groups together various file parsers
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class FileParser {
	/**
	 * Parses a file containing gene data and returns a usable array of genes
	 * 
	 * @param fileName
	 *            String of the name of the file containing the gene data
	 * @return geneArray Gene[] an array of gene object
	 */
	public static Gene[] readGeneData(String fileName) {
		// Collect the gene data from the data file
		List<String> geneList = convertDataFileToArrayList(fileName);
		geneList.size();
		Gene[] geneArray = new Gene[geneList.size()];
		// For each line of gene data convert it into a gene object
		for (int i = 0; i < geneList.size(); i++) {

			String geneStr = geneList.get(i);
			String[] sections;
			sections = geneStr.split(",");
			String geneName = sections[0];
			Gene gene = getOrCreateGene(geneName, geneArray);
			//Set gene intial expression
			String strGeneExpressed = sections[1];
			Boolean geneExpressed = Boolean.valueOf(strGeneExpressed);
			gene.setExpressed(geneExpressed);
			geneArray[i] = gene;
		}
		// create and assign the update functions of the genes
		insertUpdateFunctions(geneList, geneArray);
		return geneArray;
	}
	/**
	 * Converts the gene's update function from the String version to a expression tree
	 * 
	 * @param geneList a list containing the strings representation of gene data
	 * @param geneArray  the array of gene objects without their update functions          
	 * @return geneArray Gene[] an array of gene object
	 */
	public static void insertUpdateFunctions(List<String> geneList, Gene[] geneArray) {
		// For each gene  get the string containing its function
		for (int i = 0; i < geneList.size(); i++) {
			ArrayList<ExpressionNode> arrayVersion;
		// Create an arrayList to hold the function data	
			arrayVersion = new ArrayList<ExpressionNode>();
			Gene gene = geneArray[i];
			String geneStr = geneList.get(i);
			String[] sections;
		//Split the function into its constituent parts	
			sections = geneStr.split(",");
			String function = sections[2];
			String[] parts = function.split(" ");
		// check what each part is and create the correct type of expression Node
			for (String s : parts) {
				ExpressionNode temp;
				switch (s) {
				case "AND":
					temp = new AndNode(null, null);
					arrayVersion.add(temp);
					break;
				case "OR":
					temp = new OrNode(null, null);
					arrayVersion.add(temp);
					break;
				case "NOT":
					temp = new NotNode(null);
					arrayVersion.add(temp);
					break;
				default:
					Gene geneVar = getOrCreateGene(s, geneArray);
					temp = new VarNode(geneVar);
					arrayVersion.add(temp);
				}

			}
			ExpressionNode[] a = arrayVersion.toArray(new ExpressionNode[arrayVersion.size()]);
			ExpressionTree t = new ExpressionTree();
			// Create a expressionTree which represents an update function
			t.createTree(a);
			// Set the current gene's update function
			gene.setUpdateFunction(t);

		}
	}

	/**
	 * Opens a given file and converts it to list of strings. 
	 * Where each string contains the data about a single gene
	 * 
	 * @param fileName String of the name of file containing the data
     
	 * @return GeneList  List containing the gene data.
	 */
	private static List<String> convertDataFileToArrayList(String fileName) {
		List<String> GeneList = new ArrayList<String>();
		FileReader fr = null;
		Scanner sc;
		try {
			fr = new FileReader(fileName);
			sc = new Scanner(fr);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				GeneList.add(line);

			}
		fr.close();
		sc.close();
		} catch (IOException e) {
			System.err.println("File not found");
		} 
		return GeneList;

	}
	
	/**
	 * For a given string of a gene name checks whether a gene with that name
	 * already exists if it does it returns the gene object if not it creates a
	 * gene object and returns it
	 * 
	 * @param name String name of the gene
	 * @return g Gene object.
	 */
	private static Gene getOrCreateGene(String name, Gene[] arrayOfGenes) {
		for (Gene g : arrayOfGenes) {

			if (g != null && name.equals(g.getName()))
				return g;
		}
		Gene g = new Gene(name, false);
		return g;

	}
	/**
	 * Opens a file containing a list of genes names and creates a set of gene objects
	 * @return setOfGenes  Set<Gene>.
	 */	

	public static Set<Gene> readGeneNameFile() {

		Set<Gene> setOfGenes = new HashSet<Gene>();

		FileReader fr;
		try {
			fr = new FileReader("listOfGeneNames.txt");
			Scanner sc = new Scanner(fr);
			Random r = new Random();
			while (sc.hasNextLine()) {

				String geneName = sc.nextLine();

				Gene g = new Gene(geneName, r.nextBoolean());
				setOfGenes.add(g);
			}
			fr.close();
			sc.close();
		} catch (IOException e) {

			System.out.println("Could not read list of gene names.");
		}

		return setOfGenes;

	}

}
