package code;

import java.util.ArrayList;

/**
 * Created by Ferdila Rahmi on 9/1/2016.
 * evaluasi
 */
public class Testing {
    Testing(){
        super();
    }
    public boolean evalTesting(String sms_testing, DataModel data_model) throws Exception {

        String data_after = "";
        boolean nbc;
        ArrayList<String> arl_term_testing = new ArrayList<String>();

        ProCleansing pro_cleansing = new ProCleansing();
        ProTokenizing pro_tokenizing = new ProTokenizing();

        data_after = pro_cleansing.caseFolding(sms_testing);
        data_after = pro_cleansing.removeChar(data_after);

        //proses tokenzing terdapat filtering, stemming return nilai array list
        arl_term_testing = pro_tokenizing.tokenizing(data_after);
        NaiveBayes naive_bayes = new NaiveBayes();
        nbc = naive_bayes.naiveBayesClass(arl_term_testing,data_model);

        return nbc;
    }
}
