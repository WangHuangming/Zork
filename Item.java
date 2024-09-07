public class Item {
    //member varibale
    private String name;
    private String type;
    private String description;

    //constructor
    public Item(String pName, String pType, String pDescription) {
        name = pName;
        type = pType;
        description = pDescription;
    }

    //accessor
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    //mutator
    public void setName(String pName) {
        name = pName;
    }
    
    public void setType(String pType) {
        type = pType;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public String toString(){
        return name + " [ " + type + " ]: " + description;
    }
}
