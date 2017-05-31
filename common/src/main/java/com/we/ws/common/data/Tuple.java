package com.we.ws.common.data;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-23
 */
public class Tuple<L, M, R> {
    private L l;
    private M m;
    private R r;

    private Tuple(L left, M middle, R right) {
        this.l = left;
        this.m = middle;
        this.r = right;
    }

    public static <L, M, R> Tuple<L, M, R> of(L left, M middle, R right) {
        return new Tuple<>(left, middle, right);
    }

    public static <L, M, R> Tuple<L, M, R> of(Pair<L, M> pair, R right) {
        return new Tuple<>(pair.getL(), pair.getR(), right);
    }

    public L getL() {
        return this.l;
    }

    public R getR() {
        return this.r;
    }

    public M getM() {
        return m;
    }
}
