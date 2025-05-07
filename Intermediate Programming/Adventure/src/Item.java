import java.util.ArrayList;

public class Item {

    private String name_;
    private ArrayList<String> description_;
    private String origin_, destination_;

    public Item(String name, ArrayList<String> description, String origin, String destination) {
    	name_ = name;
    	description_ = description;
    	origin_ = origin;
    	destination_ = destination;
    }
    
    public void look() {
    	
    	System.out.println(name_);
    	for(String l : description_) {
    		System.out.println(l);
    	}
    	
    }
    
}