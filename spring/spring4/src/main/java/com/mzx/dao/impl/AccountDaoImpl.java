package com.mzx.dao.impl;

import com.mzx.dao.AccountDao;
import com.mzx.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Override
    public void access() {
        System.out.println("访问了AccountDaoImpl的access方法中");
    }

    @Autowired
    public void setDataBase(DataSource dataBase) {
        this.setDataSource(dataBase);
    }

    @Override
    public void save(Account account) {
        String sql  = "insert into account (name,money) values (?,?)";
        this.getJdbcTemplate().update(sql,account.getName(),account.getMoney());
    }

    @Override
    public void delete(Account account) {
        String sql = "delete from account where id = ?";
        this.getJdbcTemplate().update(sql,account.getId());
    }

    @Override
    public List<Account> findList() {
        String sql = "select * from account";
        return this.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public void update(Account account) {
        String sql = "update account set name= ?,money=? where id = ?";
        this.getJdbcTemplate().update(sql,account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public void sendMoney(String sendName, Double money) {
        String sql = "update account set money = money - ? where name = ?";
        this.getJdbcTemplate().update(sql,money,sendName);
    }

    @Override
    public void receiveMoney(String receiveName, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql,money,receiveName);
    }
}
