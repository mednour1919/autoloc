
package tn.esprit.autoloc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    @AfterThrowing(
        pointcut = "execution(* tn.esprit.autoloc.service..*(..))",
        throwing = "exception"
    )
    public void gererException(
            JoinPoint joinPoint,
            Throwable exception) {

        System.out.println("===== EXCEPTION DETECTEE =====");

        System.out.println("Methode : "
                + joinPoint.getSignature().getName());

        System.out.println("Type d'exception : "
                + exception.getClass().getSimpleName());

        System.out.println("Message d'erreur : "
                + exception.getMessage());

        System.out.println("==============================");
    }
}
