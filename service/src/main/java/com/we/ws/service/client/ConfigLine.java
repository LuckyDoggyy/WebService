package com.we.ws.service.client;

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
    private String rule;

    private ConfigLine() {
    }

    public static ConfigLine of(String line) {
        int start = line.indexOf("[");
        int end = line.lastIndexOf("]");
        String rule = null;
        if (start != -1 && end != -1) {
            rule = line.substring(start + 1, end);
            line = line.substring(0, start - 1);
        }

        String[] token = line.split("\\.");
        if (token.length != 3) {
            return null;
        }
        ConfigLine configLine = new ConfigLine();
        configLine.setObjName(token[0]);
        configLine.setType(token[2]);
        configLine.setRule(rule);
        try {
            parseProperty(configLine, token[1]);
        } catch (Exception e) {
            return null;
        }

        return configLine;
    }

    private static void parseProperty(ConfigLine configLine, String placeTwo) throws Exception {
        int index = placeTwo.indexOf("(");
        if (index == -1) {
            configLine.setProperty(placeTwo);
        } else {
            int lastIndex = placeTwo.indexOf(")");
            configLine.setProperty(placeTwo.substring(0, index));
            configLine.setAlies(placeTwo.substring(index + 1, lastIndex));
        }
    }

    public boolean typeIsDefined() {
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getTypeWithRule() {
        return type + "|" + rule;
    }
}
