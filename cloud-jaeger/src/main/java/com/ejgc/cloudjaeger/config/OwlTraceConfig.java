package com.ejgc.cloudjaeger.config;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class OwlTraceConfig {

    private static final String TAG_COMPONENT = "java";

    @Lazy
    @Autowired
    Tracer tracer;
    @Bean
    public TracingAspect pxTracingAspect() {
        return new TracingAspect();
    }

    @Aspect
    class TracingAspect {
        @Around("@annotation(com.ejgc.cloudjaeger.annotation.OwlTrace)")
        public Object pxTraceProcess(ProceedingJoinPoint pjp) throws Throwable {
            Span span = null;
            if (tracer != null) {
                final String cls = pjp.getTarget().getClass().getName();
                final String mName = pjp.getSignature().getName();
                span = tracer.buildSpan(cls + "." + mName)
                        .withTag(Tags.COMPONENT.getKey(), TAG_COMPONENT)
                        .withTag("class", cls)
                        .withTag("method", mName)
                        .startActive(false)
                        .span();
            }
            try {
                return pjp.proceed();
            } catch (Throwable t) {
                Map exceptionLogs = new LinkedHashMap(2);
                exceptionLogs.put("event", Tags.ERROR.getKey());
                exceptionLogs.put("error.object", t);
                span.log(exceptionLogs);
                Tags.ERROR.set(span, true);
                throw t;
            } finally {
                if (tracer != null && span != null) {
                    span.finish();
                }
            }
        }
    }

}
