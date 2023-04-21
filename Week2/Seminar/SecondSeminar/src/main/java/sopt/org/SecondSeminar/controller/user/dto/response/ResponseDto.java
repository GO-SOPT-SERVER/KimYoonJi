package sopt.org.SecondSeminar.controller.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> { // 데이터 타입이 달라질 수 있기 때문에 제네릭으로 선언
    int status;
    T data;
}