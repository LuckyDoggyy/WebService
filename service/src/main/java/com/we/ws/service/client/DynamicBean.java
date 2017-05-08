package com.we.ws.service.client;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.*;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-04-24
 */
public class DynamicBean {
    private Object object = null;//动态生成的类
    private BeanMap beanMap = null;//存放属性名称以及属性的类型

    public DynamicBean() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public DynamicBean(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    public void setValue(Object property, Object value) {
        beanMap.put(property, value);
    }

    public Object getValue(String property) {
        return beanMap.get(property);
    }

    public Object getObject() {
        return this.object;
    }

    @SuppressWarnings("rawtypes")
    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        return generator.create();
    }

    public static void main(String[] args) {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("name", List.class);
        Object obj = generator.create();
        BeanMap beanMap = BeanMap.create(obj);
        beanMap.put("name", Arrays.asList("hah", "aa"));
        System.out.println(beanMap.get("name"));
    }

}
