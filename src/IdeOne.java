
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class IdeOne {
	public class Node {
		 
		public int data;
		public Node left;
		public Node right;
 
		public Node(int newdata){
			this.data = newdata;
			this.left = this.right = null;
		}
 
		public String toString(){
			return String.valueOf(data);
		}
 
		public Node clone(){
 
			Node nNode = new Node(this.data);
			if(this.left != null) nNode.left = this.left.clone();
			if(this.right != null) nNode.right = this.right.clone();
 
			return nNode;
		}
 
	}
 
	public void createAllTopologies(int n){
 
		if(n%2 == 0) return;
 
		Map<Integer, List<Node>> topologies = new HashMap<Integer, List<Node>>();
 
		topologies.put(1, new ArrayList<Node>());
		topologies.get(1).add(new Node(1));
 
		for(int i=3;i<=n;i+=2){
 
			List<Node> list = new ArrayList<Node>();
 
			for(int j=1;j<i;j+=2){
				List<Node> left = topologies.get(j);
				List<Node> right = topologies.get(i-j-1);
				list.addAll(generateAllCombinations(left,right));
			}
			topologies.put(i, list);
		}
 
		List<Node> result = topologies.get(n);
 
		for(int i=0;i<result.size();i++){
			printTree(result.get(i),0);
			System.out.println("-----------------------------");
		}
 
	}
 
	private List<Node> generateAllCombinations(List<Node> left, List<Node> right) {
 
		List<Node> list = new ArrayList<Node>();
		for(int i=0;i<left.size();i++){
			for(int j=0;j<right.size();j++){
				Node nNode = new Node(1);
				nNode.left = left.get(i).clone();
				nNode.right = right.get(j).clone();
				list.add(nNode);
			}
		}
		return list;
	}
 
	protected void printTree(Node nNode,int pos){
		if (nNode==null) {
			for(int i=0;i<pos;i++) System.out.print("\t");
			System.out.println("*");
			return;
		}
		printTree(nNode.right,pos+1);
		for(int i=0;i<pos;i++) System.out.print("\t");
		System.out.println(nNode.data);
		printTree(nNode.left,pos+1);
 
	}
 
	public static void main (String[] args) throws java.lang.Exception
	{
		IdeOne topologyCreator = new IdeOne();
		topologyCreator.createAllTopologies(5);
	}

}
