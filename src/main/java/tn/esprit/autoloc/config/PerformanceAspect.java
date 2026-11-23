
package tn.esprit.autoloc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* tn.esprit.autoloc.service..*(..))")
    public Object mesurerPerformance(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long debut = System.currentTimeMillis();

        Object resultat;

        try {
            // Exécuter la méthode interceptée
            resultat = joinPoint.proceed();

        } finally {

            long fin = System.currentTimeMillis();

            long duree = fin - debut;

            System.out.println("===== PERFORMANCE =====");

            System.out.println("Methode : "
                    + joinPoint.getSignature().getName());

            System.out.println("Temps d'execution : "
                    + duree + " ms");

            if (duree > 1000) {
                System.out.println(
                        "ATTENTION : execution superieure a 1000 ms !"
                );
            }
        }

        return resultat;
    }
}
