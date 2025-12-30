package com.example.settlement.aspect;

import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SettlementTimingAspect {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final ThreadLocal<Map<String, Long>> phases =
            ThreadLocal.withInitial(LinkedHashMap::new);

    @Around(
      "execution(* com.example.settlement.service.AbstractSettlementWorkerService.process(..))"
    )
    public Object aroundSettlementProcess(ProceedingJoinPoint pjp) throws Throwable {

        startTime.set(System.nanoTime());
        phases.get().clear();

        try {
            // 👉 calls your ORIGINAL process() method
            return pjp.proceed();

        } finally {
            long totalMs =
                (System.nanoTime() - startTime.get()) / 1_000_000;

            System.out.println(
                "[SETTLEMENT-TIMING] total=" + totalMs + " ms"
            );

            startTime.remove();
            phases.remove();
        }
    }
}
