package com.we.ws.admin.flow.match.SemanticSimilarity;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by xuxyu on 2017/8/17.
 */
public class TextSimilarity {

    WordSimilarity ws;
    KuhnMunkres km = new KuhnMunkres();

    public TextSimilarity() throws Exception {
        ws = new WordSimilarity();
    }


    public String textSegment(String text) throws Exception {  //文本分割，去无关词，变小写
        String handledText = null;
        CharArraySet cas = new CharArraySet(Version.LUCENE_44, 0, true);

        Iterator iterator = StandardAnalyzer.STOP_WORDS_SET.iterator();
        while (iterator.hasNext()) {
            cas.add(iterator.next());
        }

        StandardAnalyzer sa = new StandardAnalyzer(Version.LUCENE_44, cas);
        TokenStream ts = sa.tokenStream("field", text);
        ts.reset();
        CharTermAttribute ch = ts.addAttribute(CharTermAttribute.class);
        while (ts.incrementToken())
            if (ch != null)
                handledText += " " + ch.toString();
        ts.end();
        ts.close();

        return handledText;
    }

    public ArrayList<String> textToList(String text) {   //文本转换为String数组，返回String ArrayList
        ArrayList<String> list = new ArrayList<>();
        String[] words = text.split("[.-_\\s]");
        String[] handledWords = Arrays.copyOfRange(words, 1, words.length);
        Arrays.sort(handledWords);
        for (int i = 0; i < handledWords.length - 1; )
            for (int j = i + 1; j < handledWords.length; j++) {
                if (!handledWords[j].equals(handledWords[i]) && j != handledWords.length - 1) {
                    list.add(handledWords[i]);
                    i = j;
                } else if (handledWords[j].equals(words[i]) && j != handledWords.length - 1) {
                    continue;
                } else if (handledWords[j].equals(words[i]) && j == handledWords.length - 1) {
                    list.add(handledWords[i]);
                    i = j;
                    break;
                } else if (!handledWords[j].equals(words[i]) && j == handledWords.length - 1) {
                    list.add(handledWords[i]);
                    list.add(handledWords[j]);
                    i = j;
                    break;
                }
            }
        return list;
    }

    public double [] formatText(ArrayList<String> words1, ArrayList<String> words2) {   //String数组变为相同长度，添加null，返回权重
        int length = words1.size() > words2.size() ? words1.size() : words2.size();
        int len = words1.size();
        double[] weight = new double[length];
        for (int i = 0; i < words1.size(); i++)
            weight[i] = (double) 1 / (double) len;
        if (words1.size() > words2.size()) {
            for (int i = 0; i < words1.size() - words2.size(); i++)
                words2.add(null);
        } else {
            for (int i = 0; i < words2.size() - words1.size(); i++) {
                words1.add(null);
                weight[len++] = 0;
            }
        }
        return weight;
    }

/*
    public WordNode wordToWordNode(String word, ArrayList<String> words) {
        // 单个word转化为WordNode，words为另一个text
        TreeMap<WordNode, Double> nodeMap = new TreeMap<>();
        for (int i = 0; i < words.size(); i++) {
            WordNode wNode = new WordNode(words.get(i));
            double sim = ws.similarity(word, words.get(i));
            nodeMap.put(wNode, sim);
        }
        return new WordNode(word, nodeMap);
    }

    public ArrayList<WordNode> textToWordNodes(ArrayList<String> words1, ArrayList<String> words2) {
        //words1转化为WordNodeList，
        ArrayList<WordNode> wordNodesList = new ArrayList<WordNode>();
        for (int i = 0; i < words1.size(); i++) {
            WordNode wordNode1 = wordToWordNode(words1.get(i), words2);
            wordNodesList.add(wordNode1);
        }
        return wordNodesList;
    }
*/


    public double[][] setValueMatrix(ArrayList<String> text1, ArrayList<String> text2) {  //计算相似度矩阵
        double[][] value = new double[100][100];
        for (int i = 0; i < text1.size(); i++)
            for (int j = 0; j < text2.size(); j++)
                value[i][j] = ws.getSimilarity(text1.get(i), text2.get(j));
        return value;
    }

    public void printMatrix(double[][] value, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.printf("%6.6f ", value[i][j]);
            }
            System.out.println();
        }
    }

    public double textSimilarity(String text1, String text2) throws Exception {   //计算文本相似度
        text1 = textSegment(text1);
        text2 = textSegment(text2);
        ArrayList<String> text1List = textToList(text1);
        ArrayList<String> text2List = textToList(text2);
        int length = text1List.size() > text2List.size() ? text1List.size() : text2List.size();
        double [] xWeight = formatText(text1List,text2List);
        double [][] value = setValueMatrix(text1List, text2List);
        km.setN(length);
        km.setValue(value);
        km.setxWeight(xWeight);
        return km.getKM(1);
    }

/*
    public static void main(String[] args) throws Exception {
        TextSimilarity ts = new TextSimilarity();
        String text1 = ts.textSegment("This service returns renting price of the pair of a car model and one person bicycle model. The price of car and bicycle is for one day rent.");
        String text2 = ts.textSegment("It is the best service that returns renting price of the given pair of a car model and 1(one) person bicycle model. The price of car and bicycle pair is for one day rent.");
        System.out.println(text1);
        System.out.println();
        System.out.println(text2);
        System.out.println();
        ArrayList<String> list1 = ts.textToList(text1);
        ArrayList<String> list2 = ts.textToList(text2);
        for (String string : list1)
            System.out.println(string);
        System.out.println();
        for (String string : list2)
            System.out.println(string);
        System.out.println();
        ts.formatText(list1, list2);
        for (String string : list1)
            System.out.println(string);
        System.out.println();
        for (String string : list2)
            System.out.println(string);
        System.out.println();
*/
/*
        ArrayList<WordNode> list3 = ts.textToWordNodes(list1, list2);
        for(WordNode wNode : list3){
            System.out.println(wNode.word);
            for(Map.Entry<WordNode, Double> entry : wNode.nodeMap.entrySet())
                System.out.println(entry.getKey().word + " : " + entry.getValue());
            System.out.println();
        }
*//*


        KuhnMunkres km = new KuhnMunkres();
        double[][] value = ts.setValueMatrix(list1, list2);
        double[] weight = ts.formatText(list1, list2);
        int length = list1.size() > list2.size() ? list1.size() : list2.size();
        for (int i = 0; i > length; i++)
            System.out.print(weight[i] + " ");
        System.out.println();
        System.out.println();
        ts.printMatrix(value, length);
        System.out.println();
        System.out.println();
        km.setValue(value);
        km.setN(length);
        km.setxWeight(weight);
        double[][] kmValue = km.getValue();
        ts.printMatrix(kmValue, length);
        System.out.println(km.getKM(1));


    }
*/
}

