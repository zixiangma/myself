package com.mzx.transaManager;

public class quickly {
    /**
     * spring的事务管理是基于aop提供的一个功能,她托管了jdbc的事务
     *
     * jdbcTemplate 是spring提供的一个jdbc模版,用来操作数据库
     *
     * PlatformTransactionManager是spring提供的一个接口,内含控制事务的方法
     * 其中TransactionDefinition是事务的定义信息对象
     * 他对事务的隔离级别/事务的传播行为/事务的超时时间/事务的读写方法有着明确的设定
     * 首先是事务的隔离级别  isolation
     * isolation_READ_UNCOMMITTED 隔离级别为不提交,此种情况,存在脏读/不可重复读/幻读的情况
     * isolation_READ_COMMITTED 隔离界别为提交,此种情况消除了脏读
     * isolation_REPEATABLE_READ     隔离级别为可重复读,此种情况下消除了不可重复读
     * isolation_SERIALIZABLE
     * isolation-Default 代表是当前使用数据库的默认
     *
     * 事务的传播行为,代表在一个方法中调用多种方法,这些方法如果存在事务,应该如何处理
     *
     * required 如果当前没有事务,则创建一个事务;如果当前有事务,则加入到当前事务中;
     * supports 支持当前事务; 如果当前有事务则加入事务,如果当前没有事务,则以无事务的方式进行
     * mandatory 使用当前的事务,如果当前没有事务,则抛出异常
     * required_news 新建事务,如果当前在事务中,吧当前事务挂起,从新开启一个新的事务
     * never 以非事务方式运行,如果当前存在事务,抛出异常
     * Nested 如果当前存在事务,则在嵌套事务内进行,如果当前没有事务,则执行required类似的操作
     *
     *
     * 第一种编程是事务管理,其思路是利用TransactionTemplate对象中的execute方法来重写需要事务代理的方法
     * 此种方法有代码侵入性,使用不灵活,也违反开闭原则,并且依赖transactionTemplate对象
     *  1 创建dataSource对象
     *  2 创建TransactionManager对象 注入dataSource
     *  3 创建transactionTemplate对象,注入属性 TransactionManager
     *  4 在service成 注入属性 transactionTemplate ,重写transferMoney方法
     *  5 执行程序
     *
     *
     *  第二种方法是声明式方法,创建一个transactionManager对象,之后利用tx提供的事务注解来进行事务管理
     *  此种方法灵活且没有代码侵入十分方便
     *  1 创建dataSource对象
     *  2 创建transactionManager对象
     *  3 创建tx 事务管理标签,规定事务的定义信息
     *  4 创建aop 切面编程标签,进行配置
     *
     *  第三种是注解配置
     *  此种方法较为简洁,只需开启事务注解扫描,以及在想要被事务管理的方法或者类上添加@Transaction注解即可,
     *  可以通过注解的属性来配置事务的定义信息,相对于第二种灵活性较差
     *  一般使用第二种以及第三种方法配置
     */
}
