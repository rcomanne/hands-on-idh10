package sample.web.ui.crosscutting;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.management.ObjectName;

@Aspect
@Component
public class MyExecutionAspect {
    @Pointcut("@annotation(sample.web.ui.crosscutting.MyExecutionTime) && execution(* sample.web.ui..*(..))")
    public void myExecutionTimeAnnotation() {

    }

    @SuppressWarnings("squid:S00112")
    @Around("myExecutionTimeAnnotation()")
    public Object myExectuinTimeAdvice(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("(AOP_myExecTime) Starting timing method " + point.getSignature());
        Object returnValue = point.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("(AOP_myExecTime) Call to "
            + point.getSignature()
            + " took "
            + duration
            + " ms"
        );

        return returnValue;
    }
}
