package dev.saksham.loggeraspect;

import dev.saksham.ClassLoggerAspect;
import dev.saksham.LoggerAspect;
import dev.saksham.Calculator;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class LoggeraspectApplication {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LoggeraspectApplication.class,Calculator.class, ClassLoggerAspect.class);
        context.refresh();
        Calculator calc = context.getBean(Calculator.class);
        calc.getCurrTime();
        List<String> list=new ArrayList<String>();
        list.add("hello");
        list.add("world");
        calc.tryList(list);
        calc.sum(10,15);
        //calc.divide(10,5);
        //calc.mulitply(10,5);
        //calc.subtract(10,5);
    }

}
