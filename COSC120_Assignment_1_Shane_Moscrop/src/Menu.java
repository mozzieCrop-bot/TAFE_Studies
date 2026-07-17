
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {
    // Fields
    private final List<Coffee> menu = new ArrayList<>();

    // Methods

    /**
     * method to add a Coffee object to the database (menu)
     * @param coffee a Coffee object
     */
    public void addCoffee(Coffee coffee) {this.menu.add(coffee);}

    /**
     * a method to return a set of extras in the dataset
     * @return Set<String> of available extras
     */
    public Set<String> getAllExtras(){
        Set<String> allExtras = new HashSet<>();
        for(Coffee i: menu){
            allExtras.add(i.getExtraOption());
        }
        return allExtras;
    }
    // Setting up the matching criteria search for coffee

    /**
     * returns a collection of Coffee objects that meet the users criteria
     * @param coffeeCriteria a Coffee object representing the users dream coffee
     * @return a Coffee object
     */
    public List<Coffee> findMatch(Coffee coffeeCriteria){
        List<Coffee> possibleCoffee = new ArrayList<>();
        for(Coffee coffee: this.menu){
            //if(!coffee.getMilk().equals(coffeeCriteria.getMilk())) continue;
            if(coffee.getNumberOfShots() != coffeeCriteria.getNumberOfShots()) continue;
            if(!coffee.getSugar().equals(coffeeCriteria.getSugar())) continue;
            if(!coffee.getExtraOption().contains(coffeeCriteria.getExtraOption())) continue;
            if(coffee.getPrice() < coffeeCriteria.getMinPrice() || coffee.getPrice() > coffeeCriteria.getMaxPrice()) continue;
            possibleCoffee.add(coffee);
        }
        return possibleCoffee;
    }
}
