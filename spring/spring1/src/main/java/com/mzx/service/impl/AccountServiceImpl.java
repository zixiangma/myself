package com.mzx.service.impl;

import com.mzx.dao.AccountDao;
import com.mzx.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void access() {
        accountDao.access();
        System.out.println("访问到了accountServiceImpl的access类中");
    }
}
