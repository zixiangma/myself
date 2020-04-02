package com.mzx.dao.impl;

import com.mzx.dao.AccountDao;
import com.mzx.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl  implements AccountDao {
    @Autowired
    private Connection connection;
    @Override
    public void access() {
        System.out.println("访问了AccountDaoImpl的access方法中");
    }
    @Autowired
    private QueryRunner queryRunner;

    @Override
    public void save(Account account) throws SQLException {
        String sql  = "insert into account (name,money) values (?,?)";
        queryRunner.update(connection,sql,account.getName(),account.getMoney());
    }

    @Override
    public void delete(Account account) throws SQLException {
        String sql = "delete from account where id = ?";
        queryRunner.update(connection,sql,account.getId());
    }

    @Override
    public List<Account> findList() throws SQLException {
        String sql = "select * from account";
        return queryRunner.query(connection,sql,new BeanListHandler<>(Account.class));
    }

    @Override
    public void update(Account account) throws SQLException {
        String sql = "update account set name= ?,money=? where id = ?";
        queryRunner.update(connection,sql,account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public void send(String sendName, Double money) throws SQLException {
        String sql = "update account set money = money - ? where name = ?";
        queryRunner.update(connection,sql,money,sendName);
    }

    @Override
    public void receive(String receiveName, Double money) throws SQLException {
        String sql = "update account set money = money - ? where name = ?";
        queryRunner.update(connection,sql,money,receiveName);
    }
}
