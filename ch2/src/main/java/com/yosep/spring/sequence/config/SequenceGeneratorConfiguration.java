package com.yosep.spring.sequence.config;

import com.yosep.spring.sequence.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceGeneratorConfiguration {
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        sequenceGenerator.setPrefix("30");
        sequenceGenerator.setSuffix("A");
        sequenceGenerator.setInitial(100000);
        return sequenceGenerator;
    }
}
