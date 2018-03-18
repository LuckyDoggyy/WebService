package com.we.ws.admin.flow.match.SemanticSimilarity;//package edu.sussex.nlp.jws;


import edu.mit.jwi.*;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.data.*;
import edu.mit.jwi.data.compare.*;
import edu.mit.jwi.data.parse.*;
import edu.mit.jwi.item.*;
import edu.mit.jwi.morph.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// David Hope, 2008, University Of Sussex
public class JWS {
    private String wnhome = "";
    private String icfilename = "";
    private URL url = null;
    private IDictionary dict = null;
    private ArrayList<ISynsetID> roots = null;


    private ICFinder icfinder = null;
    private Lin lin = null;
    private WuAndPalmer wup = null;


    // constructor 1 - use user specified IC file
    public JWS(String dir, String icfile) throws Exception{
//        System.out.println("Loading modules");

        wnhome = dir+"/dict";
        icfilename = icfile; // user defined IC file
        if (!exists(wnhome) || !exists(icfilename)) {
            System.out.println("your directory paths are wrong:\n" + wnhome + "\n" + icfilename);
            System.exit(1);
        }
//        System.out.println("set up:");
        initialiseWordNet();

// get <roots>
//        System.out.println("... finding noun and verb <roots>");
        roots = new ArrayList<ISynsetID>();
// get noun <roots>
        getRoots(POS.NOUN);
// get verb <roots>
        getRoots(POS.VERB);

        icfinder = new ICFinder(icfilename);
        lin = new Lin(dict, icfinder);
        wup = new WuAndPalmer(dict, roots);
//        System.out.println("\n\nJava WordNet::Similarity using WordNet " + dict.getVersion() + " : loaded\n\n\n");
    }

    // !!! put in JWS !!!
    private void getRoots(POS pos) {
        ISynset synset = null;
        Iterator<ISynset> iterator = null;
        List<ISynsetID> hypernyms = null;
        List<ISynsetID> hypernym_instances = null;
        iterator = dict.getSynsetIterator(pos);
        while (iterator.hasNext()) {
            synset = iterator.next();
            hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);                    // !!! if any of these point back (up) to synset then we have an inf. loop !!!
            hypernym_instances = synset.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
            if (hypernyms.isEmpty() && hypernym_instances.isEmpty()) {
                roots.add(synset.getID());
            }
        }
    }


    private boolean exists(String dir) {
        return (new File(dir)).exists();
    }

    private void initialiseWordNet() throws Exception {
        try {
            url = new URL("file", null, wnhome);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) return;
        dict = new Dictionary(url);
        dict.open();
    }

    public Lin getLin() {
        return (lin);
    }

    public WuAndPalmer getWuAndPalmer() {
        return (wup);
    }

    // ......................................................................................................................................................
    public IDictionary getDictionary() {
        return (dict);
    }
// ......................................................................................................................................................
    //public SemCorFinder getSemCorFinder()
    //{
    //	return ( semcor );
    //}

}
