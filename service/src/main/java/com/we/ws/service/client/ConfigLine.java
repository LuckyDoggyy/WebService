package com.we.ws.service.client;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-25
 */
public class ConfigLine {
    private String objName;
    private String property;
    private String alies;
    private String type;

    private ConfigLine() {
    }

    public static ConfigLine of(String line) {
        String[] token = line.split("\\.");
        if (token.length != 3) {
            return null;
        }
        ConfigLine configLine = new ConfigLine();
        configLine.setObjName(token[0]);
        configLine.setType(token[2]);
        try {
            praseProperty(configLine, token[1]);
        } catch (Exception e) {
            return null;
        }
        return configLine;
    }

    private static void praseProperty(ConfigLine configLine, String placeTwo) throws Exception {
        int index = placeTwo.indexOf("(");
        if (index == -1) {
            configLine.setProperty(placeTwo);
        } else {
            int lastIndex = placeTwo.indexOf(")");
            configLine.setProperty(placeTwo.substring(0, index));
            configLine.setAlies(placeTwo.substring(index + 1, lastIndex));
        }
    }

    public boolean typeIsBasic() {
        switch (type) {
            case ConfigConstant.STRING:
                return true;
            case ConfigConstant.INTEGER:
                return true;
            case ConfigConstant.DOUBLE:
                return true;
            case ConfigConstant.DATE:
                return true;
        }
        return false;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
