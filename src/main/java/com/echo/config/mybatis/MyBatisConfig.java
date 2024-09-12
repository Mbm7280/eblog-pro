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
