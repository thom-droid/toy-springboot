package org.cannedcoffee.springboot.config.auth.dto;

import lombok.RequiredArgsConstructor;
import org.cannedcoffee.springboot.config.auth.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //verify if parameter is from the same annotation
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        //verify if class is the same with SessionUser class
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        //after all, any parameter with LoginUser annotation and whose class type is SessionUser.class will return true
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //when supportsParameter() returns true, httpSession will get attribute
        return httpSession.getAttribute("user");
    }
}
