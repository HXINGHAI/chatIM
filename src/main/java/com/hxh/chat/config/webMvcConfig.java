package com.hxh.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: H_xinghai
 * @Date: 2019/9/6 16:45
 * @Description:
 */
@Configuration
public class webMvcConfig implements WebMvcConfigurer {


    @Override
    /**
    * @Author: H_xinghai on 2019/9/6 16:57
    * @param:[registry]
    * @return:void
    * @Description: 设置首页显示策略
    */
    public void addViewControllers(ViewControllerRegistry registry){
        //主要设置为无业务逻辑跳转
        registry.addViewController("/").setViewName("forward:/view.shtml");
        //代表这个过滤器在众多过滤器中级别最高，也就是过滤的时候最先执行
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


}

