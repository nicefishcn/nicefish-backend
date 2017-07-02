package com.nicefish;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*@SpringBootApplication(scanBasePackages={
        "com.nicefish.config","com.nicefish.common","com.nicefish.service",
        "com.nicefish.message","com.nicefish.swagger","com.nicefish.web"})*/
@SpringBootApplication
@RestController
@MapperScan(basePackages = {"com.nicefish.mapper"})
public class NicefishApplication {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World,kimmking!";
    }

    public static void main(String[] args) {
        SpringApplication.run(NicefishApplication.class, args);
    }

    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverter(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.QuoteFieldNames
        );
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(jsonConfig);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(mediaTypes);
        return new HttpMessageConverters(converter);
    }
}
