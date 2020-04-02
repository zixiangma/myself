package com.mzx.service.impl;

import com.mzx.dao.AccountDao;
import com.mzx.domain.Account;
import com.mzx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;


    @Override
    public void access() {
        accountDao.access();
        System.out.println("访问到了accountServiceImpl的access类中");
    }

    @Override
    public void save(Account account) throws SQLException {
        accountDao.save(account);
    }

    @Override
    public void delete(Account account) throws SQLException {
        accountDao.delete(account);
    }

    @Override
    public List<Account> findList() throws SQLException {
        return accountDao.findList();
    }

    @Override
    public void update(Account account) throws SQLException {
        accountDao.update(account);
    }

    @Override
    public void transferMoney(String sendName, String receiveName, Double money) throws SQLException {
        accountDao.send(sendName,money);
//        int i = 1/0;
        accountDao.send(receiveName,money);
    }
}
