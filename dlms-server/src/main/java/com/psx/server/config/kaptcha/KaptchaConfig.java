package com.psx.server.config.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author psx
 * @date 2021/3/28 20:59
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha defaultKaptcha(){
        //验证码生成器
        DefaultKaptcha myKaptcha=new DefaultKaptcha();
        //配置
        Properties properties=new Properties();
        //是否有边框
        properties.setProperty("kaptcha.border", "yes");
        //边框的颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //边框的粗细
        properties.setProperty("kaptcha.border.thickness","1");
        //验证码文本的颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        //验证码的宽度
        properties.setProperty("kaptcha.image.width", "125");
        //验证码的高度
        properties.setProperty("kaptcha.image.height", "45");
        //验证码
        properties.setProperty("kaptcha.session.key", "code");
        //验证码文本字符内容范围
        properties.setProperty( "kaptcha.textproducer.char.string" ,"0123456789abcdefghijklmnopqrstuvwxyz");
        //验证码字符长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //验证码字符字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //字符间距，默认为2
        properties.setProperty("kaptcha.textproducer.char.space","4");
        //生成kaptcha配置
        Config config=new Config(properties);
        //加载kaptcha配置
        myKaptcha.setConfig(config);
        return myKaptcha;
    }

}
