package cola.template.sb2_template.system.advice;


import cola.template.sb2_template.system.annotation.SystemLogging;
import cola.template.sb2_template.system.enums.SystemLoggingLevel;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Cola0817
 * @date 2023/9/8 15:38
 * @since 1.0
 */
@Aspect
@Component
public class SystemLoggingAspect {

    /**
     * 正常返回后通知：记录日志，日志内容为方法名称、方法的参数和返回值
     */
    @AfterReturning(pointcut = "@annotation(systemLogging)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, SystemLogging systemLogging, Object result) {
        // 获取到logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        // 获取到方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取到方法的参数
        Object[] args = joinPoint.getArgs();

        // 获取日志级别
        SystemLoggingLevel level = systemLogging.level();

        // 拼接日志内容
        String s = buildLogMessage(systemLogging, result, methodName, args);

        // 根据日志级别记录日志
        switch (level) {
            case TRACE -> logger.trace(s);
            case DEBUG -> logger.debug(s);
            case INFO -> logger.info(s);
            case WARN -> logger.warn(s);
            case ERROR -> logger.error(s);
        }
    }

    private static String buildLogMessage(SystemLogging systemLogging, Object result, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用方法：").append(methodName);
        if (systemLogging.logParams()) {
            // 将参数转为JSON字符串
            String argsJson = JSON.toJSONString(args);
            sb.append("，参数：").append(argsJson);
        }
        if (systemLogging.logResult()) {
            // 将Result转为JSON字符串
            String resultJson = JSON.toJSONString(result);
            sb.append("，返回值：").append(resultJson);
        }
        return sb.toString();
    }

    /**
     * 抛出异常后通知：记录日志，日志内容为方法名称、方法的参数和异常信息
     */
    @AfterThrowing(pointcut = "@annotation(systemLogging)", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, SystemLogging systemLogging, Exception e) {
        // 获取到logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        // 获取到方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取到方法的参数
        Object[] args = joinPoint.getArgs();

        // 获取日志级别
        SystemLoggingLevel level = systemLogging.level();

        // 拼接日志内容
        String s = buildLogMessage(systemLogging, e, methodName, args);

        // 根据日志级别记录日志
        switch (level) {
            case TRACE -> logger.trace(s);
            case DEBUG -> logger.debug(s);
            case INFO -> logger.info(s);
            case WARN -> logger.warn(s);
            case ERROR -> logger.error(s);
        }
    }

    private static String buildLogMessage(SystemLogging systemLogging, Exception e, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("调用方法：").append(methodName);
        if (systemLogging.logParams()) {
            // 将参数转为JSON字符串
            String argsJson = JSON.toJSONString(args);
            sb.append("，参数：").append(argsJson);
        }
        if (systemLogging.logException()) {
            sb.append("，异常信息：").append(e.getMessage());
        }
        return sb.toString();
    }


}
