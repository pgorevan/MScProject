import java.util.Iterator;
import java.util.Stack;

public class ExpressionTree {
	ExpressionNode root;
	Index myindex = new Index();
	
	
	public ExpressionNode createTree( ExpressionNode[] preorder)
	{
		root = preorder[0];
		int noOfNodes = preorder.length; 
		int index = 0;
		return createTreePrivate(preorder,myindex,root);
		
	}
	 
	public boolean isEmpty(){
		return root==null;
		
	}
	
	public String print(){
		String str = "";
		String  result = inOrderIter(root,str);
		return result;
	}
	private String inOrderIter(ExpressionNode root, String output) {  
		  
		  if(root == null)  
		   return output;  
		  
		  Stack<ExpressionNode> s = new Stack<ExpressionNode>();  
		  ExpressionNode currentNode=root;  
		  
		  while(!s.empty() || currentNode!=null){  
		  
		   if(currentNode!=null)  
		   {  

			   s.push(currentNode);  
		    currentNode=currentNode.leftChild; 

		    
		   }  
		   else  
		   {  
			   
			   ExpressionNode n=s.pop();

		    output = output+n.toString()+" ";
		    currentNode=n.rightChild;
		    
	    		
		   }  
		  }
		return output;  
		 }


	private ExpressionNode createTreePrivate(ExpressionNode[] preorder,Index pos,ExpressionNode node)
	{
		int i = pos.index;
		if(i==preorder.length)
			return null;
		
		ExpressionNode temp = preorder[i];
		pos.index++;
		if(temp instanceof VarNode)
		{
			  temp = (VarNode) temp;
		}
		if(preorder[i] instanceof VarNode)
		{
			
		}else if(preorder[i] instanceof OrNode)
		{
			 ((OrNode) temp).setLeftNode(createTreePrivate(preorder,myindex,temp.leftChild));
			 ((OrNode) temp).setRightNode(createTreePrivate(preorder,myindex,temp.rightChild));
		}
		else if(preorder[i] instanceof AndNode)
		{
			((AndNode) temp).setLeftNode(createTreePrivate(preorder,myindex,temp.leftChild));
			 ((AndNode) temp).setRightNode(createTreePrivate(preorder,myindex,temp.rightChild));
		}
		else
		{
			((NotNode) temp).setNode(createTreePrivate(preorder,myindex,temp.rightChild));
		}
		
		
			
		return temp;
		

	
		
		

	}
	
	public Iterator<ExpressionNode> iterator(){
		return new UDFIterator();
		
	}
	class UDFIterator implements Iterator<ExpressionNode>{
		Stack<ExpressionNode> stack;
		
		public UDFIterator()
		{
			ExpressionNode currentNode = root;
			stack = new Stack<ExpressionNode>();
			while(currentNode!=null){
				stack.push(currentNode);
				currentNode = currentNode.leftChild;
			}
		}
		public boolean hasNext(){
			return !stack.isEmpty();
		}
		public ExpressionNode next(){
			ExpressionNode node = stack.pop();
			if(node.rightChild!=null){
				ExpressionNode temp  = node.rightChild;
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
