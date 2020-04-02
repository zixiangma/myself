package com.mzx.annotation;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class quickly {

    /**
     * ioc 注解
     * @Compoent/Controller/Service/Respository
     * 此注解用在类上代表获得applicationContext对象时会自动实例化被标注的类
     * 其中Controller/Service/Repository注解都属于Component注解,分别对应web/service/dao层
     * 如果不给value属性赋值,默认bean对象的id为类名首字母小写
     * 在类上还可以填写其他bean标签的属性
     * 其中 @PostConstruct代表 init-method属性  @PreDestroy代表 destroy-method属性
     * @Scope作用范围对应scope属性
     *
     * DI注解
     * @Autowired/@Qualifier/@Resource/@Value
     * @Autowited是Spring提供的自动匹配注入注解,其特点是会自动匹配bean工厂中的对象来进行注入,
     * 先匹配类型,之后匹配名称,首先会根据类型找到所有符合的对象,如果符合的对象只有一个,那么久注入这个对象
     * 如果符合的对象有多个,则根据属性名称去匹配这些对象的id,相同的注入,没有的话就抛出异常
     * @Qualifier注解 一般跟@AutoWired一起使用,用来在有多个符合对象时,指定注入对象的Id
     * @Resource是java自带的一个注解,它的作用与Autowired相同,区别在于它先匹配id之后匹配类型
     * @Value使用来注入基本数据类型时使用的
     *
     *
     * 全注解开发,不配置applicationContext配置文件
     * 首先要创建一个配置类,通过类像bean工厂内添加一些第三方的类
     * @Configuration 注解,用来标识那个是配置类,也可以没有,因为引用时默认是有的
     * @ ComponentScan 注解 用来打开全局扫描
     * @import是用来引入子配置项
     * @PropertySource 注解使用来引用配置文件的
     * @bean注解是用来代替Bean标签的,他可以方法在方法上,返回值就是bean对象,方法名是id,形参默认都是autowired注入
     */
}
