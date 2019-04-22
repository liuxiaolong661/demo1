package com.springboot.demo1.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamUtils{
    /**
     * 将Object转换为xml
     * @param obj 转换的bean
     * @return bean转换为xml
     */
    public static String objectToXml(Object obj) {
        XStream xStream = new XStream();
        //xstream使用注解转换
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }

    /**
     * 将xml转换为T
     * @param <T> 泛型
     * @param xml 要转换为T的xml
     * @param cls T对应的Class
     * @return xml转换为T
     */
    public static <T> T xmlToObject(String xml, Class<T> cls){
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //xstream使用注解转换
        xstream.processAnnotations(cls);
        return (T) xstream.fromXML(xml);
    }
}
