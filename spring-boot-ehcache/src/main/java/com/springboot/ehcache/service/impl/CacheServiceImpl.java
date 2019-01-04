package com.springboot.ehcache.service.impl;

import com.springboot.ehcache.domain.User;
import com.springboot.ehcache.reposity.UserRepository;
import com.springboot.ehcache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: Arthas
 * @date: 2019-01-04 10:32
 * @description:
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    private static final String CACHE_NAMESPACE = "CACHE_NAMESPACE";

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = CACHE_NAMESPACE, key = "'User_'+#id")
    public User findUserById(Long id) {
        log.info("没有走缓存，直接查询数据库，出现缓存击穿.....");
        return userRepository.findById(id).get();
    }
}
