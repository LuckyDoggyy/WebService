package com.we.ws.admin.flow.node;


import com.we.ws.admin.flow.FlowException;
import com.we.ws.common.data.Pair;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-23
 */
public class Conditions {

//    public static final String[] all = {"==", "!=", ">", ">=", "<", "<="};

//    public static final String EQUAL = "==";
//    public static final String NOT_EQUAL = "!=";
//    public static final String GREATERTHAN = ">";
//    public static final String GREATERTHANOREQUAL = ">=";
//    public static final String LESSTHAN = "<";
//    public static final String LESSTHANOREQUAL = "<=";

    public static final Condition[] all = new Condition[6];

    static {
        all[0] = new Condition("==", Conditions::equal);
        all[1] = new Condition("!=", Conditions::notequal);
        all[2] = new Condition(">", Conditions::greaterThan);
        all[3] = new Condition(">=", Conditions::greaterThanOrEqual);
        all[4] = new Condition("<", Conditions::lessThan);
        all[5] = new Condition("<=", Conditions::lessThanOrEqual);

    }

    public ConditionExecute conditionExecute = Conditions::equal;


    public static class Condition {
        private String judgment;
        private ConditionExecute conditionExecute;

        public Condition(String judgment, ConditionExecute conditionExecute) {
            this.judgment = judgment;
            this.conditionExecute = conditionExecute;
        }

        public boolean judge(String p1, String p2) {
            return conditionExecute.judge(p1, p2);
        }

        public boolean containedIn(String str) {
            if (StringUtils.isEmpty(str)) return false;
            return str.contains(judgment);
        }

        public Pair<String, String> parseVariable(String str) throws FlowException {
            String[] variables = str.split(judgment);
            if (variables.length != 2) {
                throw new FlowException("not a standard judgment : " + judgment);
            }
            return Pair.of(variables[0], variables[1]);
        }
    }

    @FunctionalInterface
    interface ConditionExecute {
        boolean judge(String p1, String p2);
    }

    private static boolean equal(String p1, String p2) {
        if (p1 == null) {
            return p2 == null;
        }
        return p1.equals(p2);
    }

    private static boolean notequal(String p1, String p2) {
        if (p1 == null) {
            return p2 != null;
        }
        return !(p1.equals(p2));
    }

    private static boolean greaterThan(String p1, String p2) {
        double d1 = Double.parseDouble(p1);
        double d2 = Double.parseDouble(p2);
        return d1 > d2;
    }

    private static boolean greaterThanOrEqual(String p1, String p2) {
        double d1 = Double.parseDouble(p1);
        double d2 = Double.parseDouble(p2);
        return d1 >= d2;
    }

    private static boolean lessThan(String p1, String p2) {
        double d1 = Double.parseDouble(p1);
        double d2 = Double.parseDouble(p2);
        return d1 < d2;
    }

    private static boolean lessThanOrEqual(String p1, String p2) {
        double d1 = Double.parseDouble(p1);
        double d2 = Double.parseDouble(p2);
        return d1 <= d2;
    }

}
