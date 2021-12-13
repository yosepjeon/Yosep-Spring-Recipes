package com.yosep.spring.sequence.config;

import com.yosep.spring.sequence.DatePrefixGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrefixConfiguration {
    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        DatePrefixGenerator datePrefixGenerator = new DatePrefixGenerator();
        datePrefixGenerator.setPattern("yyyyMMdd");

        return datePrefixGenerator;
    }
}
