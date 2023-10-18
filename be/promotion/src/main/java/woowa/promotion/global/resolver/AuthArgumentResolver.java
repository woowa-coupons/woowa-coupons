package woowa.promotion.global.resolver;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import woowa.promotion.admin.admin.domain.Admin;
import woowa.promotion.app.member.domain.Member;
import woowa.promotion.global.exception.ApiException;
import woowa.promotion.global.exception.domain.AuthorizationException;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authentication.class)
                && (Member.class.isAssignableFrom(parameter.getParameterType())
                || Admin.class.isAssignableFrom(parameter.getParameterType()));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/admin")) {
            return request.getAttribute("admin");
        }

        if (requestURI.startsWith("/app")) {
            return request.getAttribute("member");
        }

        throw new ApiException(AuthorizationException.INVALID_ACCESS); // TODO: QA 해보고 어떤 상황에 발생할 수 있는 예외인지 확인 필요
    }
}
