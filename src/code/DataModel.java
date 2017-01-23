package code;

import java.util.HashMap;

/**
 * Created by Ferdila Rahmi on 8/26/2016.
 */
public class DataModel {
    HashMap<String,Double> prob_true = new HashMap<String,Double>();
    HashMap<String,Double> prob_false = new HashMap<String,Double>();
    DataModel(){
        super();
    }

    public HashMap<String,Double> getProb_true() {
        return (HashMap<String, Double>) prob_true;
    }

    public void setProb_true(HashMap<String,Double> prob_true) {
        this.prob_true = prob_true;
    }

    public HashMap<String,Double> getProb_false() {
        return (HashMap<String, Double>) prob_false;
    }

    public void setProb_false(HashMap<String,Double> prob_false) {
        this.prob_false = prob_false;
    }
}
