
public class Gene {
 private String name = "AGene";
 private boolean express;
 
 Gene (String name, boolean express)
 {
	 this.name = name;
	 this.express = express;
	 System.out.println("Gene "+name+" Expressed? "+express);
 }
 
 public String getName(){
	 return name;
 }
 public boolean checkExpression ()
 {
	 return express;
 }
 
 
}
