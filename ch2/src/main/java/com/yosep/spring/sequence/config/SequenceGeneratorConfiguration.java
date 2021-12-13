package com.yosep.spring.sequence.config;

import com.yosep.spring.sequence.DatePrefixGenerator;
import com.yosep.spring.sequence.PrefixGenerator;
import com.yosep.spring.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PrefixConfiguration.class)
public class SequenceGeneratorConfiguration {
    @Value("#{datePrefixGenerator}")
    private PrefixGenerator prefixGenerator;

    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        sequence.setPrefixGenerator(prefixGenerator);
        return sequence;
    }
}
