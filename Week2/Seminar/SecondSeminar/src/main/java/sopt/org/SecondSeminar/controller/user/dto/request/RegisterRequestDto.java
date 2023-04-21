package sopt.org.SecondSeminar.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE) // lombok에서 모든 멤버 변수를 인자로 받아서 자동으로 생성자 생성해 줌

public class RegisterRequestDto {
    private String gender;
    private String name;
    private String contact;
    private int age;
}
