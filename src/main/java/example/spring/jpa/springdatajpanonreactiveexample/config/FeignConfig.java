package example.spring.jpa.springdatajpanonreactiveexample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverter getHttpMessageConverter(
            @Autowired
            final ObjectMapper objectMapper
    ) {

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Bean
    public HttpMessageConverters httpMessageConverters(
            @Autowired
                    HttpMessageConverter httpMessageConverter
    ) {

        return new HttpMessageConverters(httpMessageConverter);
    }
}
