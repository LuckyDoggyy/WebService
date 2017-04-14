package com.we.ws.admin.flow.node;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-14
 */
public abstract class Node implements Handle {

    protected Node next;

    public void setNext(Node next) {
        this.next = next;
    }
}
