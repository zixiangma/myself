package com.zxm;

import com.mzx.Configuration;
import com.mzx.domain.Account;
import com.mzx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class FullAnnotationTest {
    @Autowired
    AccountService accountService;

    @Test
    public void test() {
        List<Account> list = accountService.findList();
        System.out.println(list);
    }
}
