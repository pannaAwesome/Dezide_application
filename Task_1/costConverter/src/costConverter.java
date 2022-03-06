import java.util.ArrayList;
import java.util.List;

public class costConverter {
    public static void main(String[] args) throws Exception {
        model model1234 = new model("model1234", 500);
        costConverter.availableModels.add(model1234);

        costConverter converter = new costConverter(args[0], args[1], args[2]);

        System.out.println(converter.calculateCost());
    }

    public static List<model> availableModels = new ArrayList<model>();
    public static double globalValueCost = 300;

    double time;
    double money;
    double timeFactor;

    public costConverter(String currTime, String currMoney, String modelIdName) {
        setTime(currTime);
        setMoney(currMoney);
        setTimeFactor(new model(modelIdName));
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
    
    private void setTimeFactor(model currModel) {
        if (availableModels.contains(currModel)) {
            int modelIndex = availableModels.indexOf(currModel);
            model localModel = availableModels.get(modelIndex);

            if (localModel.localCost == -1) {
                timeFactor = globalValueCost;
            } else {
                timeFactor = localModel.localCost;
            }
        } else {
            throw new IllegalArgumentException("The model, you are trying to use, do not exist in the system.");
        }
    }

    public double calculateCost() {
        return Math.round(time * (timeFactor/60) + money);
    }
}