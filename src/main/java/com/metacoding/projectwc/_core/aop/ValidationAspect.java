package com.metacoding.projectwc._core.aop;

import com.metacoding.projectwc._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Aspect
@Component
public class ValidationAspect {
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validationCheck(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    String errMsg = errors.getFieldErrors().get(0).getField() + ": " + errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg);
                }
            }
        }
    }
}
