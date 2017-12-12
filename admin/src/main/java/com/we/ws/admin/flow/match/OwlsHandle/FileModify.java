package com.we.ws.admin.flow.match.OwlsHandle;

/**
 * Created by xuxyu on 2017/8/5.
 */

import java.io.*;

/**
 * 修改文件
 */
public class FileModify {

    public static void operateDirectory(File file) {
        if (file.isDirectory()){
            File [] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                operateDirectory(files[i]);
            }
        }
        if(fileType(file).equals("owls")){
            operateFile(file);
        } else
            return;
    }

    public static String fileType(File file) {
        String fileName = file.getName();
        String type = fileName.substring(fileName.indexOf(".") + 1,fileName.length());
        return type;
    }

    public static void operateFile(File file) {
        try {

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufReader = new BufferedReader(isr);//数据流读取文件

            StringBuffer strBuffer = new StringBuffer();
            for (String line = null; (line = bufReader.readLine()) != null; line = null) {
                if (line.contains("xmlns:service")) {
                    line = line.replace(line, "xmlns:service   = \"http://www.daml.org/services/owl-s/1.2/Service.owl#\"\n");
                } else if (line.contains("xmlns:process")) {
                    line = line.replace(line, "xmlns:process   = \"http://www.daml.org/services/owl-s/1.2/Process.owl#\"\n");
                } else if (line.contains("xmlns:profile")) {
                    line = line.replace(line, "xmlns:profile   = \"http://www.daml.org/services/owl-s/1.2/Profile.owl#\"\n");
                } else if (line.contains("xmlns:grounding")) {
                    line = line.replace(line, "xmlns:grounding   = \"http://www.daml.org/services/owl-s/1.2/Grounding.owl#\"\n");
                } else if (line.contains("xmlns:expr")) {
                    line = line.replace(line,"xmlns:expr=\"http://www.daml.org/services/owl-s/1.2/generic/Expression.owl#\"\n");
                } else if (line.contains("xmlns:list")) {
                    line = line.replace(line,"xmlns:list=\"http://www.daml.org/services/owl-s/1.2/generic/ObjectList.owl#\"\n");
                }
                strBuffer.append(line);
                strBuffer.append(System.getProperty("line.separator"));
            }
            bufReader.close();
            PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());  //替换后输出的文件位置
            printWriter.write(strBuffer.toString().toCharArray());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        String directory = "E:\\Apache24\\htdocs";
        File file = new File(directory);
        operateDirectory(file);

    }
}