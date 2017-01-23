package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Ferdila Rahmi on 8/22/2016.
 */
public class ControllerModel {
    DBModel db_model;
    private String error;

    Training training;

    ControllerModel() throws Exception {
        super();
        db_model = new DBModel();
    }
    public void model() throws Exception {
        //training data 1
        //set query
        String query="";
        try{
            DataModel data_model1 = new DataModel();
            query = "SELECT * FROM DATA_TRAINING1";

            training = new Training();
            data_model1 = training.getData_model(query);

            //print data model
            createTableModel1();
            insertTableModel1(data_model1);

            /**********************************************/
            System.out.println("----------------------------------------------------------------");
            DataModel data_model2 = new DataModel();
            query = "SELECT * FROM DATA_TRAINING2";

            training = new Training();
            data_model2 = training.getData_model(query);
            createTableModel2();
            insertTableModel2(data_model2);

/***********untuk evalusi data training tanpa cross validate tinggal ganti querytrain/querytest di kelas evaluasi*/
            /*Evaluation eval = new Evaluation();

            System.out.println("Evaluasi1-----------------");
            DataEval[] data_eval =  new DataEval[2];
            query = "SELECT * FROM DATA_TRAINING1";
            eval.crossValidate(1,query);
                data_eval[0] = new DataEval();
                data_eval[0] = eval.getData_eval(0);
            printDataEval(data_eval);

            System.out.println("Evaluasi2-----------------");
            DataEval[] data_eval2 =  new DataEval[2];
            query = "SELECT * FROM DATA_TRAINING2";
            eval.crossValidate(1,query);
            data_eval2[0] = new DataEval();
            data_eval2[0] = eval.getData_eval(0);
            printDataEval(data_eval2);
*/
        }catch(Exception e){
            error = e.toString();
        }

    }

    public void printDataEval(DataEval[] data_eval){
        double total_acc=0;
        double avg_acc=0;
        int i=0;
            System.out.println("Akurasi");
            System.out.println("TRUE POSITIF\t"+data_eval[i].getTp());
            System.out.println("TRUE NEGATIF\t"+data_eval[i].getTn());
            System.out.println("FALSE POSITIF\t"+data_eval[i].getFp());
            System.out.println("FALSE NEGATIF\t"+data_eval[i].getFn());

            System.out.println("ACCURACY\t\t"+data_eval[i].getAccuracy());
            System.out.println("RECALL\t\t\t"+data_eval[i].getRecall());
            System.out.println("PRECISION\t\t"+data_eval[i].getPrecision());
            System.out.println("F-MEASURE\t\t"+data_eval[i].getFmeasure());
    }

    public void createTableModel1(){

        String field="";

        field=field+" term varchar(255),";
        field=field+" prob_spam double default 0,";
        field=field+" prob_notspam double default 0);";

        String queryDrop="DROP TABLE IF EXISTS model1;";
        String queryCreate="CREATE TABLE model1(id_term int auto_increment primary key,"+field;
        try{
            db_model.queryTable(queryDrop);
            db_model.queryTable(queryCreate);
        }catch(Exception e){
            error = e.toString();
        }
    }
    public void insertTableModel1(DataModel data_model){
        ArrayList<String> attibute = new ArrayList<String>();
        attibute = training.getAttribute();

        String insertTable="";
        try{
            for (int i=0;i<attibute.size();i++){
                insertTable = "INSERT INTO model1 (ID_TERM, TERM, prob_spam, prob_notspam) VALUES" +
                        "(NULL,\""+attibute.get(i)+"\","+data_model.getProb_true().get(attibute.get(i))+","+data_model.getProb_false().get(attibute.get(i))+")";
                db_model.queryTable(insertTable);
            }
        }catch(Exception e){
            error = e.toString();
        }
    }
    public void createTableModel2(){

        String field="";

        field=field+" term varchar(255),";
        field=field+" prob_fraud double default 0,";
        field=field+" prob_promo double default 0);";

        String queryDrop="DROP TABLE IF EXISTS model2;";
        String queryCreate="CREATE TABLE model2(id_term int auto_increment primary key,"+field;
        try{
            db_model.queryTable(queryDrop);
            db_model.queryTable(queryCreate);
        }catch(Exception e){
            error = e.toString();
        }
    }
    public void insertTableModel2(DataModel data_model){
        ArrayList<String> attibute = new ArrayList<String>();
        attibute = training.getAttribute();
        String insertTable="";
        try{
            for (int i=0;i<attibute.size();i++){
                insertTable = "INSERT INTO model2 (ID_TERM, TERM, prob_fraud, prob_promo) VALUES" +
                        "(NULL,\""+attibute.get(i)+"\","+data_model.getProb_true().get(attibute.get(i))+","+data_model.getProb_false().get(attibute.get(i))+")";
                db_model.queryTable(insertTable);
//                System.out.println(insertTable);
            }
        }catch(Exception e){
            error = e.toString();
        }
    }
    public String getError(){
        return this.error;
    }
}
