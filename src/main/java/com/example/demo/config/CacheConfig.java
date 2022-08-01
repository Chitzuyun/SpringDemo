package com.example.demo.config;

import java.lang.reflect.Method;

import javax.crypto.KeyGenerator;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		return cacheManager;
	}
	
//	@Bean("demoKeyGenerator")
//	public KeyGenerator keyGenerator() {
//		return new KeyGenerator() {		
//			@Override
//			public Object generate(Object target, Method method, Object... params) {
//				Object[] args = new Object[params.length + 2];
//				args[0] = target;
//				args[1] = method;
//				System.arraycopy(params, 0, args, 2, params.length);
//				
//				return new SimpleKey(args);
//			}
//		};
//	}
	
}
