package sopt.org.fourthSeminar.config.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sopt.org.fourthSeminar.config.jwt.JwtService;
import sopt.org.fourthSeminar.controller.dto.TokenDto;
import sopt.org.fourthSeminar.exception.model.ExpiredAccessTokenException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String accessToken = request.getHeader("Authorization").split(" ")[1];
        String refreshToken = request.getHeader("Refresh").split(" ")[2];

        try {
            // 토큰 검증
            if (!jwtService.verifyToken(accessToken) || !jwtService.verifyRefreshToken(refreshToken)) {
                throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
            }
        } catch (ExpiredAccessTokenException e) {
            TokenDto tokenDto = jwtService.regenerateToken(refreshToken);
            accessToken = tokenDto.getAccessToken();
            refreshToken = tokenDto.getRefreshToken();
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(accessToken);
        try {
            return Long.parseLong(tokenContents);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }
    }
}