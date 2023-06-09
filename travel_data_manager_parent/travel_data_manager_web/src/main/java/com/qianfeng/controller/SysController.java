package com.qianfeng.controller;

import com.qianfeng.domain.Syslog;
import com.qianfeng.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Aspect//t通知类
@Component//框架帮忙创建对象
@RequestMapping("/sysLog")
public class SysController {//该类被称为通知类
    @Pointcut(value = "execution(* com.qianfeng.controller..*.*(..))")
    public void pt1(){

    }

    private Date visitTime=null;
    @Resource
    private HttpServletRequest request;
    @Resource
    private ISysLogService sysLogService;
    //环绕通知
    @Around(value = "pt1()")
    public Object around(ProceedingJoinPoint pdj) throws Throwable {
        visitTime=new Date();//系统现在时间
        Syslog syslog=new Syslog();
        //1.id;
        syslog.setId(UUID.randomUUID().toString());
        //2用户名
        SecurityContext securityContext =SecurityContextHolder.getContext();
        User user= (User) securityContext.getAuthentication().getPrincipal();
        String username = user.getUsername();
        syslog.setUsername(username);
        //3visittime
        syslog.setVisitTime(visitTime);
        //ip
        String ip= request.getRemoteAddr();
        syslog.setIp(ip);
        //url
        //5.1当前用户请求会访问哪个类
        Object target=pdj.getTarget();
        //判断类是否存在RequestMapping这个注解
        Class targetClass=target.getClass();
        String url="";
        String method=null;
        Object[] args=null;
        if (targetClass.isAnnotationPresent(RequestMapping.class)){
            //存在注解
            RequestMapping requestMappingClass=(RequestMapping) targetClass.getAnnotation(RequestMapping.class);
            url+=requestMappingClass.value()[0];//获取到类上的一级映射地址
            String  executionMethodName=pdj.getSignature().getName();//获取方法名称
            //获取方法执行参数
            args=pdj.getArgs();
            Class[] params =new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                params[i]=args[i].getClass();
                
            }
            Method executionMethod=targetClass.getMethod(executionMethodName,params);
            method =targetClass.getName()+"."+executionMethod.getName();
            //判断方法上是否存在@RequestMapping注解
            if(executionMethod.isAnnotationPresent(RequestMapping.class)){
                RequestMapping requestMappingMethod=executionMethod.getAnnotation(RequestMapping.class);
            //获取方法上的二级映射地址
                url+=requestMappingMethod.value()[0];
            }
        }
        syslog.setUrl(url);
        //6method
        syslog.setMethod(method);
        //执行时长
        Long executionTime=new Date().getTime()-visitTime.getTime();
        syslog.setExecutionTime(executionTime);
        //执行
        Object result=pdj.proceed(args);
        //syslog插入数据库
        //调用syslogService
        sysLogService.addSysLog(syslog);

        System.out.println(result);
        return result;
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Syslog> syslogList=sysLogService.findAll();
        ModelAndView mav=new ModelAndView();
        mav.addObject("sysLogs",syslogList);
        mav.setViewName("syslog-list");
        return mav;
    }
}
