package sopt.org.SecondSeminar.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRegisterRequestDto {
    // 회원 등록 시 필요한 정보
    private String name;
    private int age;
    private String gender;
    private String contact;
    private boolean marriage;
}
