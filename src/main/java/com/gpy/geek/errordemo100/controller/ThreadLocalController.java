package com.gpy.geek.errordemo100.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: guopy
 * @Date: 2020/8/21 15:52
 * @version: v1.0.0
 */
@RestController
@RequestMapping
@Slf4j
public class ThreadLocalController {


    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("/wrong")
    public Map wrong(@RequestParam("userId") Integer userId){

        // 设置前查询一次ThreadLocal中用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();

        // 设置用户信息到ThreadLocal
        currentUser.set(userId);

        try {
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            Map result = new HashMap<>();

            result.put("before", before);
            result.put("afger", after);
            return result;
        } finally {
            currentUser.remove();
        }
    }


}