package code;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ferdila Rahmi on 9/1/2016.
 */
public class Training {
    DBModel db_model;
    private String error;
    int n_attribut;
    ArrayList<String> attribute;
    int n_data;
    int n_true;
    int n_false;

    String lbl_true;
    String lbl_false;
    String row_model_class;
    String row_model_null;

    DataModel data_model = new DataModel();
    DataTermFreq data_termfreq = new DataTermFreq();

    Training() throws Exception {
        super();
        db_model = new DBModel();
        attribute = new ArrayList<String>();

        n_data=0;
        n_true=0;
        n_false=0;
        lbl_true="true";
        lbl_false="false";
        row_model_class="1111";
        row_model_null="0000";

        attribute.add(row_model_class);
        n_attribut = 1;
    }
    public DataModel getData_model(String query) throws Exception {
        genModel(query);
        return data_model;
    }
    /******************************************************************************/
    public ArrayList<String> getAttribute(){
        //pastikan sudah genModel
        return attribute;
    }
    public void genModel(String query) throws Exception {
        String document;
        boolean kelas;

        ArrayList<String> token = new ArrayList<String>();

        try{
            db_model.getDataTraining(query);
            if(db_model.getResult().last()){
                n_data = db_model.getResult().getRow();
                db_model.getResult().beforeFirst();
            }
            while(db_model.getResult().next()){
                document = db_model.getResult().getString(2);
                kelas = db_model.getResult().getBoolean(3);
//                System.out.println(document);
                String data_after="";
                if(kelas == true){
                    n_true++;
                }else{
                    n_false++;
                }

                ProCleansing pro_cleansing = new ProCleansing();
                document = pro_cleansing.caseFolding(document);
                document = pro_cleansing.removeChar(document);

                //proses tokenzing terdapat filtering, stemming return nilai array list
                ProTokenizing pro_tokenizing = new ProTokenizing();
                token = pro_tokenizing.tokenizing(document);

                for(int j=0;j<token.size();j++){
                    data_after = data_after+" "+token.get(j);
                    cekAttribute(token.get(j),kelas);//jika merupakan kelas spam maka lakukan generate attribut 2
                }
//                System.out.println(data_after+","+kelas);
            }
            updateProbClass();
            updateProbModel();//ngeproses sebelum attribut 0000 diisi
            attribute.add(row_model_null);//penting ga penting
            updateProbNull();
//            System.out.println("************************************************");
//            printHashMap(data_termfreq.getClass_false());
//            System.out.println("************************************************");
//            printHashMap(data_termfreq.getClass_true());
//            System.out.println("************************************************");
//            printHashMap(data_termfreq.getClass_all());
        }catch(Exception e){
            error = e.toString();
        }
    }
    public void cekAttribute(String term, boolean kelas){
        if(n_attribut==1){
            attribute.add(term);
//            System.out.println(term);
            n_attribut++;
        }else{
            int i=0;
            boolean found=false;
            while(i<n_attribut && found==false){//n_attribut_1 represent atribut yang sudah di add ke array list
                if(term.equals(attribute.get(i))){//cek apakah attribut sebelumnya sudah data, data yang seudah ada sebanyak n_attribut_1
                    found=true;
                }
                i++;
            }
            if(found==false){
                attribute.add(term);
//                System.out.println(term);
                n_attribut++;
            }
        }
        countTermModel(term, kelas);
    }
    public void countTermModel(String term, boolean kelas){
        int count;
        count = cekKeyExist(data_termfreq.getClass_all(), term);
        data_termfreq.getClass_all().put(term,count+1);
//        System.out.println(term + " : " + hashMap_all_model1.get(term));
        if(kelas==true){//spam
            countTermTrue(term);
        }else{
            countTermFalse(term);
        }
    }
    public void countTermTrue(String term){
        int count=0;
        count = cekKeyExist(data_termfreq.getClass_true(), term);
        data_termfreq.getClass_true().put(term,count+1);
        count = cekKeyExist(data_termfreq.getEach_class(), lbl_true);
        data_termfreq.getEach_class().put(lbl_true,count+1);
//        System.out.println("[SPAM] " + term + " : " + hashMap_spam.get(term));
    }
    public void countTermFalse(String term){
        int count=0;
        count = cekKeyExist(data_termfreq.getClass_false(), term);
        data_termfreq.getClass_false().put(term,count+1);
        count = cekKeyExist(data_termfreq.getEach_class(), lbl_false);
        data_termfreq.getEach_class().put(lbl_false,count+1);
//        System.out.println("[NOTSPAM] " + term + " : " + hashMap_notspam.get(term));
    }
    public int cekKeyExist(HashMap hashMap, String term){
        int count = 0;
        if (hashMap.containsKey(term)) {
            count = (int) hashMap.get(term);
        }
        return count;
    }
    public void printHashMap(HashMap hashMap){
        for(int i=0;i<attribute.size();i++){
            System.out.println(attribute.get(i) + "\t" + hashMap.get(attribute.get(i)));
        }
//        System.out.println("True :" + "\t" + data_termfreq.getEach_class().get(lbl_true));
//        System.out.println("False :" + "\t" + data_termfreq.getEach_class().get(lbl_false));
    }

