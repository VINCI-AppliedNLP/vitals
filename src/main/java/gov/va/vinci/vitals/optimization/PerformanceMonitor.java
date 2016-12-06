package gov.va.vinci.vitals.optimization;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by ryancornia on 12/6/16.
 */
@Aspect
public class PerformanceMonitor {
    /** Performance monitoring variables. **/
    private static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();
    private EtmPoint etmPoint = null;

    @Around("call(* annotate(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        etmPoint = etmMonitor.createPoint(point.getTarget().getClass().getCanonicalName());
        Object result = point.proceed();
        etmPoint.collect();
        return result;
    }
}
