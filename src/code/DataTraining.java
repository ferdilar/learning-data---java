package code;

/**
 * Created by Ferdila Rahmi on 10/24/2016.
 * ini untuk evaluasi data training random oleh mesin
 */
public class DataTraining {
    int id_data;
    String data_sms;
    boolean kelas;

    DataTraining() {
        super();
    }

    public int getId_data() {
        return id_data;
    }

    public void setId_data(int id_data) {
        this.id_data = id_data;
    }

    public String getData_sms() {
        return data_sms;
    }

    public void setData_sms(String data_sms) {
        this.data_sms = data_sms;
    }

    public boolean getKelas() {
        return kelas;
    }

    public void setKelas(boolean kelas) {
        this.kelas = kelas;
    }
}
