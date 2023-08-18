package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class TimerAop {

    @Pointcut(value = "within(com.example.filter.controller.UserApiController)")
    public void timePointCut() {
    }

    @Before(value = "timePointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before");
    }

    @Around(value = "timePointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before method run");
        Arrays.stream(joinPoint.getArgs()).forEach(
                it -> {
                    if (it instanceof UserRequest) {
                        var tempUser = (UserRequest)it;
                        var phoneNumber = tempUser.getPhoneNumber().replace("-", "");
                        tempUser.setPhoneNumber(phoneNumber);
                    };
                }
        );

        // 암복호화 or 로깅
        var newObjs = Arrays.asList(
                new UserRequest()
        );

        var stopWatch = new StopWatch();
        stopWatch.start();


        joinPoint.proceed(newObjs.toArray());

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeMillis());

        System.out.println("after method run");
    }
}
