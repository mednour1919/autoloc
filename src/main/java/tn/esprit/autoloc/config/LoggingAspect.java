
package tn.esprit.autoloc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // S'exécute avant chaque méthode du package service
    @Before("execution(* tn.esprit.autoloc.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("===== AVANT EXECUTION =====");

        System.out.println("Methode : "
                + joinPoint.getSignature().getName());

        System.out.println("Parametres : "
                + Arrays.toString(joinPoint.getArgs()));
    }

    // S'exécute après chaque méthode du package service
    @After("execution(* tn.esprit.autoloc.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("===== FIN EXECUTION =====");

        System.out.println("Methode terminee : "
                + joinPoint.getSignature().getName());
    }
}
