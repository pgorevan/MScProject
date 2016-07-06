
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
		else
		{
			((AndNode) temp).setLeftNode(CreateTree(preorder,myindex,temp.leftChild));
			 ((AndNode) temp).setRightNode(CreateTree(preorder,myindex,temp.rightChild));
		}
		
		
			
		return temp;
		
	}
 class Index{
	 int index = 0;
	
}
}
