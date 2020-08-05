package com.coin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TestController
 * @Description:
 * @Author kh
 * @Date 2020-07-29 19:09
 * @Version V1.0
 **/
@RestController
public class TestController {
    @GetMapping("/user")
    public Object user(HttpServletRequest request) {
        String username = request.getAttribute("credentials").toString();
        return username;

    }
}
