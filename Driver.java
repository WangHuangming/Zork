import java.util.Scanner;

public class Driver {
    private static Location currLocation;
    private static ContainerItem myInventory = new ContainerItem("myInventory", "Storage", "Like a Backpack");

    public static void createWorld() {
        //add locations
        Location Kitchen = new Location("Kitchen","A normal village kitchen");
        Location Hallway = new Location("Hallway","A Hallway");
        Location Bedroom = new Location("Bedroom","A bedroom");
        Location Bathroom = new Location("Bathroom","A bathroom");

        //build connections
        Hallway.connect("south",Bedroom);
        Hallway.connect("north", Kitchen);
        Hallway.connect("west", Bathroom);
        Kitchen.connect("south", Hallway);
        Bathroom.connect("east", Hallway);
        Bedroom.connect("north", Hallway);
        
        //add items
        //kitchen stuff
        Item Knife = new Item("Knife", "kitchen utensil", "Very sharp!");
        Item Turkey = new Item("Turkey", "Food", "Delicious!");
        Item Plate = new Item("Plate", "kitchen utensil", "It made of gold!");
        Kitchen.addItem(Knife);
        Kitchen.addItem(Turkey);
        Kitchen.addItem(Plate);
        
        //bathroom stuff
        Item Toothbrush = new Item("Toothbrush", "Tool", "Good!");
        Item Key = new Item("Key", "Tool", "Wonder where this goes?");
        Bathroom.addItem(Toothbrush);
        Bathroom.addItem(Key);

        //bedroom stuff
        Item Bed = new Item("Bed", "Furniture", "So comfortable!");
        Item Pen = new Item("Pen", "Study tool", "Necessary tool.");

        //Chest stuff
        ContainerItem Chest = new ContainerItem("Chest", "Funiture", "It can store clothes.");
        Item Shirt = new Item("Shirt", "cloth", "A white shirt.");
        Chest.addItem(Shirt);

        //desk stuff
        ContainerItem Desk = new ContainerItem("Desk", "Funiture", "It can store small items.");
        Desk.addItem(Pen);

        //keysafe
        ContainerItem Safe = new ContainerItem("Safe", "Funiture", "Insert Key Here");

        Bedroom.addItem(Safe);
        Bedroom.addItem(Bed);
        Bedroom.addItem(Chest);
        Bedroom.addItem(Desk);

        //Hallway stuff
        Item Lamb = new Item("Lamb", "Luminaire", "Soft light!");
        Hallway.addItem(Lamb);

        //bookshelf stuff
        ContainerItem Bookshelf = new ContainerItem("Bookshelf", "Funiture", "With a lot of books in it.");
        Item BookA = new Item("BookA", "Book", "Knowledge inside!");
        Bookshelf.addItem(BookA);
        Hallway.addItem(Bookshelf);

        //set initial position
        currLocation = Hallway;

    }

    public static void helpCommand() {
        System.out.println("Useful commands:\n");
        System.out.println("The \"quit\" command let you end this game");
        System.out.println("The \"look\" command let you look at your current location and check if any item here");
        System.out.println("The \"examine\" command follow with a item name let you look at the item more closely");
        System.out.println("The \"go\" command follow with a direction let you walk through this direction and arrive at a new location");
        System.out.println("The \"inventory\" command let you look at your inventory");
        System.out.println("The \"take\" follow with a item name let you take this item from your current location to your inventory");
        System.out.println("The \"take ITEM from CONTAINER\" let you take the ITEM from the CONTAINER in your current location to your inventory");
        System.out.println("The \"put ITEM in CONTAINER\" let you put the ITEM from your inventory to the CONTAINER in your current location");
        System.out.println("The \"drop\" follow with a item name let you drop this item from your inventory to your current location");
        System.out.println("The \"help\" command let you check all avaliable commands");
    }
    public static void endGame(){ 
        if(currLocation.hasItem("Safe")==true){
            ContainerItem TargetItem=(ContainerItem)currLocation.getItem("Safe");
            if(TargetItem.hasItem("Key")==true){
                System.out.println("You win the Game and that is your key to unlock your folder");
                System.out.println("The key is ");
                System.out.println("Now you enter command to quit the game");
            }    
        }
    }

