package com.we.ws.service.client;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-03-26
 */
public class KeyValuePair<K,V> {
    private K name;
    private V value;

    private KeyValuePair() {}

    private KeyValuePair(K name, V value) {
        this.name = name;
        this.value = value;
    }

    public static <K,V> KeyValuePair<K,V> of(K name,V value){
        return new KeyValuePair(name,value);
    }


    public K getName() {
        return name;
    }

    public V getValue() {
        return value;
    }
}
