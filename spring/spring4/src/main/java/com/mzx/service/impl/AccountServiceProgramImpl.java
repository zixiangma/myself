package com.mzx.service.impl;

import com.mzx.dao.AccountDao;
import com.mzx.domain.Account;
import com.mzx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
@Service("accountServiceProgram")
public class AccountServiceProgramImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void access() {
        accountDao.access();
        System.out.println("访问到了accountServiceImpl的access类中");
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void delete(Account account) {
        accountDao.delete(account);
    }

    @Override
    public List<Account> findList() {
        return accountDao.findList();
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void transfer(String sendName, String receiveName, Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.sendMoney(sendName,money);
//                int i =1/0;
                accountDao.receiveMoney(receiveName,money);
            }
        });
    }
}
