package com.kk.firstappdemo.repository;


import com.kk.firstappdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 *
 *
 *
 *
 */
@Repository
public class UserRepository {


    /**
     * 采用内存型的存储方式 -> map
     */
    private final ConcurrentMap<Integer, User> repository
            = new ConcurrentHashMap<>();

    private final AtomicInteger idGenerator = new AtomicInteger();



    /**
     * 保存用户对象
     * @param user {@link User} 对象
     * @return 如果成功，返回<code>true</cod>, 否则返回<code>false</code>
     */
    public boolean save(User user) {

        // ID 从 1 开始增长并返回
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);


        return repository.put(id, user) == null;
    }
}
