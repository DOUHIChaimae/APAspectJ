package ma.enset.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    long t1, t2;


    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    @Pointcut("execution(* ma.enset.metier.IMetierBanqueImpl.*(..))")
    public void pc1() {
    }

    //@Before("pc1()")
    public void avant(JoinPoint jp) {
        t1 = System.currentTimeMillis();
        logger.info("**************************************");
        logger.info("Avant l'exécution de la méthode " + jp.getSignature());
    }

    //@After("pc1()")
    public void apres(JoinPoint jp) {
        logger.info("Après l'exécution de la méthode" + jp.getSignature());
        t2 = System.currentTimeMillis();
        logger.info("Durée d'exécution de la méthode " + jp.getSignature() + " est " + (t2 - t1) + " ms");
        logger.info("**************************************");
    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp, JoinPoint jp) throws Throwable {
        t1 = System.currentTimeMillis();
        logger.info("Avant l'exécution de la méthode " + jp.getSignature());
        Object object = pjp.proceed();
        logger.info("Après l'exécution de la méthode" + jp.getSignature());
        t2 = System.currentTimeMillis();
        logger.info("Durée d'exécution de la méthode " + pjp.getSignature() + " est " + (t2 - t1) + " ms");
        return object;
    }

}
