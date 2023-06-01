package sopt.org.fourthSeminar.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sopt.org.fourthSeminar.controller.dto.TokenDto;
import sopt.org.fourthSeminar.exception.Error;
import sopt.org.fourthSeminar.exception.model.BadRequestException;
import sopt.org.fourthSeminar.exception.model.NotFoundException;
import sopt.org.fourthSeminar.exception.model.UnauthorizedException;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final RedisTemplate<String, String> redisTemplate;

    private final Long REFRESH_TOKEN_EXPIRE_TIME = 120 * 60 * 1000L;

    @Value("${jwt.secret}") // jwt가 시작되면 딱 한 번 실행되는 어노테이션, jwt.secret 값을 불러와 저장
    private String jwtSecret;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder()
                .encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 토큰 발급 로그인 시 String 형태로 토큰 반환
    public String issuedToken(String userId) {
        final Date now = new Date();

        // 클레임 생성
        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 60 * 1000L)); // access token 만료 기한 1분으로 설정

        //private claim 등록
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE , Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public String issuedRefreshToken(String userId) {
        final Date now = new Date();

        // 클레임 생성
        final Claims claims = Jwts.claims()
                .setSubject("refresh_token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME)); // refresh token 만료 기한 2시간으로 설정

        //private claim 등록
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE , Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public TokenDto signIn(String userId) {
        try {
            String accessToken = issuedToken(userId);
            String refreshToken = issuedRefreshToken(userId);

            // Redis에 저장 - 만료 시간 설정을 통해 자동 삭제 처리
            redisTemplate.opsForValue().set(
                    userId,
                    refreshToken,
                    REFRESH_TOKEN_EXPIRE_TIME,
                    TimeUnit.MILLISECONDS
            );

            return new TokenDto(accessToken, refreshToken);

        } catch (BadRequestException e) {
            throw new BadRequestException(Error.INVALID_CREDENTIALS_EXCEPTION, Error.INVALID_CREDENTIALS_EXCEPTION.getMessage());
        }
    }

    private Key getSigningKey() {
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT access 토큰 검증
    public boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
            }
            return false;
        }
    }

    // JWT refresh 토큰 검증
    public boolean verifyRefreshToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(Error.TOKEN_TIME_EXPIRED_EXCEPTION, Error.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage());
            }
            return false;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public TokenDto regenerateToken(String token) {
        try {
            // Refresh Token 검증
            if (!verifyRefreshToken(token)) {
                throw new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
            }

            // Access Token에서 userId를 가져온다.
            String userId = getJwtContents(token);

            // 토큰 재발행
            TokenDto tokenDto = new TokenDto(issuedToken(userId), token);

            // RefreshToken Redis에 업데이트
            redisTemplate.opsForValue().set(
                    userId,
                    token,
                    REFRESH_TOKEN_EXPIRE_TIME,
                    TimeUnit.MILLISECONDS
            );

            return tokenDto;

        } catch (NotFoundException e) {
            throw new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage());
        }
    }

    // JWT 토큰 내용 확인
    public String getJwtContents(String token) {
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }
}