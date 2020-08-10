package com.coin.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @ClassName CaptchaController
 * @Description: TODO
 * @Author kh
 * @Date 2020-08-10 10:50
 * @Version V1.0
 **/
@RestController
public class CaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try(OutputStream os = response.getOutputStream();) {
            ImageIO.write(image, "PNG", os);
        } catch (Exception e) {

        }
    }

}
