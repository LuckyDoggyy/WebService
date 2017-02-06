package com.we.ws.common.data;

/**
 * Created by twogoods on 16/10/12.
 */
public class Pair<L, R> {
    private L l;
    private R r;

    public Pair(L left, R right) {
        this.l = left;
        this.r = right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair(left, right);
    }

    public L getL() {
        return this.l;
    }

    public R getR() {
        return this.r;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "l=" + l +
                ", r=" + r +
                '}';
    }
}
