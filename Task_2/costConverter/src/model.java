public class model {
    String id;
    double localCost = -1;
    customCostList localCustomCosts = new customCostList();

    public model(String idName) {
        id = idName;
    }

    public model(String idName, double cost) {
        this(idName);
        setCost(cost);        
    }

    private void setCost(double cost) {
        if (cost >= 0) {
            localCost = cost;
        } else {
            throw new IllegalArgumentException("The local cost for a model cannot be less than zero");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }else if (obj.getClass() != this.getClass()) {
            return false;
        }

        model otherModel = (model) obj;

        if (this.id.equals(otherModel.id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}