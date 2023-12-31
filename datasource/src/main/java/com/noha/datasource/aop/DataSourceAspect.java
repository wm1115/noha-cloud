package com.noha.datasource.aop;


import com.noha.datasource.annotation.TargetDataSource;
import com.noha.datasource.dysource.DyContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
@Slf4j
public class DataSourceAspect implements Ordered {


    @Pointcut("@annotation(com.noha.datasource.annotation.TargetDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        TargetDataSource ds = method.getAnnotation(TargetDataSource.class);
        if (ds != null) {
            DyContextHolder.setDataSourceKey(ds.name());
        }
        try {
            return point.proceed();
        } finally {
            log.info("清除线程数据源，后续使用默认数据源");
            DyContextHolder.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
