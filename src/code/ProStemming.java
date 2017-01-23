package code;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class ProStemming {
    DBModel db_model;
    private String error;
    public ProStemming() throws Exception {
        db_model = new DBModel();
    }
    public String getTermStemming(String term){
        if(cekStemword(term)){
            term = cekPartikel(term);
            if(cekStemword(term)){
                term = cekPossPro(term);
                if(cekStemword(term)){
                    term = cekPrefiks1(term);
                    if(cekStemword(term)){
                        term = cekPrefiks2(term);
                        if(cekStemword(term)){
                            term = cekSuffix(term);
                        }
                    }
                }
            }
        }
//        term = cekPartikel(term);
//        term = cekPossPro(cekPartikel(term));
//        System.out.println("term get "+term);
        return term;
    }
    public String cekPartikel(String term){
        if(term.length()>3){
            String partikel = term.substring(term.length()-3);//pastikah strlen>4
//            if(cekStemword(term)){
            if((partikel.equals("kah"))||(partikel.equals("lah"))||(partikel.equals("pun"))){
//                    System.out.println("partikel "+partikel);
                term = term.substring(0,term.length()-3);
            }
//            }
        }
        return term;
    }
    public String cekPossPro(String term){
        if(term.length()>2){
            String partikel = term.substring(term.length()-2);
//            if(cekStemword(term)){
            if((partikel.equals("ku"))||(partikel.equals("mu"))){
                term = term.substring(0,term.length()-2);
            }else if(term.substring(term.length()-3).equals("nya")){
                term = term.substring(0,term.length()-3);
            }
//            }
        }
        return term;
    }
    public String cekPrefiks1(String term){
        if(term.length()>4){
            if(term.substring(0,4).equals("meng")){
                if(term.charAt(4)=='e'||term.charAt(4)=='u'){
                    term = "k".concat(term.substring(4));
                }else{
                    term = term.substring(4);
                }
            }else if(term.substring(0,4).equals("peng")){
                if(term.charAt(4)=='e'||term.charAt(4)=='a'){
                    term = "k".concat(term.substring(4));
                }else{
                    term = term.substring(4);
                }
            }else if(term.substring(0,4).equals("meny")||term.substring(0,4).equals("peny")){
                term = "s".concat(term.substring(4));
            }else if(term.substring(0,3).equals("men")){
                term = term.substring(3);
            }else if(term.substring(0,3).equals("mem")||term.substring(0,3).equals("pen")||term.substring(0,4).equals("pem")){
                if(term.charAt(3)=='a'||term.charAt(3)=='i'||term.charAt(3)=='u'||term.charAt(3)=='e'||term.charAt(3)=='o'){
                    if(term.substring(0,3).equals("pen")){
                        term = "t".concat(term.substring(3));
                    }else{
                        term = "p".concat(term.substring(3));
                    }
                }else{
                    term = term.substring(3);
                }
            }else if(term.substring(0,2).equals("me")){
                term = term.substring(2);
            }else if(term.substring(0,2).equals("pe")){//not
                term = term.substring(2);
            }else if(term.substring(0,2).equals("di")){
                term = term.substring(2);
            }else if(term.substring(0,3).equals("ter")){
                term = term.substring(3);
            }else if(term.substring(0,2).equals("be")){
                term = term.substring(2);
            }else if(term.substring(0,2).equals("ke")){
                term = term.substring(2);
            }
        }
        return term;
    }
    public String cekPrefiks2(String term) {
        if (term.length() > 3) {
            if(term.substring(0,3).equals("ber")){
                term = term.substring(3);
            }else if(term.substring(0,2).equals("be")){
                term = term.substring(2);
            }else if(term.substring(0,3).equals("per") && term.length()>5){
                term = term.substring(3);
            }else if(term.substring(0,3).equals("pe") && term.length()>5){
                term = term.substring(3);
            }else if(term.substring(0,3).equals("pel") && term.length()>5){
                term = term.substring(3);
            }else if(term.substring(0,3).equals("se") && term.length()>5){
                term = term.substring(3);
            }
        }
        return term;
    }
    public String cekSuffix(String term){
        if (term.length() > 3) {
            if(term.substring(term.length()-3).equals("kan")){
                term = term.substring(0,term.length()-3);
            }else if(term.charAt(term.length()-1)=='i'){
                term = term.substring(0,term.length()-1);
            }else if(term.substring(term.length()-2).equals("an")){
                term = term.substring(0,term.length()-2);
            }
        }
        return term;
    }
    public boolean cekStemword(String term){
        boolean stemwordNotFound=false;
        try{
            db_model.searchStem(term);
            //tidak ditemukan maka proses
            if(!db_model.getResult().isBeforeFirst()){
                stemwordNotFound=true;
            }
        }catch(Exception e){
            error = e.toString();
        }
        return stemwordNotFound;
    }

    public String getError(){
        return this.error;
    }
}
