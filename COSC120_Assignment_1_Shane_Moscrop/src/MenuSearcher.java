import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuSearcher {

    // Fields
    private final static String appName = "The Caffeinated Geek";
    private final static String filePath = "./menu.txt";
    private final static String iconPath = "./the_caffeinated_geek.png";
    private static Menu menu;

    // >>>>>>>>>>>>>>> STILL NEED TO COMPLETE MAIN SECTION
    public static void main(String[] args){
        menu = loadMenu();
        ImageIcon icon = new ImageIcon(iconPath);

        JOptionPane.showMessageDialog(null, "Welcome to The Caffeinated Geek coffee order app!\n\tTo start, click OK.", appName, JOptionPane.QUESTION_MESSAGE, icon);
        Coffee coffeeCriteria = getCoffeeCriteria();
        List<Coffee> potentialMatches = menu.findMatch(coffeeCriteria);

        if (potentialMatches.size() > 0) {
            Map<String,Coffee> options = new HashMap<>();
            StringBuilder infoToShow = new StringBuilder("Matches found!! The following coffee options meet your criteria:\n");
            for (Coffee potentialMatch:potentialMatches) {
                infoToShow.append(potentialMatch.getMenuItemName()).append(" (").append(potentialMatch.getMenuItemID()).append(")\n").append(potentialMatch.getCoffeeDescription()).append("\nIngredients:\nMilk: ")./*append(potentialMatch.getMilk()).*/append("\nNumber of Shots: ").append(potentialMatch.getNumberOfShots()).append("\nSugar: ").append(potentialMatch.getSugar()).append("\nExtras: ").append(potentialMatch.getExtraOption()).append("\nPrice: $").append(potentialMatch.getPrice());
                options.put(potentialMatch.getMenuItemName(), potentialMatch);
            }
            String order = (String) JOptionPane.showInputDialog(null,infoToShow+"\n\nPlease select your choice of beverage to purchase:", "The Caffeinated Geek", JOptionPane.QUESTION_MESSAGE, null, options.keySet().toArray(), "");
            if (order == null) System.exit(0);
            else {
                Coffee chosenCoffee = options.get(order);
                Geek customer = getUserDetails();
                submitOrder(customer, chosenCoffee);
                JOptionPane.showMessageDialog(null, "Thank you! Your order has been placed. "+"You will be called up soon when your order is ready.", appName, JOptionPane.QUESTION_MESSAGE, icon);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Unfortunately, it appears we do not offer a beverage that meets your criteria\n\tTo exit, click OK.", appName, JOptionPane.QUESTION_MESSAGE, icon);
        }
        System.exit(0);
    }

    /**
     * method to get a user to input their name, phone number, and email address
     * @return a Geek object representing the user of the app
     */
    private static Geek getUserDetails(){
        String name;
        do {
            name = JOptionPane.showInputDialog("Please enter your full name (in format firstname surname): ");
            if(name == null) System.exit(0);
        } while (!isValidFullName(name));

        String phoneNumber;
        do {
            phoneNumber = JOptionPane.showInputDialog("Please enter your phone number (10 digit number in the format eg. 0412345678): ");
            if(phoneNumber == null) System.exit(0);
        } while (!isValidPhoneNumber(phoneNumber));

        String emailAddress;
        do {
            emailAddress = JOptionPane.showInputDialog(null, "Please enter your email address.", appName, JOptionPane.QUESTION_MESSAGE);
            if (emailAddress == null) System.exit(0);
        } while (!isValidEmail(emailAddress));

        return new Geek(name, phoneNumber, emailAddress);
    }

    /**
     * Using regex to check full name format is valid
     * @param fullName the full name of the geek entered by the user
     * @return true if the name matches regex, else it returns false
     */
    public static boolean isValidFullName (String fullName) {
        String regex = "[A-Z][a-z]+\\s[A-Z][a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    /**
     * Using regex to check phone number format is valid
     * @param phoneNumber the phone number of the geek entered by the user
     * @return true if the phone number matches regex, else it returns false
     */
    public static boolean isValidPhoneNumber (String phoneNumber) {
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Using regex to check email address format is valid
     * @param email the email address of the geek entered by the user
     * @return true if the email matches regex, else it returns false
     */
    public static boolean isValidEmail (String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^,-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * generates JOptionPanes requesting for input of Milk, Sugar, Number of Shots, Extras, and price
     * @return a coffee object representing the users desired coffee order
     */
    private static Coffee getCoffeeCriteria() {
        // Asking for type of milk
        //Milk[] milk = (Milk[]) JOptionPane.showInputDialog(null, "Please select which milk you wish to use:", appName, JOptionPane.QUESTION_MESSAGE, null, Milk.values(), Milk.FULL_CREAM);
        //if (milk == null) System.exit(0);

        // Asking if the user wants sugar
        Sugar sugar = (Sugar) JOptionPane.showInputDialog(null,"Would you like sugar?", appName, JOptionPane.QUESTION_MESSAGE, null, Sugar.values(), Sugar.YES);
        if (sugar == null) System.exit(0);

        // Asking for number of shots
        int numberOfShots = -1;
        while (numberOfShots == -1) {
            try {
                numberOfShots = Integer.parseInt(JOptionPane.showInputDialog(null, "How many shots of coffee would you like?", appName, JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
            }
        }
        // >>>>>>>>>> STILL NEED TO EDIT THIS TO KEEP ASKING FOR EXTRAS UNTIL SKIP
        String extraChoice = (String) JOptionPane.showInputDialog(null, "Please select your choice of extras", "The Caffeinated Geek", JOptionPane.QUESTION_MESSAGE,null,menu.getAllExtras().toArray(), "");
        if (extraChoice == null) System.exit(0);

        float minPrice = 0.00f, maxPrice = 0.00f;
        while (minPrice == 0.00f) {
            try {
                minPrice = Float.parseFloat(JOptionPane.showInputDialog(null, "Please enter the desired minimum price:", appName, JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
        }
        while (maxPrice < minPrice) {
            try{
                maxPrice = Float.parseFloat(JOptionPane.showInputDialog(null, "Please enter the desired maximum price:", appName, JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
            if (maxPrice < minPrice) JOptionPane.showInputDialog(null, "Maximum price must be higher than or equal to the minimum price.");
        }

        Coffee coffeeCriteria = new Coffee(0, "", 0.00f, 0, sugar/*, milk*/, extraChoice, "");
        coffeeCriteria.setMinPrice(minPrice);
        coffeeCriteria.setMaxPrice(maxPrice);
        return coffeeCriteria;
    }

    /**
     * method to load all the menu options from the file, storing as coffee objects in an instance of Menu
     * @return a Menu object - functions as database of coffee
     */
    private static Menu loadMenu() {
        Menu menu = new Menu();
        Path path = Path.of(filePath);

        List<String> menuEntries = null;
        try {
            menuEntries = Files.readAllLines(path);
        }
        catch (IOException io) {
            System.out.println("Could not load file. \nError message: " + io.getMessage());
            System.exit(0);
        }
        // menu item ID [0], menu item name[1], price[2], numberOfShots[3], sugar[4], milk[5], extras[6], description[7]
        for (int i=1; i < menuEntries.size(); i++){
            String [] elements = menuEntries.get(i).split(",(?=(?:[^\\[\\]]*\\[[^\\[\\]]*\\]){0,2}(?:[^\\[\\]]*\\[[^\\[\\]]*\\])?[^\\[\\]]*$)");

            int menuItemID = 0;
            try{
                menuItemID = Integer.parseInt(elements[0]);
            }
            catch (NumberFormatException n){
                System.out.println("Error in file. Menu item ID could not be parsed into int on line "+(i+1)+". Terminating now. \nError message: "+n.getMessage());
                System.exit(0);
            }
            String menuItemName = elements[1];
            float price = 0.0f;
            try{
                price = Float.parseFloat(elements[2]);
            }
            catch (NumberFormatException n){
                System.out.println("Error in file. Price could not be parsed into float on line "+(i+1)+". Terminating now. \nError message: "+n.getMessage());
                System.exit(0);
            }
            int numberOfShots = 0;
            try{
                numberOfShots = Integer.parseInt(elements[3]);
            }
            catch (NumberFormatException n){
                System.out.println("Error in file. Number of shots could not be parsed into int on line "+(i+1)+". Terminating now. \nError message: "+n.getMessage());
                System.exit(0);
            }
            Sugar sugar = Sugar.valueOf(elements[4].toUpperCase());

            /*
            String milkList = elements[5];
            if (milkList.contains("[") && milkList.contains("]")){
                milkList = milkList.substring(1,milkList.length() -1);
            }
            if (milkList.contains("-")){
                milkList = milkList.replace("-", "_");
            }
            milkList = milkList.toUpperCase();
            String[] milkTypes = milkList.split(",");
            Milk[] milk;
            for (String milkType : milkTypes){
                if (milkType == ""){
                    milkType = "NO_MILK";
                }
                if (milkType == null){
                    continue;
                }
                try {

                    milk = Milk.valueOf(milkType);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Invalid milk type: " +milkTypes[j]);
                }
            }
            */
            // Extras[6]
            String extraOption = elements[6];
            if (extraOption.contains("[") && extraOption.contains("]")) {
                extraOption = extraOption.substring(1, extraOption.length() -1);
            }
            // Description[7]
            String coffeeDescription = elements[7];
            Coffee coffee = new Coffee(menuItemID, menuItemName, price, numberOfShots, sugar/*, milk*/, extraOption, coffeeDescription);
            menu.addCoffee(coffee);
        }
        return menu;
    }

    /**
     * provides The Caffeinated Geek with a file containing the users coffee order
     * @param geek a geek object represent
     * @param coffee
     */
    private static void submitOrder(Geek geek, Coffee coffee) {
        String filePath = geek.name().replace(" ", "_")+"_coffee_order.txt";
        Path path = Path.of(filePath);
        String lineToWrite = "Order Details:\nName: "+geek.name()+" ("+geek.phoneNumber()+")\nItem: "+coffee.getMenuItemName()+" ("+coffee.getMenuItemID()+")\nMilk:"/*+coffee.getMilk()*/+" Milk\nExtras: "+coffee.getExtraOption();
        try {
            Files.writeString(path, lineToWrite);
        }
        catch (IOException io) {
            System.out.println("File could not be written.\nError Message: "+io.getMessage());
            System.exit(0);
        }
    }
}
