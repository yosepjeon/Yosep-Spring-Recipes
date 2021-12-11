package com.yosep.spring;

import com.yosep.spring.sequence.SequenceDao;
import com.yosep.spring.sequence.SequenceGenerator;
import com.yosep.spring.shop.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Ch2Application {

    public static void main(String[] args) {
        ApplicationContext sequenceContext = new AnnotationConfigApplicationContext("com.yosep.spring.sequence");

        SequenceGenerator generator = sequenceContext.getBean(SequenceGenerator.class);

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());

		SequenceDao sequenceDao = sequenceContext.getBean(SequenceDao.class);

		System.out.println(sequenceDao.getNextValue("IT"));
		System.out.println(sequenceDao.getNextValue("IT"));

        ApplicationContext shopContext = new AnnotationConfigApplicationContext("com.yosep.spring.shop");

        Product aaa = shopContext.getBean("aaa", Product.class);
        Product cdrw = shopContext.getBean("cdrw", Product.class);

        System.out.println(aaa);
        System.out.println(cdrw);

        SpringApplication.run(Ch2Application.class, args);
    }

}
