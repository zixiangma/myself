package com.zxm;

import com.mzx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TransactionTest {
    @Autowired
    @Qualifier("accountServiceProgram")
    private AccountService accountServicePragram;
    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        accountServicePragram.transfer("李四","张三",100d);
    }

    @Test
    public void test2(){
        accountService.transfer("李四","张三",100d);
    }
}
