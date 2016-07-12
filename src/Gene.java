
public class Gene {
 private String name = "AGene";
 private boolean expressed;
 private ExpressionTree updateFunction;
 
 Gene (String name, boolean express)
 {
	 this.name = name;
	 this.expressed = express;

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
	 return expressed;
 }
 
 
 public ExpressionTree getUpdateFunction(){
	 return updateFunction;
 }
 
 public void setExpressed(Boolean b){
	 expressed = b;
 }
 public String toString(){
	return name+"  Expressed:"+expressed;
	 
 }
 
 
}
