package code;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class ProFiltering {
    DBModel db_model;
    private String error;

    ProFiltering() throws Exception {
        super();
        db_model = new DBModel();
    }
    public boolean cekStopword(String term) throws Exception {
        boolean stopwordNotFound=false;
        try{
            db_model.getStopwordByTerm(term);
            //tidak ditemukan berarti bukan stopword tidak perlu diproses(hapus)
            if(!db_model.getResult().isBeforeFirst()){
                stopwordNotFound=true;
            }
        }catch(Exception e){
            error = e.toString();
        }
        return stopwordNotFound;
    }
    //////////////////////////////////////////////////////
    public boolean cekSynonym(String term){
        boolean filterwordFound=false;
//        System.out.println("cekbaku");
        try{
            db_model.getSynonymByTerm(term);
            //ditemukan berarti belum baku baku
            if(db_model.getResult().isBeforeFirst()){
                filterwordFound=true;
//                System.out.println("cek "+term+"="+filterwordFound);
            }

        }catch(Exception e){
            error = e.toString();
        }
        return filterwordFound;
    }
    public String getTermSynonym(String term){
        try{
            db_model.getSynonymByTerm(term);
            if(db_model.getResult().next()){
                term = db_model.getResult().getString(3);
//                System.out.println("hasil after "+term);
            }
        }catch(Exception e){
            error = e.toString();
        }
        return term;
    }
    public String getError(){
        return this.error;
    }
}