    /***************************************/

    public double countProb(int a, int b, int c) throws Exception {
        double p=0;
        if(b>0||c>0){
//            p=Math.log10((double)(a+1)/(b+c));//log 10
            p=(double)(a+1)/(b+c);//salah
//            System.out.println("("+a+"+1)/("+b+"+"+c+")");
//            System.out.println("p : "+p);
        }
        return p;
    }
    public void updateProbClass() throws Exception {//0000
        //      log n(c)/n(s);
        int n_s=n_data;
            if(n_false == n_s && n_false > 0){//set 1 karena log 1=0
                data_model.getProb_false().put(row_model_class, (double) 1);
            }else if(n_false > 0 && n_s > 0){
//                System.out.println("("+n_false+")/("+n_s+")");
//                System.out.println("p : "+Math.log10((double)(n_false)/(n_s)));
//                data_model.getProb_false().put(row_model_class,Math.log10((double)(n_false)/(n_s)));//log 10
                data_model.getProb_false().put(row_model_class,(double)(n_false)/(n_s));//salah
            }else{
                data_model.getProb_false().put(row_model_class, (double) 0);
            }

            /*--------------------------------------------------------------*/
            if(n_true == n_s && n_true > 0){
                data_model.getProb_true().put(row_model_class, (double) 1);
            }else if(n_true > 0 && n_s > 0){
//                System.out.println("("+n_true+")/("+n_s+")");
//                System.out.println("p : "+Math.log10((double)(n_true)/(n_s)));
//                data_model.getProb_true().put(row_model_class,Math.log10((double)(n_true)/(n_s)));//log 10
                data_model.getProb_true().put(row_model_class,(double)(n_true)/(n_s));//salah
            }else {
                data_model.getProb_true().put(row_model_class, (double) 0);
            }
//        System.out.println(data_model.getProb_false().get(row_model_class));
//        System.out.println(data_model.getProb_true().get(row_model_class));
    }

    public void updateProbModel() throws Exception {//pakai data dari arl_attribute
        //hitung probab berdasarkan term pada arl_attribute terhadap data pada table term_freq
        //perulangan sebanyak arl_attribute
        String term;
        int a,b,c;
        double p;
            for(int i=1;i<attribute.size();i++){//i 1 karena 0000
                term=attribute.get(i);

                c = cekKeyExist(data_termfreq.getClass_all(), term);

                a = cekKeyExist(data_termfreq.getClass_false(), term);
                b = cekKeyExist(data_termfreq.getEach_class(), lbl_false);

                p = countProb(a,b,c);
                //update data_model
                data_model.getProb_false().put(term,p);


                a = cekKeyExist(data_termfreq.getClass_true(), term);
                b = cekKeyExist(data_termfreq.getEach_class(), lbl_true);

                p = countProb(a,b,c);
                //update data_model
                data_model.getProb_true().put(term,p);
            }
    }
    public void updateProbNull() throws Exception {
        int a=0,b,c=0;
        double p;
        b = cekKeyExist(data_termfreq.getEach_class(), lbl_false);

        p = countProb(a,b,c);
//        System.out.println(a+" "+b+" "+c+" "+p);
        //update data_model
        data_model.getProb_false().put(row_model_null,p);

        b = cekKeyExist(data_termfreq.getEach_class(), lbl_true);

        p = countProb(a,b,c);
//        System.out.println(a+" "+b+" "+c+" "+p);
        //update data_model
        data_model.getProb_true().put(row_model_null,p);
    }
    public String getError(){
        return this.error;
    }
}
