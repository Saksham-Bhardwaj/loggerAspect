package dev.saksham;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggerAspect {
    //execution(@MethodLogger * *(..))
    @Around(value="execution(@MethodLogger * *(..)) && @annotation(methodLogger)", argNames= "pjp,methodLogger")
    public Object loggerMethod(ProceedingJoinPoint pjp , MethodLogger methodLogger) throws Throwable{
        long startTime =0;
        if(methodLogger.enter()){
            startTime = System.currentTimeMillis();
        }
        Object currClass = pjp.getTarget().getClass().getName();
        Logger logger = Logger.getLogger(currClass.getClass());
        String methodName = pjp.getSignature().getName();
        logger.info("Entering Class " + currClass + " With Method Name " + methodName);

        Object[] params= pjp.getArgs();;
        //log if parameters are specified to be logged
        if(methodLogger.parameters()){
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
        if(methodLogger.returnValue()){
            logger.info("Excecution Completed for method : " + methodName + " in Class : " + currClass + " with result "
                    + output);
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        //Log if Exit time is specified to be logged
        if(methodLogger.exit()){
            logger.info("Execution time for method : " + methodName + " in Class : " + currClass + " : " + elapsedTime
                    + " milliseconds.");
        }
        return output;
    }



    /*@Around(value = "execution(* *(..))")
    public Object logAll(ProceedingJoinPoint pjp) throws Throwable{
        //Demo Logger to log all methods
        Object currClass = pjp.getTarget().getClass().getName();
        Logger logger = Logger.getLogger(currClass.getClass());
        String methodName = pjp.getSignature().getName();
        Object output = pjp.proceed(pjp.getArgs());
        logger.info("Logging All methods: "+methodName);
        return output;
    }*/

}
