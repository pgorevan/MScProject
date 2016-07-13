
public class Gene {
 private String name;
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
 public Gene clone(){
	 Gene clone = new Gene(this.name,this.expressed);
	 clone.setUpdateFunction(this.updateFunction);
	return clone;
	 
 }
 
}
