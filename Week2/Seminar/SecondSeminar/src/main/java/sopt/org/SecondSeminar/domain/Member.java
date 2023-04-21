package sopt.org.SecondSeminar.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString(exclude = "id")
public class Member {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private boolean marriage;

    // 생성자 정의
    public Member(String name, int age, String gender, String contact, boolean marriage) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.marriage = marriage;
    }
}


