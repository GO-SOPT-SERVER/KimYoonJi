package sopt.org.SecondSeminar.controller.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequestDto {
    private String name;
    private int age;
    private String gender;
    private String contact;
    private boolean marriage;
}
