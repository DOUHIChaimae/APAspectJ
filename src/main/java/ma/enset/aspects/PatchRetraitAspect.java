package ma.enset.aspects;

import ma.enset.metier.Compte;
import ma.enset.metier.IMetierBanqueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* ma.enset.metier.IMetierBanqueImpl.retirer(..))")
    public void pc1() {
    }

    @Around("pc1() && args(code, montant)")
    public Object around(Long code, double montant, ProceedingJoinPoint pjp, JoinPoint jp) throws Throwable {
        IMetierBanqueImpl metier = (IMetierBanqueImpl) jp.getTarget();
        Compte compte = metier.consulter(code);
        if (compte.getSolde() < montant) throw new RuntimeException("Solde insuffisant");
        return pjp.proceed();
    }

}
