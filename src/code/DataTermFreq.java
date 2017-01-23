package code;

import java.util.HashMap;

/**
 * Created by Ferdila Rahmi on 9/1/2016.
 */
public class DataTermFreq {
    HashMap class_all = new HashMap<>();
    HashMap class_true = new HashMap<>();
    HashMap class_false = new HashMap<>();

    HashMap each_class = new HashMap<>();

    DataTermFreq(){
        super();
    }

    public HashMap getClass_all() {
        return class_all;
    }

    public void setClass_all(HashMap class_all) {
        this.class_all = class_all;
    }

    public HashMap getClass_true() {
        return class_true;
    }

    public void setClass_true(HashMap class_true) {
        this.class_true = class_true;
    }

    public HashMap getClass_false() {
        return class_false;
    }

    public void setClass_false(HashMap class_false) {
        this.class_false = class_false;
    }

    public HashMap getEach_class() {
        return each_class;
    }

    public void setEach_class(HashMap each_class) {
        this.each_class = each_class;
    }
}
