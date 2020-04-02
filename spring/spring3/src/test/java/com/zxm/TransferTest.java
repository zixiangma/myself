package com.zxm;

import com.mzx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TransferTest {
    @Autowired
    private AccountService accountService;
    @Test
    public void testBeforeAop() throws SQLException {
        System.out.println(accountService);
        accountService.transferMoney("李四","张三",100d);
    }

}
