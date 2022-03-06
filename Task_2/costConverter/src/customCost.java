import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class customCost {
    private final List<String> costValues = new ArrayList<String>(Arrays.asList("low", "medium", "high", "none"));
    
    String costType;
    String costValue;

    public customCost(String type, String value) {
        costType = type;
        costValue = value;
    }
    
    public void setCostValue(String value) {
        if (costValues.contains(value)) {
            costValue = value;
        } else {
            throw new IllegalArgumentException("The value: " + value + " is not possible for the custom cost, please use one of low, medium, high or none.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }else if (obj.getClass() != this.getClass()) {
            return false;
        }

        customCost cost = (customCost) obj;
        if (this.costType.equals(cost.costType)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return costType.hashCode();
    }
}