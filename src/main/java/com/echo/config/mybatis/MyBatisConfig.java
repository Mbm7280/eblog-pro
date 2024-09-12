package com.echo.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2023/10/14 23:31
 * 项目名称: {cli}
 * 文件名称: MyBatisConfig
 * 文件描述: []
 * version：1.0
 * All rights Reserved, Designed By Echo
 *
 ********************************************************/

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/18 10:20
 * 项目名称: {pro-cli}
 * 文件名称: MyBatisConfig
 * 文件描述: [ MyBatis-plus 分页拦截器配置 ]
 * version：1.0
 *
 ********************************************************/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.echo.modules.*.mapper"})
public class MyBatisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
