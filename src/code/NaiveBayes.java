package code;

import java.util.ArrayList;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class NaiveBayes {
    DBModel db_model;
    private String error;
    double c_map_true;
    double c_map_false;

    NaiveBayes() throws Exception {
        super();
        db_model = new DBModel();
        c_map_true = 0;
        c_map_false = 0;
    }

    public double getC_map_true() {
        return c_map_true;
    }

    public void setC_map_true(double c_map_true) {
        this.c_map_true = c_map_true;
    }

    public double getC_map_false() {
        return c_map_false;
    }

    public void setC_map_false(double c_map_false) {
        this.c_map_false = c_map_false;
    }

    public boolean naiveBayesClass(ArrayList<String> arl_term_testing, String tabel_model){//String data_model database
        boolean nbc = false;

        setC_map_true(cMAP(arl_term_testing,true,tabel_model));
        setC_map_false(cMAP(arl_term_testing,false,tabel_model));

        if(getC_map_true()>getC_map_false()){
            nbc = true;
        }
        return nbc;
    }

    public double cMAP(ArrayList<String> arl_term_testing, boolean c, String tabel_model){
        String row_model_class="1111";
        String row_model_null="0000";
        double prob=0;
        double p_class=0;
//        double p_term_all=0;//log 10
        double p_term_all=1;//salah
        try{
            p_class=getProbModel(row_model_class, c, tabel_model);
            if(arl_term_testing.size()==0){
                p_term_all=0;
            }else{
                for(int j=0;j<arl_term_testing.size();j++) {
                    if(cekExistTerm(arl_term_testing.get(j),tabel_model)){
//                p_term_all=p_term_all+getProbModel(arl_term_testing.get(j),c,data_model);//log 10
                        p_term_all=p_term_all*getProbModel(arl_term_testing.get(j),c,tabel_model);//salah
//                        System.out.println(arl_term_testing.get(j)+" "+getProbModel(arl_term_testing.get(j),c,tabel_model));

                    }else{
//                p_term_all=p_term_all+getProbModel(row_model_null,c,data_model);//log 10
                        p_term_all=p_term_all*getProbModel(row_model_null,c,tabel_model);//salah
//                        System.out.println(arl_term_testing.get(j)+" "+getProbModel(row_model_null,c,tabel_model));
                    }
                }
            }
            //hitung cmap untuk kelas c
            prob = p_class*p_term_all;
//            System.out.println(" "+p_term_all+" "+p_class+" "+prob);
        }catch(Exception e){
            error = e.toString();
        }
        return prob;
    }

    private boolean cekExistTerm(String term, String tabel_model) {
        //cekexist term in row tabel
        boolean existed=false;
        try {
            db_model.getProbModel(term,tabel_model);
            if (db_model.getResult().next()) {
                existed=true;
            }
        }catch(Exception e){
            error = e.toString();
        }
        return existed;
    }

    public double getProbModel(String term, boolean c, String tabel_model){
        double p_term=0;
        try{
            db_model.getProbModel(term,tabel_model);
            if(db_model.getResult().next()){
                if(c==true){
                    p_term=Double.valueOf(db_model.getResult().getDouble(3));
                }else{
                    p_term=Double.valueOf(db_model.getResult().getDouble(4));
                }
//                System.out.println(p_term);
            }
        }catch(Exception e){
            error = e.toString();
        }
        return p_term;
    }
/**************************************************************************************************************************************/
    public boolean naiveBayesClass(ArrayList<String> arl_term_testing, DataModel data_model){
        boolean nbc = false;

        setC_map_true(cMAP(arl_term_testing,true,data_model));
        setC_map_false(cMAP(arl_term_testing,false,data_model));

        if(getC_map_true()>getC_map_false()){
            nbc = true;
        }
        return nbc;
    }

    public double cMAP(ArrayList<String> arl_term_testing, boolean c, DataModel data_model){
        String row_model_class="1111";
        String row_model_null="0000";
        double prob=0;
        double p_class=0;
//        double p_term_all=0;//log 10
        double p_term_all=1;
        try{
            p_class=getProbModel(row_model_class, c, data_model);
            if(arl_term_testing.size()==0){
                p_term_all=0;
            }else {
                for (int j = 0; j < arl_term_testing.size(); j++) {
                    if (cekExistTerm(arl_term_testing.get(j), data_model)) {
//                p_term_all=p_term_all+getProbModel(arl_term_testing.get(j),c,data_model);//log 10
                        p_term_all = p_term_all * getProbModel(arl_term_testing.get(j), c, data_model);//salah
                    } else {
//                p_term_all=p_term_all+getProbModel(row_model_null,c,data_model);//log 10
                        p_term_all = p_term_all * getProbModel(row_model_null, c, data_model);//salah
                    }
                }
            }
            //hitung cmap untuk kelas c
            prob = p_class*p_term_all;
//            System.out.println(" "+p_term_all+" "+p_class);
        }catch(Exception e){
            error = e.toString();
        }
        return prob;
    }

    private boolean cekExistTerm(String term, DataModel data_model) {
        //cekexist key hashmap
        if (data_model.getProb_true().containsKey(term)) { //getProb_true or false sama saja
            return true;
        }else{
            return false;
        }
    }

    public double getProbModel(String term, boolean c, DataModel data_model){
        double p_term=0;
        try{
                if(c==true){
                    p_term = data_model.getProb_true().get(term);
                }else{
                    p_term = data_model.getProb_false().get(term);
                }
//                System.out.println(p_term);
        }catch(Exception e){
            error = e.toString();
        }
        return p_term;
    }

    public String getError(){
        return this.error;
    }
}
