package sample.web.ui.crosscutting;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyLoggingAspect {
    @Pointcut("execution(* sample.web.ui.controller.MessageController.createAndDecorateOrder())")
    public void dummyMethod() {

    }

    @Before("dummyMethod()")
    public void loggingBeforeAdvice(JoinPoint joinPoint) {
        log.debug("Executing: {}. {}", joinPoint.getSignature().getDeclaringTypeName());
    }

    @SuppressWarnings("suiqd:S00112")
    @Around("dummyMethod()")
    public Object loggingAroundAdvice(ProceedingJoinPoint point) throws Throwable {
        log.debug("Before execution: {}. {}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        Object retValue = point.proceed();

        log.debug("After execution: {}. {}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        return retValue;
    }
}
