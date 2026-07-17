import java.text.DecimalFormat;
import java.util.Map;

public record FruitingPlant(String productCode, String productName, String description, Map<Integer,Float> potSizeToPrice, DreamPlant dreamPlant) {

    public String toString(Map<Criteria,Object> plantCriteria) {
        DecimalFormat df = new DecimalFormat("0.00");
        return this.productName+" ("+this.productCode+") "+description;
    }
}
