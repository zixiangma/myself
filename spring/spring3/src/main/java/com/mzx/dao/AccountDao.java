package com.mzx.dao;

import com.mzx.domain.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    void access();

    void save(Account account) throws SQLException;

    void delete(Account account) throws SQLException;

    List<Account> findList() throws SQLException;

    void update(Account account) throws SQLException;

    void send(String sendName,Double money) throws SQLException;

    void receive(String receiveName,Double money) throws SQLException;
}
