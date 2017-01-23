package code;

import java.util.ArrayList;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 * Belum dipakai
 */

public class DataSMS{
    String data_before;
    //utnuk _id_data dari index array+1 jgn lupa
    ArrayList<String> data_after;
    boolean class_1;//spam = 1 notspam = 0
    boolean class_2;//fraud = 1 promo = 0

    DataSMS(){
        super();
        this.class_1=false;
        this.class_2=false;
        data_after = new ArrayList<String>();
    }

    public void setData_before(String document){
        this.data_before = document;
    }
    public String getData_before() {
        return this.data_before;
    }

    public void setData_after(ArrayList<String> token){
        this.data_after = token;
    }
    public ArrayList<String> getData_after() {
        return this.data_after;
    }

    public void setClass_1(boolean class_1){
        this.class_1 = class_1;
    }
    public boolean getClass_1() {
        return this.class_1;
    }

    public void setClass_2(boolean class_2){
        this.class_2 = class_2;
    }
    public boolean getClass_2() {
        return this.class_2;
    }

}
