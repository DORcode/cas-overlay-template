package com.coin.web;

import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServiceController
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-05 14:32
 * @Version V1.0
 **/
@RestController
public class ServiceController {

    @Autowired
    ServicesManager servicesManager;
}
