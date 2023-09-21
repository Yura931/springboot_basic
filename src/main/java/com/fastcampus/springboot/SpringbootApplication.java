package com.fastcampus.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
//@Conditional(OSCondition.class)
//class ConditionTrueTest {
//    public String toString() {
//        return "ConditionTrueTest{}";
//    }
//}

@Component
@Conditional(FalseCondition.class)
class ConditionFalseTest {
    @Override
    public String toString() {
        return "ConditionFalseTest{}";
    }
}

class TrueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}


class FalseCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}

class OSCondition implements  Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        System.out.println("System.getProperties() = " + System.getProperties());
        return env.getProperty("sum.desktop").equals("windows");
    }
}

@SpringBootApplication
@ServletComponentScan
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(SpringbootApplication.class, args);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);

        Arrays.stream(beanDefinitionNames)
                .filter(b -> !b.startsWith("org"))
                .forEach(System.out::println);
    }

}
