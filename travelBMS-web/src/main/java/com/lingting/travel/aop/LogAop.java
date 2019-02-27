package com.lingting.travel.aop;

import com.lingting.travel.controller.SysLogController;
import com.lingting.travel.domain.SysLog;
import com.lingting.travel.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Date;

/**
    放入 IOC 容器
    切面 注解配置方式配置
    在每个特定的方法 前 后 执行；生成日志信息
 */
@Component
@Aspect
public class LogAop {

    /** 获取容器中的 web 原生 API */
    @Autowired
    private HttpServletRequest request;

    /** 日志service层 bean*/
    @Autowired
    private SysLogService sysLogService;

    /** 访问时间 */
    private Date startTime;

    /** 访问的类 */
    private Class executionClass;

    /** 访问的方法 */
    private Method executionMethod;

    /** 前置通知
     获取 访问的时间 访问的类 访问的方法
     JoinPoint 封装了 要访问信息
     */
    @Before("execution(* com.lingting.travel.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        // 访问的时间
        startTime = new Date();

        // 访问的类
        executionClass = jp.getTarget().getClass();

        // 访问的方法 方法的名称
        String methodName = jp.getSignature().getName();

        // 获取访问的方法的参数
        Object[] args = jp.getArgs();

        // 分情况讨论获取 方法对象
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);
        } else {
            // 有参数的情况，需要加盖args中所有的元素遍历，获取对应的Class,装入一个 Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }

            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    /** 后置通知
     获取 时长， ip url ...

     */
    @After("execution(* com.lingting.travel.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        // 获取类上的 @RequestMapping 对象
        if (executionClass != SysLogController.class) {
            RequestMapping classAnnotion = (RequestMapping)executionClass.getAnnotation(RequestMapping.class);

            if (classAnnotion != null) {
                // 获取方法上的 @RequestMapping对象
                RequestMapping methodAnnotion = (RequestMapping)executionMethod.getAnnotation(RequestMapping.class);

                if (methodAnnotion != null) {

                    // 日志信息的pojo
                    SysLog sysLog = new SysLog();

                    // 拼接 类上与方法上的 @RequestMapping对象 的value
                    sysLog.setUrl(classAnnotion.value()[0] + methodAnnotion.value()[0]);

                    // 计算访问时长
                    sysLog.setExecutionTime(System.currentTimeMillis() - startTime.getTime());

                    // 获取 ip
                    sysLog.setIp(request.getRemoteAddr());

                    // 还可以通过securityContext中获取，也可以从request.getSession中获取

                    /*
                     SecurityContext context = (SecurityContext)
                     request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                     */
                    SecurityContext context = SecurityContextHolder.getContext();

                    String username = ((User)(context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setUsername(username);

                    sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());

                    sysLog.setVisitTime(startTime);

                    // 调用Service, 将sysLog对象插入到数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
