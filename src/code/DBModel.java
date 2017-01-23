package code;

import java.sql.*;
/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class DBModel  extends DBHelper {
    public DBModel()throws Exception, SQLException {
        super();
    }

    public void getDataTraining(String query){
        try{
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void getDataModel(String query){
        try{
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void queryTable(String query){
        try{
            sqlTable(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public void getStopwordByTerm(String term){
        try{
            String query = "SELECT * FROM STOPWORD WHERE STOPWORD='" + term + "'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public void getSynonymByTerm(String term){
        try{
            String query = "SELECT * FROM SYNONYM WHERE KATA_INPUT='" + term + "'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public void searchStem(String term){
        try{
            String query = "SELECT * FROM STEMWORD WHERE KATADASAR='" + term + "'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    //testing
    public void getProbModel(String term, String data_model){
        try{
            String query = "SELECT * FROM "+ data_model +" WHERE TERM=\""+term+"\"";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    //Eval

    public void getDBEvalTest1(int a, int partisi){
        try{
            String query = "SELECT * FROM DATA_TRAINING1 WHERE ID_DATA BETWEEN "+a+" AND "+partisi;
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void getDBEvalTest2(int a, int partisi){
        try{
            String query = "SELECT * FROM DATA_TRAINING2 WHERE CLASS_1 = 1 AND ID_DATA BETWEEN "+a+" AND "+partisi;
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
