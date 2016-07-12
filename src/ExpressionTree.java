import java.util.Iterator;
import java.util.Stack;

public class ExpressionTree {
	ExpressionTreeNode root;
	Index myindex = new Index();
	
	
	public ExpressionTreeNode createTreeFirst( ExpressionTreeNode[] preorder)
	{
		root = preorder[0];
		int noOfNodes = preorder.length; 
		int index = 0;
		return CreateTree(preorder,myindex,root);
		
	}
	 
	public boolean isEmpty(){
		return root==null;
		
	}
	
	public String print(){
		String str = "";
		String  result = inOrderIter(root,str);
		return result;
	}
	private String inOrderIter(ExpressionTreeNode root, String output) {  
		  
		  if(root == null)  
		   return output;  
		  
		  Stack<ExpressionTreeNode> s = new Stack<ExpressionTreeNode>();  
		  ExpressionTreeNode currentNode=root;  
		  
		  while(!s.empty() || currentNode!=null){  
		  
		   if(currentNode!=null)  
		   {  

			   s.push(currentNode);  
		    currentNode=currentNode.leftChild; 

		    
		   }  
		   else  
		   {  
			   
			   ExpressionTreeNode n=s.pop();

		    output = output+n.toString()+" ";
		    currentNode=n.rightChild;
		    
	    		
		   }  
		  }
		return output;  
		 }


	private ExpressionTreeNode CreateTree(ExpressionTreeNode[] preorder,Index pos,ExpressionTreeNode node)
	{
		int i = pos.index;
		if(i==preorder.length)
			return null;
		
		ExpressionTreeNode temp = preorder[i];
		pos.index++;
		if(temp instanceof VarNode)
		{
			  temp = (VarNode) temp;
		}
		if(preorder[i] instanceof VarNode)
		{
			
		}else if(preorder[i] instanceof OrNode)
		{
			 ((OrNode) temp).setLeftNode(CreateTree(preorder,myindex,temp.leftChild));
			 ((OrNode) temp).setRightNode(CreateTree(preorder,myindex,temp.rightChild));
		}
		else if(preorder[i] instanceof AndNode)
		{
			((AndNode) temp).setLeftNode(CreateTree(preorder,myindex,temp.leftChild));
			 ((AndNode) temp).setRightNode(CreateTree(preorder,myindex,temp.rightChild));
		}
		else
		{
			((NotNode) temp).setNode(CreateTree(preorder,myindex,temp.rightChild));
		}
		
		
			
		return temp;
		

	
		
		

	}
	
	public Iterator<ExpressionTreeNode> iterator(){
		return new UDFIterator();
		
	}
	class UDFIterator implements Iterator<ExpressionTreeNode>{
		Stack<ExpressionTreeNode> stack;
		
		public UDFIterator()
		{
			ExpressionTreeNode currentNode = root;
			stack = new Stack<ExpressionTreeNode>();
			while(currentNode!=null){
				stack.push(currentNode);
				currentNode = currentNode.leftChild;
			}
		}
		public boolean hasNext(){
			return !stack.isEmpty();
		}
		public ExpressionTreeNode next(){
			ExpressionTreeNode node = stack.pop();
			if(node.rightChild!=null){
				ExpressionTreeNode temp  = node.rightChild;
				while(temp !=null){
					stack.push(temp);
					temp = temp.leftChild;
				}
					
			}
			return node;	
		}
		
	}

	
 class Index{
	 int index = 0;
	
}
}
