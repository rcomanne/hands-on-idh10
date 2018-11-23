package sample.web.ui.crosscutting;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
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
        log.debug("Starting timing method: {}", point.getSignature());
        Object returnValue = point.proceed();
        long duration = System.currentTimeMillis() - start;
        log.debug("Call to {} took {} ms", point.getSignature(), duration);

        return returnValue;
    }
}
