import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class customCostList extends ArrayList<customCost> {
    public boolean addCustomCost(String customCostType, String customCostValue) {
        customCost cost = new customCost(customCostType, customCostValue);
        return super.add(cost);
    }

    public void addCustomCost(String customCostStringPair) {
        String[] pair = customCostStringPair.split(":");
        if (pair.length == 2) {
            addCustomCost(pair[0], pair[1]);
        } else {
            throw new IllegalArgumentException("The specified custom costs shall be on the form type:value.");
        }
    }

    public void addCustomCosts(Map<String, String> customCostPairs) {
        for (Map.Entry<String, String> costPair : customCostPairs.entrySet()) {
            addCustomCost(costPair.getKey(), costPair.getValue());
        }
    }

    public customCost findCustomCost(String costType) {
        customCost currentCost = new customCost(costType, "");

        if (this.contains(currentCost)) {
            int index = this.indexOf(currentCost);
            return this.get(index);
        } else {
            return null;
        }
    }
}
