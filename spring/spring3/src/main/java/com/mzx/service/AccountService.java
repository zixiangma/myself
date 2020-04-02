package com.mzx.service;

import com.mzx.domain.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    void access();

    void save(Account account) throws SQLException;

    void delete(Account account) throws SQLException;

    List<Account> findList() throws SQLException;

    void update(Account account) throws SQLException;

    void transferMoney(String sendName,String receiveName,Double money) throws SQLException;
}
