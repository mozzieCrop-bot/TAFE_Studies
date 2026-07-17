import java.util.HashMap;
import java.util.Map;

public class DreamPlant {
    // fields
    private final Map<Criteria, Object> plantCriteria;
    private float maxPrice;
    private float minPrice;
    private int potSize;
    

    // constructor
    public DreamPlant(Map<Criteria, Object> plantCriteria, float minPrice, float maxPrice, int potSize) {
        this.plantCriteria = new HashMap<>(plantCriteria);
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.potSize = potSize;
    }

    public DreamPlant(Map<Criteria, Object> plantCriteria) {
        this.plantCriteria = new HashMap<>(plantCriteria);
    }

    // getters
    public Map<Criteria, Object> getAllPlantCriteriaAndValues() {
        return new HashMap<>(plantCriteria);
    }

    public Object getValueAtCriteria (Criteria key) {
        return getAllPlantCriteriaAndValues().get(key);
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public int getPotSize() {
        return potSize;
    }


    // methods
    public String getDreamPlantDescription(Map<Criteria, Object> criteria) {
        StringBuilder description = new StringBuilder();
        for (Criteria key: criteria.keySet())
            description.append("\n").append(key).append(": ").append(getValueAtCriteria(key));
        return description.toString();
    }

    public boolean compareDreamPlant(DreamPlant plantCriteria) {
        for (Criteria key : plantCriteria.getAllPlantCriteriaAndValues().keySet()) {
            if(!getValueAtCriteria(key).equals(plantCriteria.getValueAtCriteria(key))) return false;
        }
        return true;
    }
}
