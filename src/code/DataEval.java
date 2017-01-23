package code;

/**
 * Created by Ferdila Rahmi on 9/1/2016.
 */
public class DataEval {
    int tp;
    int tn;
    int fp;
    int fn;
    double accuracy;
    double recall;
    double precision;
    double fmeasure;
    DataEval(){
        super();
        tp = 0;
        tn = 0;
        fp = 0;
        fn = 0;
        accuracy = 0;
        recall = 0;
        precision = 0;
        fmeasure = 0;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getTn() {
        return tn;
    }

    public void setTn(int tn) {
        this.tn = tn;
    }

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public int getFn() {
        return fn;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    /******************************************/

    public double getAccuracy() {
        accuracy = ((double)(tp+fn)/(double)(tp+tn+fp+fn));
        return accuracy*100;
    }

    public double getRecall() {
        recall = ((double)(tp)/(double)(tp+tn));
        return recall;
    }

    public double getPrecision() {
        precision = ((double)(tp)/(double)(tp+fp));
        return precision;
    }

    public double getFmeasure() {
        fmeasure = ((2*(recall*precision))/(recall+precision));
        return fmeasure;
    }

}
