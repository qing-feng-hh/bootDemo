package com.aspect;

import com.sun.xml.internal.ws.api.model.CheckedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @author: hehui
 * Date: 2019/8/27
 * @Description:
 */

@Aspect
@Component
public class ParamsCheckAspect {

    private static Validator validator;
    static {
        validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator("validationMessages"))) //手动指定校验提示资源（默认在resource目录下ValidationMessages.properties）
                .buildValidatorFactory().getValidator();
    }

    // 定义接口参数校验切入点
    @Pointcut("@annotation(org.springframework.validation.annotation.Validated))")
    private void validateMethod() {
    }

    @Before("validateMethod()")
    public void before(JoinPoint joinPoint) throws CheckedException{
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 执行方法参数的校验
        Set<ConstraintViolation<Object>> constraintViolations = validator.forExecutables().validateParameters(joinPoint.getThis(), signature.getMethod(), args);
        List<String> messages = Lists.newArrayList();
        for (ConstraintViolation<Object> error : constraintViolations) {
            messages.add(error.getMessage());
        }
        if(!messages.isEmpty()){
            throw new CheckedException(JSONObject.toJSONString(messages));
        }
    }

}
