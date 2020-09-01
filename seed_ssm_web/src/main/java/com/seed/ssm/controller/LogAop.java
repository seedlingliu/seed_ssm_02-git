package com.seed.ssm.controller;

import com.seed.ssm.domain.SysLog;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日期，
 * 获取 用户名，IP，url，访问时间，访问的方法，访问时长
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogController sysLogController;

    private Date visitTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Pointcut("execution(* com.seed.ssm.controller.*.*(..)))")
    public void pointCut(){

    }
    /**
     * 前置通知
     * 获取访问时间
     * 获取访问的类，访问的方法
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
//        sysLog.setVisitTime(visitTime);
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法名
        String methodName = jp.getSignature().getName();
        //获取传入的参数
        Object[] args = jp.getArgs();
        if (args!=null && args.length != 0){
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classes);
        }else {
            executionMethod = executionClass.getMethod(methodName);
        }
//        sysLog.setMethod("[类名] " + executionClass.getName() + " 方法名 " + methodName);
    }
    /**
     * 后置通知
     * 获取访问时长，用户名，IP，url
     */
    @After("pointCut()")
    public void doAfter() throws Exception {
        //获取类上的@RequestMapping
        if (executionClass != SysLogController.class){
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                //获取方法上的@RequestMapping
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    //获取@RequestMapping的value,并组合url
                    String url = classAnnotation.value()[0] + methodAnnotation.value()[0];

                    //获取访问时长
                    Date Now = new Date();
                    Long executionTime = Now.getTime() - visitTime.getTime();

                    //获取用户名
                    /**
                     * context的获取由两种
                     * 1：SecurityContextHolder.getContext();
                     * 2：request.getSession().getAttribute("SPRING-SECURITY-CONTEXT");
                     */
                    SecurityContext context = SecurityContextHolder.getContext();
//                    SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING-SECURITY-CONTEXT");
                    User user = (User) context.getAuthentication().getPrincipal();
                    String userName = user.getUsername();
                    //获取IP
                    String ip = request.getRemoteAddr();

                    //封装SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setUserName(userName);
                    sysLog.setUrl(url);
                    sysLog.setIp(ip);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod(executionMethod.getName());
                    sysLog.setExecutionTime(executionTime);

                    sysLogController.save(sysLog);
                }
            }
        }
    }
}
