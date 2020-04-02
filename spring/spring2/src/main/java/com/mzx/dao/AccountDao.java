package com.mzx.dao;

import com.mzx.domain.Account;

import java.util.List;

public interface AccountDao {
    void access();

    void save(Account account);

    void delete(Account account);

    List<Account> findList();

    void update(Account account);
}
