package sopt.org.SecondSeminar.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostingRegisterRequestDto {
    private String title;
    private String context;
    private String writer;
    private String time;
}
