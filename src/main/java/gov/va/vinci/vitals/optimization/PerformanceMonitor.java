package gov.va.vinci.vitals.optimization;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import gov.va.vinci.leo.annotationpattern.AnnotationPatternService;
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

    @Around("call(* annotate(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        EtmPoint etmPoint = etmMonitor.createPoint(point.getTarget().getClass().getCanonicalName());
        Object result = point.proceed();
        etmPoint.collect();
        return result;
    }

    @Around("call(* gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator.processPattern(..))")
    public Object apaAroundPattern(ProceedingJoinPoint point) throws Throwable {

        AnnotationPatternService service = (AnnotationPatternService) point.getArgs()[0];
        EtmPoint etmPoint = etmMonitor.createPoint(point.getTarget().getClass().getCanonicalName() + ":" + service.getAnnotationPattern().getPattern());
        Object result = point.proceed();
        etmPoint.collect();
        return result;
    }


}
