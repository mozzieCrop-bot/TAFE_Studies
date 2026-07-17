/**
 * This file is for naming the Fields, constructors, getters, and setters for
 * coffee objects for The Caffeinated Geek app.
 */

public class Coffee {
    // Fields
    private final int menuItemID;
    private final String menuItemName;
    private final float price;
    private final int numberOfShots;
    private final Sugar sugar;
    //private final Milk[] milk;
    private final String extraOption;
    private final String coffeeDescription;
    private float minPrice;
    private float maxPrice;

    /**
     * Constructor to create coffee object
     * @param menuItemID
     * @param menuItemName
     * @param price
     * @param numberOfShots
     * @param sugar
     //* @param milk
     * @param extraOption
     * @param coffeeDescription
     */
    // menu item ID [0], menu item name[1], price[2], numberOfShots[3], sugar[4], milk[5], extras[6], description[7]
    public Coffee(int menuItemID, String menuItemName, float price, int numberOfShots, Sugar sugar/*, Milk[] milk*/, String extraOption, String coffeeDescription) {
        this.menuItemID = menuItemID;
        this.menuItemName = menuItemName;
        this.price = price;
        this.numberOfShots = numberOfShots;
        this.sugar = sugar;
        //this.milk = milk;
        this.extraOption = extraOption;
        this.coffeeDescription = coffeeDescription;
    }

    // Getters

    /**
     * @return the unique menu item ID number
     */
    public int getMenuItemID() {
        return menuItemID;
    }

    /**
     * @return the menu item name
     */
    public String getMenuItemName() {
        return menuItemName;
    }

    /**
     * @return the price of the coffee
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the number of coffee shots in the coffee object
     */
    public int getNumberOfShots() {
        return numberOfShots;
    }

    /**
     * @return if sugar is used or not
     */
    public Sugar getSugar() {
        return sugar;
    }

    /**
     * @return the type of milk used
     */
    /*
    public Milk[] getMilk() {
        return milk;
    }*/

    /**
     * @return any extra's that are selected
     */
    public String getExtraOption() {
        return extraOption;
    }

    /**
     * @return a description of the coffee object
     */
    public String getCoffeeDescription() {
        return coffeeDescription;
    }

    /**
     * Setting the minimum price to be searched
     * @param minPrice
     */
    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Setting the maximum price to be searched
     * @param maxPrice
     */
    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return the minimum price of the coffee
     */
    public float getMinPrice() {
        return minPrice;
    }

    /**
     * @return the maximum price of the coffee
     */
    public float getMaxPrice() {
        return maxPrice;
    }
}
