package com.mzx.service;

import com.mzx.domain.Account;

import java.util.List;

public interface AccountService {
    void access();

    void save(Account account);

    void delete(Account account);

    List<Account> findList();

    void update(Account account);

    void transfer(String sendName,String receiveName,Double money);
}
