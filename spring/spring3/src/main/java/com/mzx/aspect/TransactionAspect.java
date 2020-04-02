package com.mzx.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
@Aspect
@Component
public class TransactionAspect {

    @Autowired
    private Connection connection;
//    @Before(value = "execution(* transferMoney(..))")
    public void startTransaction() throws SQLException {
        System.out.println("执行了开始");
        connection.setAutoCommit(false);
    }
//    @AfterReturning("execution(* transferMoney(..))")
    public void commit() throws SQLException {
        System.out.println("执行了后置");
        connection.commit();
    }
//    @AfterThrowing("execution(* transferMoney(..))")
    public void rollback() throws SQLException {
        System.out.println("执行了异常");
        connection.rollback();
    }
//    @After("execution(* transferMoney(..))")
    public void close() throws SQLException {
        System.out.println("执行了最终");
        connection.close();
    }
    @Around("execution(* transferMoney(..))")
    public void around(ProceedingJoinPoint pjp) throws SQLException {
        startTransaction();
        try {
            pjp.proceed();
            commit();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            rollback();
        }finally {
            close();
        }

    }
}
