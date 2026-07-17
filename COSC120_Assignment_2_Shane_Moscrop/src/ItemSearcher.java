import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemSearcher {

    private static final String filePath = "./inventory_v2.txt";
    private static final Icon icon = new ImageIcon("./the_greenie_geek.png");
    private static Inventory inventory;
    private static final String appName = "Greenie Geek";

    public static void main(String[] args) {
        inventory = loadInventory(filePath);
        DreamPlant currentPlant = getFilters();
        processSearchResults(currentPlant);
        System.exit(0);
    }


    public static DreamPlant getFilters() {
        Map<Criteria, Object> plantCriteria = new HashMap<>();

        Category category = (Category) JOptionPane.showInputDialog(null, "Please select the category you'd like to purchase.",appName,JOptionPane.QUESTION_MESSAGE,icon,Category.values(),Category.CITRUS);
        if (category == null) System.exit(0);
        plantCriteria.put(Criteria.CATEGORY, category);

        String type = (String) JOptionPane.showInputDialog(null,"Please select your preferred type.",appName,JOptionPane.QUESTION_MESSAGE,icon,inventory.getAllTypes(category).toArray(),"");
        if (type == null) System.exit(0);
        if (!type.equals("NA")) plantCriteria.put(Criteria.TYPE, type);

        if (category != Category.VINE) {
            Dwarf dwarf = (Dwarf) JOptionPane.showInputDialog(null, "Would you like a dwarf tree?",appName,JOptionPane.QUESTION_MESSAGE,icon,Dwarf.values(),Dwarf.NA);
            if (dwarf == null) System.exit(0);
            if (!dwarf.equals(Dwarf.NA)) plantCriteria.put(Criteria.DWARF, dwarf);
        }

        if (category == Category.VINE) {
            TrainingSystems trainingSystems = (TrainingSystems) JOptionPane.showInputDialog(null,"Please select your preferred training system.",appName,JOptionPane.QUESTION_MESSAGE,icon,TrainingSystems.values(),TrainingSystems.NA);
            if (trainingSystems == null) System.exit(0);
            if (!trainingSystems.equals(TrainingSystems.NA)) plantCriteria.put(Criteria.TRAINING_SYSTEM, trainingSystems);
        }

        if (category == Category.POME || category == Category.STONE_FRUIT) {
            Pollinators pollinators = (Pollinators) JOptionPane.showInputDialog(null,"Please select a pollinator.",appName,JOptionPane.QUESTION_MESSAGE,icon,Pollinators.values(),Pollinators.NA);
            if (pollinators == null) System.exit(0);
            if (!pollinators.equals(Pollinators.NA)) {
                Set<Pollinators> pollinatorsSet = new HashSet<>();
                boolean proceedFurther = false;
                while (!proceedFurther) {
                    pollinatorsSet.add(pollinators);
                    int proceedAnswer = JOptionPane.showConfirmDialog(null, "Would you like to add another pollinator?",appName, JOptionPane.YES_NO_OPTION);
                    if (proceedAnswer == JOptionPane.YES_OPTION) pollinators = (Pollinators) JOptionPane.showInputDialog(null,"Please select a pollinator.",appName,JOptionPane.QUESTION_MESSAGE,icon,Pollinators.values(),Pollinators.NA);
                    else if (proceedAnswer == JOptionPane.NO_OPTION) proceedFurther = true;
                }
                plantCriteria.put(Criteria.POLLINATOR, pollinatorsSet);
            }
        }

        //pot size
        int potSize = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Pot size (inch)? **min 8 inch",appName, JOptionPane.QUESTION_MESSAGE,icon,null,null));
        if(potSize < 8) {
            JOptionPane.showMessageDialog(null,"Invalid input. Please enter a positive number greater than 8.",appName, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //min price - max price
        float minPrice = -1f, maxPrice = -1f;
        while (minPrice < 0) {
            String userInput = (String) JOptionPane.showInputDialog(null, "Enter min price range value:", appName, JOptionPane.QUESTION_MESSAGE, icon, null, null);
            if (userInput == null) System.exit(0);
            try {
                minPrice = Float.parseFloat(userInput);
                if (minPrice < 0) JOptionPane.showMessageDialog(null,"Price must be >= 0.",appName, JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.", appName, JOptionPane.ERROR_MESSAGE);
            }
        }
        while(maxPrice < minPrice) {
            String userInput = (String) JOptionPane.showInputDialog(null, "Enter max price range value:", appName, JOptionPane.QUESTION_MESSAGE,icon,null, null);
            if (userInput == null) System.exit(0);
            try {
                maxPrice = Float.parseFloat(userInput);
                if (maxPrice < minPrice) JOptionPane.showMessageDialog(null,"Price must be >= "+minPrice,appName, JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.", appName, JOptionPane.ERROR_MESSAGE);
            }
        }

        DreamPlant currentPlant = new DreamPlant(plantCriteria, minPrice, maxPrice, potSize);
        return currentPlant;
    }

    public static void processSearchResults(DreamPlant dreamPlant) {
        List<FruitingPlant> potentialMatches = inventory.findMatch(dreamPlant);
        if (potentialMatches.size() > 0) {
            Map<String, FruitingPlant> options = new HashMap<>();
            StringBuilder infoToShow = new StringBuilder("Matches found!! The following plants meet your criteria:\n");
            for (FruitingPlant potentialMatch :potentialMatches) {
                infoToShow.append("\n").append(potentialMatch.toString(potentialMatch.dreamPlant().getAllPlantCriteriaAndValues()));
                options.put(potentialMatch.productName()+" ("+potentialMatch.productCode()+")", potentialMatch);
            }
            String purchase = (String) JOptionPane.showInputDialog(null,infoToShow+"\nPlease select which "+
                    "plant you'd like to purchase:",appName,JOptionPane.QUESTION_MESSAGE,icon,options.keySet().toArray(),"");
            if (purchase == null) System.exit(0);
            else {
                FruitingPlant chosenPurchase = options.get(purchase);
                Customer customer = getContactInfo();
                submitOrder(customer,chosenPurchase);
                JOptionPane.showMessageDialog(null,"Thank you! Your order has been submitted.\n"+
                        "One of our friendly staff will be in touch shortly.",appName,JOptionPane.QUESTION_MESSAGE,icon);
            }
        }
        else {
            boolean proceedFurther = false;
            while (!proceedFurther) {
                int proceedAnswer = JOptionPane.showConfirmDialog(null, "Unfortunately it does not appear we have a plant that meets your criteria" +
                        "\n\tWould you like to look for another plant?",appName, JOptionPane.YES_NO_OPTION);
                if (proceedAnswer == JOptionPane.YES_OPTION) {
                    DreamPlant currentPlant = getFilters();
                    processSearchResults(currentPlant);
                }
                else if (proceedAnswer == JOptionPane.NO_OPTION) proceedFurther = true;
            }
        }
    }

    public static Customer getContactInfo(){
        String name;
        do{
            name = (String) JOptionPane.showInputDialog(null,"Please enter your full name (in format firstname surname): ",appName,JOptionPane.QUESTION_MESSAGE, icon, null,null);
            if(name==null) System.exit(0);
        } while(!isValidFullName(name));
        String phoneNumber;
        do{
            phoneNumber = (String) JOptionPane.showInputDialog(null,"Please enter your phone number (10-digit number in the format 0412345678): ",appName,JOptionPane.QUESTION_MESSAGE, icon, null,null);
            if(phoneNumber==null) System.exit(0);}
        while(!isValidPhoneNumber(phoneNumber));
        return new Customer(name,phoneNumber);
    }

    public static boolean isValidFullName(String fullName) {
        String regex = "^[A-Z][a-z]+\\s[A-Z][a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static void submitOrder(Customer customer, FruitingPlant fruitingPlant) {
        String filePath = customer.name().replace(" ", "_") + "_" + fruitingPlant.productCode() + ".txt";
        Path path = Path.of(filePath);
        String lineToWrite = "Order details:" +
                "\n\tName: " + customer.name() + " ("+customer.phoneNumber() +")"+
                "\n\tItem: " + fruitingPlant.productName() + " (" + fruitingPlant.productCode() + ")" +
                "\n\tPot size (inch): "+fruitingPlant.dreamPlant().getPotSize();

        try {
            Files.writeString(path, lineToWrite);
        } catch (IOException io) {
            System.out.println("Order could not be placed. \nError message: " + io.getMessage());
            System.exit(0);
        }
    }

    public static Inventory loadInventory(String filePath) {
        Inventory inventory = new Inventory();
        Path path = Path.of(filePath);

        List<String> inventoryData = null;
        try {
            inventoryData = Files.readAllLines(path);
        }
        catch (IOException io) {
            System.out.println("Could not load the file.\nError message: "+io.getMessage());
            System.exit(0);
        }

        for (int i = 1; i < inventoryData.size(); i++) {
            String [] multipleElements = inventoryData.get(i).split("\\[");
            String [] elements = multipleElements[0].split(",");

            Category category = null;
            try {
                category = Category.valueOf(elements[0].toUpperCase().replace(" ","_"));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error in file. Category data could not be parsed for inventory on line "+(i+1)+". Terminating now.\nError message: "+e.getMessage());
                System.exit(0);
            }

            String productName = elements[1];
            String productCode = elements[2];
            String type = elements[3].toLowerCase();

            Dwarf dwarf = null;
            try {
                dwarf = Dwarf.valueOf(elements[4].toUpperCase().replace(" ",""));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error in file. Dwarf data could not be parsed for inventory on line "+(i+1)+". Terminating now.\nError message: "+e.getMessage());
                System.exit(0);
            }

            TrainingSystems trainingSystems = null;
            try {
                trainingSystems = TrainingSystems.valueOf(elements[5].toUpperCase().replace("-","_").replace(" NA", "NA").replace(" ","_"));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error in file. Training Systems data could not be parsed for inventory on line "+(i+1)+". Terminating now.\nError message: "+e.getMessage());
                System.exit(0);
            }

            Pollinators pollinators = null;
            String pollinatorElements = multipleElements[1].replace("], ", "").replace("],", "").toUpperCase();
            String [] pollinatorsSplit = pollinatorElements.replace(", ",",").replace("’","_").split(",");
            try {
                for (int x = 0; x < pollinatorsSplit.length; x++) {
                    if (pollinatorsSplit[x] == "") pollinatorsSplit[x] = "NA";
                    pollinators = Pollinators.valueOf(pollinatorsSplit[x].replace(" ","_"));
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error in file. Pollinators data could not be parsed for inventory on line "+(i+1)+". Terminating now.\nError message: "+e.getMessage());
                System.exit(0);
            }

            String pricesRaw = multipleElements[2].replace("],", "");
            String potSizesRaw = multipleElements[3].replace("],", "");

            Map<Integer,Float> potSizeToPrice = new TreeMap<>();

            if(potSizesRaw.length() > 0) {
                String[] optionsPotSizes = potSizesRaw.replace(" ","").split(",");
                String[] optionsPrices = pricesRaw.replace(" ","").split(",");
                for (int j = 0; j < optionsPrices.length; j++) {
                    int potSize = 0;
                    float price = 0;
                    try {
                        potSize = Integer.parseInt(optionsPotSizes[j]);
                        price = Float.parseFloat(optionsPrices[j]);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Error in file. Pot size/price option could not be parsed for item on line " + (i + 1) + ". Terminating. \nError message: " + e.getMessage());
                        System.exit(0);
                    }
                    potSizeToPrice.put(potSize,price);
                }
            }

            String description = multipleElements[4].replace("],", "");

            Map<Criteria,Object> plantCriteria = new HashMap<>();
            plantCriteria.put(Criteria.CATEGORY,category);
            plantCriteria.put(Criteria.TYPE,type);
            plantCriteria.put(Criteria.DWARF,dwarf);
            plantCriteria.put(Criteria.TRAINING_SYSTEM,trainingSystems);
            plantCriteria.put(Criteria.POLLINATOR,pollinators);

            DreamPlant dreamPlant = new DreamPlant(plantCriteria);
            FruitingPlant fruitingPlant = new FruitingPlant(productCode,productName,description,potSizeToPrice,dreamPlant);

            inventory.addItem(fruitingPlant);
        }
        return inventory;
    }
}