    public static void main(String[] args) {
        //setup the game
        createWorld();
        System.out.print("Type Help to learn the Commands\n");
        System.out.print("The goal is to find the key item and put it in the correct container, once this occurs the Game will end\n");

        //set Scanner
        Scanner myScanner = new Scanner(System.in);
        

        //run the game
        String status = "executing";
        while (status.equals("quit") == false) {
            endGame();
            System.out.print("Enter command: ");
    
            //read the command
            String command = myScanner.nextLine();

            //breakdown the command
            String[] breakCommand = command.split(" ");
            String type = breakCommand[0];
            String parameter = null;
            String parameter2 = null;
            String fromString = "";
            String inString = "";
            if (breakCommand.length > 1) {
                parameter = breakCommand[1];
            }
            if (breakCommand.length > 2) {
                fromString = breakCommand[2];
                inString = breakCommand[2];
                if(breakCommand.length >3){
                    parameter2 = breakCommand[3];
                }
            }
            
            //ignore commands' case
            String commandConvert = "";
            if (type.equalsIgnoreCase("quit")){
                commandConvert = "quit";
            }
            else if (type.equalsIgnoreCase("look")) {
                commandConvert = "look";
            }
            else if ((type.equalsIgnoreCase("examine"))) {
                commandConvert = "examine";
            }
            else if ((type.equalsIgnoreCase("go"))) {
                commandConvert = "go";
            }
            else if ((type.equalsIgnoreCase("inventory"))) {
                commandConvert = "inventory";
            }
            else if ((type.equalsIgnoreCase("take"))) {
                commandConvert = "take";
            }
            else if ((type.equalsIgnoreCase("drop"))) {
                commandConvert = "drop";
            }
            else if ((type.equalsIgnoreCase("help"))) {
                commandConvert = "help";
            }
            else if ((type.equalsIgnoreCase("put"))) {
                commandConvert = "put";
            }
            //commands
            switch(commandConvert) {
            case "quit":
                System.out.println("Quitting...");
                status = "quit";
                break;
            case "look":
                System.out.println(currLocation.getName() + " - " + currLocation.getDescription() + ".\nIt has the following items:");
                for(int i = 0; i < currLocation.numItems(currLocation); i++) {
                    System.out.println("+ " + currLocation.getItem(i).getName());
                }
                break;
            case "examine":
                if (parameter == null) {
                    System.out.println("Please enter the item name");
                }
                else if (currLocation.hasItem(parameter)) {
                    Item target = currLocation.getItem(parameter);

                    if (target instanceof ContainerItem) {
                        ContainerItem c1 = (ContainerItem) target;
                        System.out.println(c1.toString());
                    }
                    else {
                        System.out.println(currLocation.getItem(parameter).toString());
                    }
                }
                else if (currLocation.hasItem(parameter) == false) {
                    System.out.println("Cannot find that item");
                }
                break;
            case "go":
                if (parameter == null) {
                    System.out.println("Please enter the direction");
                    break;
                }

                String directionConvert = "";
                if (parameter.equalsIgnoreCase("south")){
                    directionConvert = "south";
                }
                else if (parameter.equalsIgnoreCase("north")) {
                    directionConvert = "north";
                }
                else if ((parameter.equalsIgnoreCase("east"))) {
                    directionConvert = "east";
                }
                else if ((parameter.equalsIgnoreCase("west"))) {
                    directionConvert = "west";
                }
        
                if (currLocation.canMove(directionConvert)) {
                    currLocation = currLocation.getLocation(directionConvert);
                }
                break;
            case "inventory":
                System.out.println(myInventory.toString());
                break;
            case "take":
                if (parameter == null) {
                    System.out.println("Please enter the item's name");
                }
                else if (fromString == "" && parameter2 == null) {
                    if (currLocation.hasItem(parameter)) {
                        myInventory.addItem(currLocation.removItem(parameter));
                    }
                    else if(currLocation.hasItem(parameter) == false) {
                        System.out.println("Cannot find that item here");
                    }
                }
                else if (fromString.equalsIgnoreCase("from") == false) {
                    System.out.println("I don't know how to do that");
                }
                else if (parameter2 == null){
                    System.out.println("Please enter the target container");
                }
                else if (currLocation.hasItem(parameter2)){
                    ContainerItem container = (ContainerItem) currLocation.getItem(parameter2);
                    if (container.hasItem(parameter) == false) {
                        System.out.println("I can't find the target item in the container");
                    }
                    else {
                        myInventory.addItem(container.removItem(parameter));
                    }
                }
                else if (currLocation.hasItem(parameter2) == false) {
                    System.out.println("Cannot find that container here");
                }
                break;
            case "drop":
                if (parameter == null) {
                    System.out.println("Please enter the item's name");
                }
                else if (myInventory.hasItem(parameter)) {
                    currLocation.addItem(myInventory.removItem(parameter));
                }
                else  if (myInventory.hasItem(parameter) == false) {
                    System.out.println("Cannot find that item in your inventory");
                }
                break;
            case "put":
                if (inString.equalsIgnoreCase("in") == false) {
                    System.out.println("I don't know how to do that.");
                }
                else if (parameter2 == null) {
                    System.out.println("Please enter the target container");
                }
                else if (currLocation.hasItem(parameter2)){
                    ContainerItem container = (ContainerItem) currLocation.getItem(parameter2);
                    if (myInventory.hasItem(parameter) == false) {
                        System.out.println("I can't find the target item in your inventory");
                    }
                    else {
                        container.addItem(myInventory.removItem(parameter));
                    }
                }
                else if (currLocation.hasItem(parameter2) == false) {
                    System.out.println("Cannot find the container here.");
                }
                break;
            case "help":
                helpCommand();
                break;
            default:
                System.out.println("I don't know how to do that");
            }
        }
    }
}
