public class customerSystem {
    public double globalValueCost;
    public customCostList globalCustomCosts = new customCostList();

    public customerSystem(double globalCost) {
        setGlobalValueCost(globalCost);
    }

    private void setGlobalValueCost(double globalCost) {
        if (globalCost < 0) {
            throw new IllegalArgumentException("The global value cost of a customer system cannot be negative.");
        } else {
            globalValueCost = globalCost;
        }
    }
}
