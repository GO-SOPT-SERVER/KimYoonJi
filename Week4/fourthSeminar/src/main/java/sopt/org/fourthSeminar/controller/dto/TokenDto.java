package sopt.org.fourthSeminar.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}
