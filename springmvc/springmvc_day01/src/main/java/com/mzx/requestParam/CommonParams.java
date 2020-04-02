package com.mzx.requestParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/requestParams")
public class CommonParams {
    @RequestMapping("/commonParams")
    public String commonParams(String name,Integer age) {
        System.out.println("成功访问到commonParams方法");
        System.out.println("name :" + name);
        System.out.println("age :" + age);
        return "success";
    }
}
