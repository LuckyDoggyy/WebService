package com.we.ws.admin.flow.match.SemanticSimilarity;

public class WordSimilarity {

    Lin lin;
    WuAndPalmer wup;
    JWS jws;

    public WordSimilarity(){
        String dir = "D:/WordNet";
        String icFile = dir + "/WordNet-InfoContent-2.1/ic-semcor.dat";
        jws = new JWS(dir, icFile);
        lin = jws.getLin();
        wup = jws.getWuAndPalmer();
    }

    public double getSimilarity(String word1, String word2){
        if(word1 == null || word2 == null)
            return 0;
        if(word1.equals(word2))
            return 1;
        double linSimilarity = Math.max(lin.max(word1, word2, "n"),lin.max(word1, word2, "v"));
        double wupSimilarity = Math.max(wup.max(word1, word2, "n"),wup.max(word1, word2, "v"));
        return (linSimilarity + wupSimilarity)/2;
    }



    public static void main(String[] args) {

        WordSimilarity ws = new WordSimilarity();

// max.
        System.out.println("\nhighest score\t=\t" + ws.getSimilarity("skiing", "skiing") + "\n\n\n");

// ... and so on for any other measure


    }
} // eof
