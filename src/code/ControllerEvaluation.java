package code;

import java.util.ArrayList;

/**
 * Created by Ferdila Rahmi on 8/22/2016.
 */
public class ControllerEvaluation{
    int k;

    private String error;

    ControllerEvaluation(){
        super();
        k = 10;
    }

    public void evaluation() throws Exception {
        Evaluation eval = new Evaluation();
        String query;
        DataEval[] data_eval1 =  new DataEval[k];
        DataEval[] data_eval2 =  new DataEval[k];
        /*model 1*/
        /*cara1*/
        query = "SELECT * FROM DATA_TRAINING1";
        /*end cara1*/

        /*cara random*/
//        createTableTrainingRand();
//        query = "SELECT * FROM data_training1 ORDER BY RAND()";
//        insertTableTrainingRand(query);
//        query = "SELECT * FROM data_random";
        /*end cara random*/

        eval.crossValidate(k,query);
        for(int i=0;i<k;i++){
            data_eval1[i] = new DataEval();
            data_eval1[i] = eval.getData_eval(i);
        }

        printDataEval(data_eval1);
        System.out.println("----------------------------------------------------------------");

        /*cara1*/
        query = "SELECT * FROM DATA_TRAINING2";
        /*end cara1*/

        /*cara random*/
//        createTableTrainingRand();
//        query = "SELECT * FROM data_training2 ORDER BY RAND()";
//        insertTableTrainingRand(query);
//        query = "SELECT * FROM data_random";
        /*end cara random*/

        eval.crossValidate(k,query);
        for(int i=0;i<k;i++){
            data_eval2[i] = new DataEval();
            data_eval2[i] = eval.getData_eval(i);
        }

        printDataEval(data_eval2);
    }
    /*cara random*/
    public void createTableTrainingRand() throws Exception {
        DBModel db_model = new DBModel();
        String field="";

        field=field+" data_sms text,";
        field=field+" kelas boolean default 0);";

        String queryDrop="DROP TABLE IF EXISTS data_random;";
        String queryCreate="CREATE TABLE data_random(id_data int auto_increment primary key,"+field;
        try{
            db_model.queryTable(queryDrop);
            db_model.queryTable(queryCreate);
        }catch(Exception e){
            error = e.toString();
        }
    }
    public void insertTableTrainingRand(String query) throws Exception {
        DBModel db_model = new DBModel();
        String insertTable;
        String data_sms;
        try{
            db_model.getDataTraining(query);
            if(db_model.getResult().last()){
                db_model.getResult().beforeFirst();
            }
            while(db_model.getResult().next()) {
                data_sms = db_model.getResult().getString(2);
                data_sms = data_sms.replace("\""," ");//problem with double quotes when insert
                insertTable = "INSERT INTO data_random (id_data, data_sms, kelas) VALUES" +
                        "(NULL,\""+data_sms+"\","+db_model.getResult().getBoolean(3)+")";
                db_model.queryTable(insertTable);
            }
        }catch(Exception e){
            error = e.toString();
        }
    }
    /*end cara random*/
    public void printDataEval(DataEval[] data_eval){
        double total_acc=0;
        double avg_acc=0;
        for(int i=0;i<k;i++){
            System.out.println("\nfold ke -"+(i+1));
            System.out.println("TRUE POSITIF\t"+data_eval[i].getTp());
            System.out.println("TRUE NEGATIF\t"+data_eval[i].getTn());
            System.out.println("FALSE POSITIF\t"+data_eval[i].getFp());
            System.out.println("FALSE NEGATIF\t"+data_eval[i].getFn());

            System.out.println("ACCURACY\t\t"+data_eval[i].getAccuracy());
            System.out.println("RECALL\t\t\t"+data_eval[i].getRecall());
            System.out.println("PRECISION\t\t"+data_eval[i].getPrecision());
            System.out.println("F-MEASURE\t\t"+data_eval[i].getFmeasure());
            total_acc = total_acc + data_eval[i].getAccuracy();
        }
        avg_acc = total_acc/(double)k;
        System.out.println("--------------------------------------------> RATA-RATA ACC\t"+ avg_acc);
    }
    public String getError(){
        return this.error;
    }
}
