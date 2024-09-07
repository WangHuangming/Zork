import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    //member variable
    private String name;
    private String description;
    private ArrayList<Item> storeItems;
    private HashMap<String,Location> connections;

    //constructor
    public Location(String pName, String pDescription) {
        name = pName;
        description = pDescription;
        storeItems = new ArrayList<Item>();
        connections = new HashMap<String,Location>();
    }
    //accessor
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //mutator
    public void setName(String pName) {
        name = pName;
    }
    
    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public void addItem(Item pItem) {
        storeItems.add(pItem);
    }

    public boolean hasItem(String target) {
        for (int i = 0; i < storeItems.size() ; i++) {
            if (storeItems.get(i).getName().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String target) {
        for (int i = 0; i < storeItems.size(); i++) {
            if (storeItems.get(i).getName().equalsIgnoreCase(target)) {
                return storeItems.get(i);
            }
        }
        return null;
    }

    public Item getItem(int index) {
        if (index < storeItems.size() && index >= 0) {
            return storeItems.get(index);
        }
        else {
            return null;
        }
    }

    public int numItems(Location pLocation) {
        return storeItems.size();
    }

    public Item removItem(String target) {
        for (int i = 0; i < storeItems.size(); i++) {
            if (storeItems.get(i).getName().equalsIgnoreCase(target)) {
                Item removedItem = storeItems.get(i);
                storeItems.remove(i);
                return removedItem;
            }
        }
        return null;
    }

    public void connect(String direction, Location pLocation) {
        connections.put(direction, pLocation);
    }

    public boolean canMove(String direction) {
        switch(direction) {
            case "south":
            if (connections.get(direction) == null) {
                System.out.println("Valid direction, but nothing there.");
                return false;
            }
            else{
                return true;
            }
            case "north":
            if (connections.get(direction) == null) {
                System.out.println("Valid direction, but nothing there.");
                return false;
            }
            else{
                return true;
            }
            case "east":
            if (connections.get(direction) == null) {
                System.out.println("Valid direction, but nothing there.");
                return false;
            }
            else{
                return true;
            }
            case "west":
            if (connections.get(direction) == null) {
                System.out.println("Valid direction, but nothing there.");
                return false;
            }
            else{
                return true;
            }
            default:
            System.out.println("Invalid direction.");
            return false;
        }
    }

    public Location getLocation(String direction) {
        if (canMove(direction)) {
            return connections.get(direction);
        }
        return null;
    }
}