import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class costConverter {
    public static void main(String[] args) throws Exception {
        model model1234 = new model("model1234", 500);
        model1234.localCustomCosts.addCustomCosts(Map.of("Risk", "high", "Inconvenience", "medium"));
        costConverter.availableModels.add(model1234);

        customerSystem customer = new customerSystem(300);
        customer.globalCustomCosts.addCustomCosts(Map.of("Risk", "low", "Inconvenience", "high"));
        costConverter.customer = customer;

        customCostList customCosts = new customCostList();
        for (int i = 3; i < args.length; i++) {
            customCosts.addCustomCost(args[i]);
        }
        costConverter converter = new costConverter(args[0], args[1], args[2], customCosts);

        System.out.println(converter.calculateCost());
    }

    public static List<model> availableModels = new ArrayList<model>();
    public static customerSystem customer;

    double time;
    double money;
    double timeFactor;
    model currentModel;
    customCostList adjustedCustomCosts = null;

    public costConverter(String currTime, String currMoney, String modelIdName) {
        setTime(currTime);
        setMoney(currMoney);
        setCurrentModel(new model(modelIdName));
        setTimeFactor();
    }

    public costConverter(String currTime, String currMoney, String modelIdName, customCostList customCosts) {
        this(currTime, currMoney, modelIdName);

        adjustedCustomCosts = customCosts;
    }

    private void setTime(String currTime) {
        double currTimeNum = Double.parseDouble(currTime);
        if (currTimeNum < 0) {
            throw new IllegalArgumentException("It is not possible to perform a task in negative time.");
        }

        time = currTimeNum;
    }

    private void setMoney(String currMoney) {
        double currMoneyNum = Double.parseDouble(currMoney);
        if (currMoneyNum < 0) {
            throw new IllegalArgumentException("It is not possible to have a negative amount of money.");
        }
        money = currMoneyNum;
    }
    
    private void setTimeFactor() {
        if (currentModel.localCost == -1) {
            timeFactor = customer.globalValueCost;
        } else {
            timeFactor = currentModel.localCost;
        }
    }

    private void setCurrentModel(model currModel) {
        if (availableModels.contains(currModel)) {
            int modelIndex = availableModels.indexOf(currModel);
            currentModel = availableModels.get(modelIndex);
        } else {
            throw new IllegalArgumentException("The model, you are trying to use, do not exist in the system.");
        }
    }

    public double calculateCost() {
        double totalCost = time * (timeFactor/60) + money;
        return Math.round(customCostAdjustments(totalCost));
    }

    private double customCostAdjustments(double originalCost) {
        for (customCost cost : customer.globalCustomCosts) {
            String adjustment = cost.costValue;
            if (adjustedCustomCosts.contains(cost)) {
                adjustment = adjustedCustomCosts.findCustomCost(cost.costType).costValue;
            } else if (currentModel.localCustomCosts.contains(cost)) {
                adjustment = currentModel.localCustomCosts.findCustomCost(cost.costType).costValue;
            }
            double adjustmentValue = valueAdjustmentSchema.valueAdjustmentSchema.get(adjustment);
            originalCost += originalCost * (adjustmentValue / 100);
        }
        return originalCost;
    }
}