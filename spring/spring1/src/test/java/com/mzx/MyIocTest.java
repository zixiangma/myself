package com.mzx;


import com.mzx.myIoc.ClassPathXmlApplicationContext;
import com.mzx.service.AccountService;
import org.junit.Test;



public class MyIocTest {
    @Test
    public void testMyIoc(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("myIoc.xml");
        AccountService accountService = (AccountService) classPathXmlApplicationContext.getBean("accountService");
        accountService.access();
    }
}
