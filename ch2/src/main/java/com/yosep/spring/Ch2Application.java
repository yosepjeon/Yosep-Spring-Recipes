package com.yosep.spring;

import com.yosep.spring.sequence.SequenceGenerator;
import com.yosep.spring.sequence.config.SequenceGeneratorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Ch2Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.yosep.spring.sequence");

        SequenceGenerator generator = context.getBean(SequenceGenerator.class);

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());

		SequenceDao sequenceDao = context.getBean(SequenceDao.class);

		System.out.println(sequenceDao.getNextValue("IT"));
		System.out.println(sequenceDao.getNextValue("IT"));

        SpringApplication.run(Ch2Application.class, args);
    }

}
