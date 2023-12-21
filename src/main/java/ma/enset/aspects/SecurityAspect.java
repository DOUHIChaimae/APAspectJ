package ma.enset.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {
    @Pointcut("execution(* ma.enset.test.Application.start(..))")
    public void startPC() {

    }

    @Around("startPC()")
    public void autourStart(ProceedingJoinPoint pjp) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer username:");
        String username = scanner.nextLine();
        System.out.println("Entrer password:");
        String password = scanner.nextLine();
        if (!username.equals("admin") || !password.equals("1234")) System.out.println("Accès refusé");
        else pjp.proceed();

    }
}
