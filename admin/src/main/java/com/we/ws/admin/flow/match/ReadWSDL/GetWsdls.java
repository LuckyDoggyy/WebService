package com.we.ws.admin.flow.match.ReadWSDL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuxyu on 2017/8/23.
 */


public class GetWsdls {
    /**
     * 要分析的网页
     */
    String htmlUrl;

    /**
     * 分析结果
     */
    ArrayList<String> hrefList = new ArrayList();

    /**
     * 网页编码方式
     */
    String charSet;

    public GetWsdls(String htmlUrl) {
        // TODO 自动生成的构造函数存根
        this.htmlUrl = htmlUrl;
    }

    /**
     * 获取分析结果
     *
     * @throws IOException
     */
    public ArrayList<String> getHrefList() throws IOException {

        parser();
        return hrefList;
    }

    /**
     * 解析网页链接
     *
     * @return
     * @throws IOException
     */
    private void parser() throws IOException {
        URL url = new URL(htmlUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);

        String contenttype = connection.getContentType();
        charSet = getCharset(contenttype);

        InputStreamReader isr = new InputStreamReader(
                connection.getInputStream(), charSet);
        BufferedReader br = new BufferedReader(isr);

        String str = null, rs = null;
        while ((str = br.readLine()) != null) {
            rs = getHref(str);

            if (rs != null)
                hrefList.add(rs);
        }

    }

    /**
     * 获取网页编码方式
     *
     * @param str
     */
    private String getCharset(String str) {
        Pattern pattern = Pattern.compile("charset=.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return matcher.group(0).split("charset=")[1];
        return null;
    }

    /**
     * 从一行字符串中读取链接
     *
     * @return
     */
    private String getHref(String str) {
        Pattern pattern = Pattern.compile("<a href=.*</a>");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return matcher.group(0);
        return null;
    }

    public ArrayList<String> getWsdlsList() throws IOException {
        ArrayList<String> hrefList = getHrefList();
        for (int i = 0; i < hrefList.size(); i++){
            String line = hrefList.get(i);
            String wsdl = htmlUrl + line.substring(line.indexOf("\"") + 1, line.indexOf(">") - 1);
            hrefList.add(i,wsdl);
            hrefList.remove(i+1);
        }
        hrefList.remove(0);
        System.out.println("Wsdls added accomplished");
        return hrefList;
    }

    public static void main(String [] args) throws Exception{
        GetWsdls gw = new GetWsdls("http://127.0.0.1/wsdl/");
        ArrayList<String> wl = gw.getWsdlsList();
        for( int i = 0 ; i < wl.size() ; i ++)
            System.out.println(wl.get(i));
    }


}