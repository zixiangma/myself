package com.mzx.myIoc;

import com.mzx.dao.AccountDao;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ClassPathXmlApplicationContext {
    private HashMap<String,Object> beanFactory = new HashMap<>();
    private HashMap<String,List<Element>> propertyList = new HashMap<>();
    public ClassPathXmlApplicationContext(String resourcePath) {
        Document document = null;
        InputStream is = null;
        try {
            is = Class.forName("com.mzx.myIoc.ClassPathXmlApplicationContext").getClassLoader().getResourceAsStream(resourcePath);
            SAXReader read = new SAXReader();
            document = read.read(is);
            Element beans = document.getRootElement();
            List<Element> beanList = beans.elements("bean");
            if (beanList != null && beanList.size() > 0) {
                for (Element bean : beanList) {
                    String id = bean.attributeValue("id");
                    String clazz = bean.attributeValue("class");
                    Object obj = Class.forName(clazz).newInstance();
                    beanFactory.put(id,obj);
                    List<Element> propertyList = bean.elements("property");
                    this.propertyList.put(id,propertyList);
                }
                Set<String> idList = beanFactory.keySet();
                for (String id : idList) {
                    List<Element> propertyList = this.propertyList.get(id);
                    if (propertyList != null && propertyList.size() > 0) {
                        for (Element property : propertyList) {
                            Object obj = beanFactory.get(id);
                            Class<?> clazz = obj.getClass();
                            String name = property.attributeValue("name");
                            String ref = property.attributeValue("ref");
                            if (ref==null) {
                                String value = property.attributeValue("value");
                                if (value == null) {
                                    throw new RuntimeException("依赖注入错误");
                                } else {
                                    Method method = clazz.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), String.class);
                                    method.invoke(obj,value);
                                }
                            } else {
                                AccountDao refBean = (AccountDao) beanFactory.get(ref);
                                if (refBean == null) {
                                    throw new RuntimeException("依赖注入错误");
                                }
                                Method method = clazz.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), refBean.getClass().getInterfaces()[0]);
                                method.invoke(obj,refBean);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Object getBean(String id) {
        return beanFactory.get(id);
    }
}
