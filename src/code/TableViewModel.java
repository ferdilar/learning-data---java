package code;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * Created by Ferdila Rahmi on 9/3/2016.
 */
public class TableViewModel {
    private IntegerProperty id_data;
    private StringProperty term;
    private DoubleProperty prob_true;
    private DoubleProperty prob_false;

    public TableViewModel(){
        super();
    }
    public TableViewModel(Integer id_data, String term, Double prob_true, Double prob_false) {
        this.id_data = new SimpleIntegerProperty(id_data);
        this.term = new SimpleStringProperty(term);
        this.prob_true = new SimpleDoubleProperty(prob_true);
        this.prob_false = new SimpleDoubleProperty(prob_false);
    }

    public int getId_data() {
        return id_data.get();
    }

    public void setId_data(int id_data) {
        this.id_data.set(id_data);
    }

    public String getTerm() {
        return term.get();
    }

    public StringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public double getProb_true() {
        return prob_true.get();
    }

    public void setProb_true(double prob_true) {
        this.prob_true.set(prob_true);
    }

    public double getProb_false() {
        return prob_false.get();
    }

    public void setProb_false(double prob_false) {
        this.prob_false.set(prob_false);
    }

}
