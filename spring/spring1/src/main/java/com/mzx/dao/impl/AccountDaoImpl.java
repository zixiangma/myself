package com.mzx.dao.impl;

import com.mzx.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void access() {
        System.out.println("访问了AccountDaoImpl的access方法中");
    }
}
