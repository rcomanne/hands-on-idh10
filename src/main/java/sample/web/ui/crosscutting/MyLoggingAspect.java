package sample.web.ui.crosscutting;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {
    @Pointcut("execution(* sample.web.ui..*(..))")
    public void dummyMethod() {

    }

    @Before("dummyMethod()")
    public void loggingBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("(AOP-myLogger) Executing: "
            + joinPoint.getSignature().getDeclaringTypeName()
            + "."
            + joinPoint.getSignature().getName()
        );
    }

    @SuppressWarnings("suiqd:S00112")
    @Around("dummyMethod()")
    public Object loggingAroundAdvice(ProceedingJoinPoint point) throws Throwable {
        System.out.println("(AOP-myLogger) Before execution: "
            + point.getSignature().getDeclaringTypeName()
            + "."
            + point.getSignature().getName()
        );

        Object retValue = point.proceed();

        System.out.println("(AOP-myLogger) After execution: "
            + point.getSignature().getDeclaringTypeName()
            + "."
            + point.getSignature().getName()
        );

        return retValue;
    }
}
