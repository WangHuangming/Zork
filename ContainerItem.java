import java.util.ArrayList;

public class ContainerItem extends Item {
    //member variable
    private ArrayList<Item> items;
    //constructor
    public ContainerItem(String pName, String pType, String pDescription) {
        super(pName,pType,pDescription);
        items = new ArrayList<Item>();
    }

    public void addItem(Item pItem) {
        items.add(pItem);
    }

    public boolean hasItem(String target) {
        for (int i = 0; i < items.size() ; i++) {
            if (items.get(i).getName().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public Item removItem(String target) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(target)) {
                Item removedItem = items.get(i);
                items.remove(i);
                return removedItem;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(200 + 40 * items.size());
        builder.append(getName() + " [ " + getType() + " ]: " + getDescription() + "\nIt contains:\n");

        for(int i = 0; i < items.size(); i++) {
            builder.append("+ " + items.get(i).getName() + "\n");
        }

        String result = builder.toString();
        return result;
    }
}