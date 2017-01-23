package code;

/**
 * Created by Ferdila Rahmi on 9/2/2016.
 */
public class Evaluation {

    DataEval[] data_eval;
    DBModel db_model;
    private String error;

    Training training;
    Testing testing;

    Evaluation() throws Exception {
        super();
        db_model = new DBModel();
    }

    public DataEval getData_eval(int i){
        return data_eval[i];
    }

    public void crossValidate(int k, String query){
        int partisi=0;
        int n_data=0;
        int i_first=0;
        int i_last=0;

        data_eval = new DataEval[k];

        try{
            db_model.getDataTraining(query);
            if(db_model.getResult().last()){
                n_data = db_model.getResult().getRow();
                db_model.getResult().beforeFirst();
            }
            for(int i=0;i<k;i++){
                data_eval[i] = new DataEval();
                String querytrain="";
                String querytest="";

                String document="";
                boolean kelas;
                boolean kelas_nbc;

                partisi = n_data/k;
                i_first = (i*partisi)+1;
                i_last = i_first+partisi-1;
                //get data not between i_first -> i_last
                querytrain = query+" where id_data not between " + i_first + " and " + i_last;

                training = new Training();
                DataModel data_model = new DataModel();
                data_model = training.getData_model(querytrain);
//                data_model = training.getData_model(query);

                testing = new Testing();
                //get data_testing dari DB
                querytest = query+" where id_data between " + i_first + " and " + i_last;
                db_model.getDataTraining(querytest);
//                db_model.getDataTraining(query);
                while(db_model.getResult().next()) {
                    document = db_model.getResult().getString(2);
                    kelas = db_model.getResult().getBoolean(3);

                    //kirim documen return nbc compare with kelas
                    kelas_nbc = testing.evalTesting(document, data_model);
                    if(kelas==true){
                        if(kelas_nbc==true){//true -> predict true (a)tp
                            data_eval[i].setTp(data_eval[i].getTp()+1);
                        }else{//true -> predict false(b)tn
                            data_eval[i].setTn(data_eval[i].getTn()+1);
//                            System.out.println("TN>>"+document);
                        }
                    }else{
                        if(kelas_nbc==false){//false -> predict false (d)fn
                            data_eval[i].setFn(data_eval[i].getFn()+1);
                        }else{//false -> predict true (c)fp
                            data_eval[i].setFp(data_eval[i].getFp()+1);
//                            System.out.println("FP>>"+document);
                        }
                    }
                }
                //kita punya data_model, dengan data_model lakukan testing evaluation
            }
        }catch(Exception e){
            error = e.toString();
        }

    }

    public String getError(){
        return this.error;
    }
}
