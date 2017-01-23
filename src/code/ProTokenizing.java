package code;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Ferdila Rahmi on 8/23/2016.
 */
public class ProTokenizing {
    ProFiltering pro_filtering;
    ProStemming pro_stemming;

    ProTokenizing() throws Exception {
        super();
        pro_filtering = new ProFiltering();
        pro_stemming = new ProStemming();
    }

    public ArrayList<String> tokenizing(String document) throws Exception {
        StringTokenizer st = new StringTokenizer(document," ");
        ArrayList<String> token1 = new ArrayList<String>();
        String term;
//      System.out.println(st.countTokens());
        while(st.hasMoreElements()){
            term = st.nextToken();
                /*
                    Filtering
                 */
            if(pro_filtering.cekSynonym(term)){
                term = pro_filtering.getTermSynonym(term);
            }

                /*
                    Stemming parah lama
                 */
//            term=pro_stemming.getTermStemming(term);

                /*
                    Stopword
                 */
            if(pro_filtering.cekStopword(term)){
                token1.add(term);
            }
        }
        return token1;
    }
}
