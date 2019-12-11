package com.ityu.mall.aspect;

import com.ityu.mall.model.LogBean;
import com.ityu.mall.service.impl.LogBeanService;
import com.ityu.mall.util.IPUtils;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.ityu.mall.util.ItyuUtilKt.getHttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author MrBird
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    private final LogBeanService logBeanService;

    public LogAspect(LogBeanService logBeanService) {
        this.logBeanService = logBeanService;
    }

    @Pointcut("@annotation(com.ityu.mall.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        HttpServletRequest request = getHttpServletRequest();
        // 设置 IP地址
        String ip = IPUtils.getRealIP(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        LogBean log = new LogBean();
        log.setUsername("测试名字");
        log.setIp(ip);
        log.setTime(time);
        log.setMethod(request.getMethod());
        logBeanService.save(log);
        return result;
    }
}
