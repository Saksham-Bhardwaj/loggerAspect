package dev.saksham;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
@Aspect
@EnableAspectJAutoProxy
public class ClassLoggerAspect {

    @Pointcut("execution(@ClassLogger * *.*(..))")
    void annotatedMethod() {}

    @Pointcut("execution(* (@ClassLogger *).*(..))")
    void methodOfAnnotatedClass() {}

    @Around("@annotation(classLogger)")
    public Object loggerClass(ProceedingJoinPoint pjp , ClassLogger classLogger) throws Throwable{
        long startTime =0;
        if(classLogger.enter()){
            startTime = System.currentTimeMillis();
        }
        Object currClass = pjp.getTarget().getClass().getName();
        Logger logger = Logger.getLogger(currClass.getClass());
        String methodName = pjp.getSignature().getName();
        logger.info("Entering Class " + currClass + " With Method Name " + methodName);

        Object[] params= pjp.getArgs();;
        //log if parameters are specified to be logged
        if(classLogger.parameters()){
            for(Object o:params){
                if(o instanceof Collection<?>){
                    ((Collection<?>) o).forEach(obj->{
                        logger.info("Method Params are : "+obj);
                    });
                }else{
                    logger.info("Method Params are : "+o);
                }
            }
        }
        //params = null;
        Object output = pjp.proceed(params);
        //Log if return value is specified to be logged
        if(classLogger.returnValue()){
            logger.info("Excecution Completed for method : " + methodName + " in Class : " + currClass + " with result "
                    + output);
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        //Log if Exit time is specified to be logged
        if(classLogger.exit()){
            logger.info("Execution time for method : " + methodName + " in Class : " + currClass + " : " + elapsedTime
                    + " milliseconds.");
        }
        return output;
    }

}
