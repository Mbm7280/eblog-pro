package com.echo.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/18 10:27
 * 项目名称: {pro-cli}
 * 文件名称: RedisConfig
 * 文件描述: [ redis 配置类 ]
 * version：1.0
 *
 ********************************************************/
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
