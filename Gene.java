/**  Class which represents a cells gene
 *
 */
public class Gene {
 private String name;
 private boolean expressed;
 private ExpressionTree updateFunction;
 
 /**  Constructor
 * 
 */
 Gene (String name, boolean express)
 {
	 this.name = name;
	 this.expressed = express;

 }
 /**  Sets this gene's update function
 * @param function ExpressionTree representing an update function
 */
 public void setUpdateFunction(ExpressionTree function)
 {
	 updateFunction= function;
 }
 /**  Returns the name of this Gene
 * @return name String of the gene's name
 */
 public String getName(){
	 return name;
 }
 /**  Checks the expression of this Gene
 * @return expressed boolean this Gene's expression
 */
 public boolean checkExpression ()
 {
	 return expressed;
 }
 
 /**  Retrieves this Genes update function
 * @return updateFunction ExpressionTree this Gene's update function
 */
 public ExpressionTree getUpdateFunction(){
	 return updateFunction;
 }
 /**  Set this gene's expression
 * @param b boolean 
 */
 public void setExpressed(boolean b){
	 expressed = b;
 }
 /**  Converts this gene data to String
 * @return String this gene's name and expression
 */
 public String toString(){
	return name+"  Expressed:"+expressed;
	 
 }
 /**  Clones this Gene object and returns a new Gene Object with the same properties
 * @return clone Gene with the same name, function and expression as this Gene
 */
 public Gene clone(){
	 Gene clone = new Gene(this.name,this.expressed);
	 clone.setUpdateFunction(this.updateFunction);
	return clone;
	 
 }
 
}
