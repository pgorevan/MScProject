
public class Gene {
 private String name = "AGene";
 private boolean express;
 private ExpressionTree updateFunction;
 
 Gene (String name, boolean express)
 {
	 this.name = name;
	 this.express = express;

 }
 
 public void setUpdateFunction(ExpressionTree function)
 {
	 updateFunction= function;
 }
 
 public String getName(){
	 return name;
 }
 public boolean checkExpression ()
 {
	 return express;
 }
 public ExpressionTree getUpdateFunction(){
	 return updateFunction;
 }
 public String toString(){
	return name+"  Expressed:"+express;
	 
 }
 
 
}
