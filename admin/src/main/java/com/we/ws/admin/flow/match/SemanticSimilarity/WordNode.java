package com.we.ws.admin.flow.match.SemanticSimilarity;

import java.util.TreeMap;

/**
 * Created by xuxyu on 2017/8/18.
 */
public class WordNode implements Comparable<WordNode> {
    String word;
    TreeMap<WordNode, Double> nodeMap;

    public WordNode(String word) {
        this.word = word;
        this.nodeMap = new TreeMap<>();
    }

    public WordNode(String word, TreeMap<WordNode, Double> nodeMap){
        this.word = word;
        this.nodeMap = nodeMap;
    }

    public void setWord(String word) {
        this. word = word;
    }

    public String getWord() {
        return this.word;
    }

    public void setNodeMap(TreeMap<WordNode, Double> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public TreeMap<WordNode, Double> getNodeMap(){
        return this.nodeMap;
    }

    public int compareTo(WordNode otherNode){
        int relation = this.word.compareTo(otherNode.word);
        if(relation > 0)
            return 1;
        else if(relation < 0)
            return -1;
        else
            return 0;
    }
}
