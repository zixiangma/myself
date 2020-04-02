package com.mzx.springmvc;

public class Quickly {
    /**
     * spring-mvc的快速入门
     *
     * 首先配置核心控制器DispatcherServlet
     * 之后配置spring-mvc配置文件文件
     * 1 开启组件扫描
     * 2 开启注解驱动(内含处理器映射器,处理器适配器等组件)
     * 3 视图解析器(配置头尾)
     *
     * @RequestMapping注解
     * 用来匹配url路径
     * 放在类上为1级路径,放在方法上为2级路径
     * 属性
     * value/path : 指定访问路径  需要"/"开头
     * method 指定方法访问 类型为数组 restFul 风格经常使用
     * params 参数 指定传递参数 post/get都可以
     * header 请求头中必须有什么
     * 接受参数
     * 1 接收普通类型参数,直接获取,要求形参名,跟传递的数据名相同,否则接收不到
     * 2 接收jeanbean 也是自动接收,但是要求属性名与传递数据名 形同,否则对应的属性值为null
     * 3 可以在jeanbean中添加list Map 类型参数
     * 4 自定义数据类型
     * 需要重新编写数据类型转换器,有String 转换成想要的类型,
     * 以日期类型为例,默认的是yyyy/MM/dd 改成 yyyy-MM-dd
     * 5 原生Api对象
     * HttpServletRequest HttpServletResponse HttpSession
     * 参数注解
     * 1 @RequestParams
     * 根据value值给标注形参赋予对应传递的值,要求value的值与传递数据的名相同
     * 属性
     * value 用来匹配传递数据
     * required  默认true 如果匹配不到对应的值,会抛出异常
     * defaultValue 设置默认值,一旦设置可以屏蔽required属性,接受不到参数的话会自动赋值给默认的值
     * 2 @RequestBody
     * 将以json格式传递过来的数据,转换成指定的类型
     * 该注解接受的参数一般都是post方法传递过来的,get传递的接受不到
     * required 能否有请求体?
     * 当值为true的时候,get方式传递会报错
     * 当值为false的时候,get请求接受到的值会为null
     * 3 @PathVariable
     * 可以接受  restFUL风格的url,并根据requestMapping重点 url进行数据匹配
     * 属性
     * value 与url中名称匹配的值
     * required 是否必须有该参数
     * 当值为true时必须有
     * 当值为false是  没有则是null
     * 4 @CookieValue
     * 接受Cookie中的值
     * 属性
     * value cookie中的key
     * required 是否必须有
     * 5 @RequestHeader
     * 接收请求头中的参数
     * 属性
     * value 请求头中的key
     * required 是否必须有
     * 6 @ModelAttributes
     * 用来补全JavaBean的,到达指定方法钱会先执行被此注解标记的方法,
     * 注意是该类的每个方法都会先经过这个方法,最后到达的值,如果请求有传送
     * 以请求的为准
     * 7 @SessionAttributes
     * model对象
     * modelAndView对象
     * 放在方法上默认是将一个键值对放在request域中
     * 放在类上默认是将键值对方放在session中
     */
}
