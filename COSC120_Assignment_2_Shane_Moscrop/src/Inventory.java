import java.util.*;

public class Inventory {

    private final List<FruitingPlant> inventory = new ArrayList<>();

    public void addItem(FruitingPlant fruitingPlant){
        this.inventory.add(fruitingPlant);
    }


    public Set<String> getAllTypes(Category type) {
        Set<String> allTypes = new TreeSet<>();
        for (FruitingPlant p: inventory){
            if(type.equals(p.dreamPlant().getValueAtCriteria(Criteria.CATEGORY)))
                allTypes.add((String) p.dreamPlant().getValueAtCriteria(Criteria.TYPE));
        }
        allTypes.add("NA");
        return allTypes;
    }

    public List<FruitingPlant> findMatch(DreamPlant plantSearch){
        List<FruitingPlant> compatiblePlants = new ArrayList<>();
        for(FruitingPlant fruitingPlant: this.inventory){
            if(!fruitingPlant.dreamPlant().compareDreamPlant(plantSearch)) continue;
            if (fruitingPlant.potSizeToPrice().containsKey(plantSearch.getPotSize())) continue;
            int potSizeChosen = plantSearch.getPotSize();
            float correspondingPriceChosen = fruitingPlant.potSizeToPrice().get(potSizeChosen);
            if(correspondingPriceChosen < plantSearch.getMinPrice() || correspondingPriceChosen > plantSearch.getMaxPrice()) continue;
            compatiblePlants.add(fruitingPlant);
        }
        return compatiblePlants;
    }

}
