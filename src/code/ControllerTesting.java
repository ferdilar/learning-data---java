package code;

import java.awt.*;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Ferdila Rahmi on 8/22/2016.
 */
public class ControllerTesting {
    @FXML
    private TextArea textarea_sms_testing;
    @FXML
    private Button btn_test;
    @FXML
    private Label lbl_sms_testing;
    @FXML
    private Label lbl_prob_testing;

//    ControllerTesting(){
//        super();
//    }
    @FXML
    public void clickBtnTest() throws Exception {
        System.out.println(">Processing Test ...");
//        System.out.println(textarea_sms_testing.getText().toString());

        String sms_testing = "";
        sms_testing = textarea_sms_testing.getText().toString();
        testing(sms_testing);
    }
    public void testing(String sms_testing) throws Exception {

        String data_after = "";
        String resume_prob = "";
        boolean nbc;
        ArrayList<String> arl_term_testing = new ArrayList<String>();

        ProCleansing pro_cleansing = new ProCleansing();
        ProTokenizing pro_tokenizing = new ProTokenizing();

        data_after = pro_cleansing.caseFolding(sms_testing);
        data_after = pro_cleansing.removeChar(data_after);

        //proses tokenzing terdapat filtering, stemming return nilai array list
        arl_term_testing = pro_tokenizing.tokenizing(data_after);

        String data_last="";
        for(int i=0;i<arl_term_testing.size();i++){
            data_last=data_last+ " " +arl_term_testing.get(i);
        }
        NaiveBayes naive_bayes = new NaiveBayes();
        nbc = naive_bayes.naiveBayesClass(arl_term_testing,"model1");

        if(nbc==false){
            resume_prob = "Kelas NOT SPAM\np(d|notspam) :"+naive_bayes.getC_map_false()+"\np(d|spam) :"+naive_bayes.getC_map_true()+"\n";
        }else{
            resume_prob = "Kelas SPAM\np(d|notspam) :"+naive_bayes.getC_map_false()+"\np(d|spam) :"+naive_bayes.getC_map_true()+"\n";
            nbc = naive_bayes.naiveBayesClass(arl_term_testing,"model2");
            if(nbc==true){
                resume_prob = resume_prob+"Kelas FRAUD\np(d|fraud) :"+naive_bayes.getC_map_true()+"\np(d|promo) :"+naive_bayes.getC_map_false()+"\n";
            }else{
                resume_prob = resume_prob+"Kelas PROMO\np(d|fraud) :"+naive_bayes.getC_map_true()+"\np(d|promo) :"+naive_bayes.getC_map_false()+"\n";
            }
        }
        lbl_prob_testing.setText(resume_prob);
        lbl_sms_testing.setText("0> "+sms_testing+"\n1> "+data_after+"\n2>"+data_last);
        System.out.println("<Done Test");
    }
}
